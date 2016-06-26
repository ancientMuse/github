package whu.com.MySQLOperation;

import java.sql.Connection;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Bean.MSGBean;
import Bean.Sector_I_NameBean;
import Dao.IData;
import Dao.SysData;
import SectorControlWebSocket.SectorBean;






public class MySQLOperation {


	private GetHibernateConnection Getscn;
	private Connection conn;
	public Connection getConn() {
		return conn;
	}
	public MySQLOperation() throws SQLException{
		Getscn = new GetHibernateConnection();
		conn=Getscn.getConneciton();
	}
	
	public ArrayList<MSGBean> getMSGContentby_FormsectorAndI(String formsector,String I) throws SQLException{//  读取消息的 SQL操作函数
		
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "select A.table_name,A.tableline_string,A.tableline_value from  lib_tableinfo A ,(select * from lib_table_i where T_to_sector_name='"+formsector+"' and I_id='"+I+"') B where A.table_name=B.table_name;";
		try {
			if(conn.isClosed())
				conn=Getscn.getConneciton();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<MSGBean> MSGBean=new ArrayList<MSGBean>();
		
		while(rs.next()){
			
			MSGBean bean=new MSGBean();
			bean.setTablename(rs.getString("table_name"));
			bean.setStr(rs.getString("tableline_string"));
			bean.setValue(rs.getString("tableline_value"));
			//封装到MSGBean 中
			MSGBean.add(bean);	
		}
		
			
			if (!rs.isClosed())
				rs.close();
			if (!conn.isClosed())
				conn.close();
			return MSGBean;
	}
	public ArrayList<MSGBean> getMSGContentby_Formsector(String formsector) throws SQLException{// 读取 各个平台对于的数据
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "select A.table_name,A.tableline_string,A.tableline_value from  lib_tableinfo A ,(select * from lib_table_i where T_to_sector_name='"+formsector+"') B where A.table_name=B.table_name;";
		try {
			if(conn.isClosed())
				conn=Getscn.getConneciton();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<MSGBean> MSGBean=new ArrayList<MSGBean>();
		
		while(rs.next()){
			
			MSGBean bean=new MSGBean();
			bean.setTablename(rs.getString("table_name"));
			bean.setStr(rs.getString("tableline_string"));
			bean.setValue(rs.getString("tableline_value"));
			//封装到MSGBean 中
			MSGBean.add(bean);	
		}
		
			
			if (!rs.isClosed())
				rs.close();
			if (!conn.isClosed())
				conn.close();
			return MSGBean;
		
	}
	public Sector_I_NameBean getSector_I_NameMapping() throws SQLException{
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "select DISTINCT I_id,T_to_sector_name, i_map,i from lib_table_i left join i_map on  lib_table_i.I_id=i_map.i order by T_to_sector_name,I_id;";
		//select distinct(y_sector) from i_sector,(select * from roles where roles.sector='002') n where i_sector.x_sector=n.sector_code;
		try {
			if(conn.isClosed())
				conn=Getscn.getConneciton();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Sector_I_NameBean bean=new Sector_I_NameBean();
		ArrayList<String> sectorList=new ArrayList<String>();
		ArrayList<String> iList=new ArrayList<String>();
		ArrayList<String> NoList=new ArrayList<String>();
		ArrayList<String> sector_CountOF_Ilist=new ArrayList<String>();
		
		String Sector="";
		int count=-1;
		while(rs.next()){
			
			iList.add(rs.getString("i_map"));
			NoList.add(rs.getString("i"));
			if(!Sector.equals(rs.getString("T_to_sector_name")))
			{
				Sector=rs.getString("T_to_sector_name");
				sectorList.add(Sector);
				if(count!=-1)sector_CountOF_Ilist.add(count+"");
				count=1;
				
			}
			else{
				count++;
			}
			
			
			
			
			
		}
		sector_CountOF_Ilist.add(count+"");
		bean.setIlist(iList);
		bean.setNOlist(NoList);
		bean.setSector_CountOF_Ilist(sector_CountOF_Ilist);
		bean.setSectorlist(sectorList);
			
			if (!rs.isClosed())
				rs.close();
			if (!conn.isClosed())
				conn.close();
			return bean;
		
		
	}
	public void insert_IData(String from_sector,String to_sector,String I,String[] key,String[] value,String time,String token){
		Session session=Getscn.getSession();
		Transaction tran=session.beginTransaction();
		
		for(int i=0;i<key.length;i++){
			
			IData dao=new IData();
			dao.setFromSector(from_sector);
			dao.setI(I);
			dao.setToSector(to_sector);
			dao.setIDataKey(key[i]);
			dao.setIDataValue(value[i]);
			dao.setTime(time);
			dao.setDatatoken(token);
			session.save(dao);   
	            if(i%5==0){   //每5条刷新并写入数据库  
	                session.flush();  
	                session.clear();  
	            }  		
		}
		 tran.commit();
		 if(session.isOpen())
            session.close();		
		
	}
	public void Delete_Idata_ByID(String Delete_ID) throws SQLException{
		
		
		
		Statement stmt = null;
		
		String sql = "delete from dataflow.i_data where Id="+Integer.parseInt(Delete_ID);
		try {
			if(conn.isClosed())
				conn=Getscn.getConneciton();
			stmt = conn.createStatement();
		    stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			if (!stmt.isClosed())
				stmt.close();
			if (!conn.isClosed())
				conn.close();
			

		
	}
	public Boolean Updata_IData(IData c){
         Session      session = Getscn.getSession();
         Transaction trans=session.beginTransaction();
         String hql="update IData dao set dao.IDataKey=:IDataKey ,dao.IDataValue=:IDataValue where dao.id=:id";
         Query queryupdate=session.createQuery(hql);
         queryupdate.setString("IDataKey", c.getIDataKey());
         queryupdate.setString("IDataValue", c.getIDataValue());
         queryupdate.setInteger("id", c.getId().intValue());
       ///  session.
         int ret=queryupdate.executeUpdate();
         trans.commit();
         if(session.isOpen())
        	 session.close();
         if(ret==1)return true;
         else return false;
		
		
	}
	public Boolean CheckUserName(String username,String psw,String sector) throws SQLException{
		ResultSet rs = null;
		Statement stmt = null;
		
		String sql = "select * from dataflow.roles where roles.username='"
				+ username + "' and roles.psw='"+psw+"' and sector='"+sector+"'";
		try {
			if(conn.isClosed())
				conn=Getscn.getConneciton();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (rs.next()) {
			if (!rs.isClosed())
				rs.close();
			if (!conn.isClosed())
				conn.close();
			return true;

		} else {
			if (!rs.isClosed())
				rs.close();
			if (!conn.isClosed())
				conn.close();
			return false;
		}
		// return false;

	}
	public List<String> getSectorfrom(String from_sector) throws SQLException{
		
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "select distinct(y_sector) from dataflow.i_sector, (select * from dataflow.roles where dataflow.roles.sector='"+from_sector+"') n where dataflow.i_sector.x_sector=n.sector_code";
		//select distinct(y_sector) from i_sector,(select * from roles where roles.sector='002') n where i_sector.x_sector=n.sector_code;
		try {
			if(conn.isClosed())
				conn=Getscn.getConneciton();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<String> sectorList=new ArrayList<String>();
		
		while(rs.next()){
			
			sectorList.add(rs.getString("y_sector"));
			
			
		}
			
			
			if (!rs.isClosed())
				rs.close();
			if (!conn.isClosed())
				conn.close();
			return sectorList;
		
		
	}
	
	public ArrayList<String> get_Response_Sector(String fromsector,String x_i) throws SQLException{
		
		  ResultSet rs = null;
			//Connection cn = null;
		  ArrayList<String> list=new ArrayList<String>();
			Statement stmt = null;
			String sql = "select * from dataflow.i_sector where x_sector='"+fromsector+"' and x_i='"+x_i+"'";
			try {
				if(conn.isClosed())
					conn=Getscn.getConneciton();
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
			
			while(rs.next()){
				
				list.add(rs.getString("y_sector"));
				
				
			}
				
				
				if (!rs.isClosed())
					rs.close();
				if (!conn.isClosed())
					conn.close();
				return list;
		
	} 
   public ArrayList<IData>	getInsertData_Bytoken(String InsertToken) throws SQLException{
	   
	    ArrayList<IData> rsList=new ArrayList<IData>();
	    ResultSet rs = null;
		//Connection cn = null;
		Statement stmt = null;
		String sql ="select * from dataflow.i_data where datatoken='"+InsertToken+"'";
		try {
			if(conn.isClosed())
				conn=Getscn.getConneciton();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		while(rs.next()){
			IData bean=new IData();
			
			bean.setId(Integer.parseInt(rs.getString("Id")));
			bean.setIDataKey(rs.getString("I_data_key"));
			bean.setIDataValue(rs.getString("I_data_value"));
			rsList.add(bean);	
		}
			
			
			if (!rs.isClosed())
				rs.close();
			if (!conn.isClosed())
				conn.close();
			return rsList;
   }
	
   public String get_NextSector_Y_I(String fromsector,String tosector,String I) throws SQLException{
	   
	   ResultSet rs = null;
		//Connection cn = null;
		Statement stmt = null;
		String sql = "select * from dataflow.i_sector where x_i='"+I+"' and x_sector='"+fromsector+"' and y_sector='"+tosector+"'";
		try {
			if(conn.isClosed())
				conn=Getscn.getConneciton();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String rsstr="";
		
		while(rs.next()){
			
			rsstr=rs.getString("y_i");
			
			
		}
			
			
			if (!rs.isClosed())
				rs.close();
			if (!conn.isClosed())
				conn.close();
			return rsstr;
	   
	   
	   
   }
   public List<String> get_toI_from(String from_sector,String to_sector) throws SQLException{
		
		ResultSet rs = null;
	
		Statement stmt = null;
		String sql = "select * from dataflow.i_sector, (select * from dataflow.roles where dataflow.roles.sector='"+from_sector+"') n where dataflow.i_sector.x_sector=n.sector_code and dataflow.i_sector.y_sector='"+to_sector+"'";
		try {
			if(conn.isClosed())
				conn=Getscn.getConneciton();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<String> sectorList=new ArrayList<String>();
		
		while(rs.next()){
			
			sectorList.add(rs.getString("x_i"));
			
			
		}
			
			
			if (!rs.isClosed())
				rs.close();
			if (!conn.isClosed())
				conn.close();
			return sectorList;
		
		
	}
    public String getSectorName(String sectorcode) throws SQLException{
    	ResultSet rs = null;
	
		Statement stmt = null;
		String sql = "select * from dataflow.sector_map where Sector_code='"+sectorcode+"'";
		try {
			if(conn.isClosed())
				conn=Getscn.getConneciton();
		
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	  String sectorname="";
		
		while(rs.next()){
			
			sectorname=rs.getString("Sector_name");
			
			
		}
			
			
			if (!rs.isClosed())
				rs.close();
			if (!conn.isClosed())
				conn.close();
			return sectorname;
    }
    public String getI_mapcode(String i) throws SQLException{
    	ResultSet rs = null;
		Statement stmt = null;
		String sql = "select * from dataflow.i_map where i='"+i+"'";
		try {
			if(conn.isClosed())
				conn=Getscn.getConneciton();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	   String i_map="";
		
		while(rs.next()){
			
			i_map=rs.getString("i_map");
			
			
		}
			
			
			if (!rs.isClosed())
				rs.close();
			if (!conn.isClosed())
				conn.close();
			return i_map;
    	
    	
    }
    
    public ArrayList<SectorBean> get_AutoNextSector(String fromsector,String code) throws SQLException{
    	
    	ResultSet rs = null;
		Statement stmt = null;
		String sql = "select * from dataflow.i_sector where x_sector='"+fromsector+"' and x_i='"+code+"'";
		ArrayList<SectorBean> re=new ArrayList<SectorBean>();
		try {
			if(conn.isClosed())
				conn=Getscn.getConneciton();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(rs.next()){
			SectorBean dao=new SectorBean();
			dao.setFrom_Sector(rs.getString("x_sector"));
			dao.setTo_sector(rs.getString("y_sector"));
			dao.setI(rs.getString("x_i"));
			dao.setReturn_I(rs.getString("y_i"));
			
			String I=rs.getString("y_i");
			System.out.println("数据操作get_AutoNextSector y_I:"+I);
			if(I!=null)
				re.add(dao);	
		}
			
			
			if (!rs.isClosed())
				rs.close();
			if (!conn.isClosed())
				conn.close();
			return re;
    	
    }
	public ArrayList<SysData> get_Sys_Data(String sector) throws SQLException{
		
		
		ResultSet rs = null;
		Statement stmt = null;
		String sql = "select * from dataflow.sys_data where sys_sector='"+sector+"'";
		ArrayList<SysData> re=new ArrayList<SysData>();
		try {
			if(conn.isClosed())
				conn=Getscn.getConneciton();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(rs.next()){
			SysData dao=new SysData();
			dao.setSysKey(rs.getString("sys_key"));
			dao.setSysValue(rs.getString("sys_value"));
			re.add(dao);	
		}
			
			
			if (!rs.isClosed())
				rs.close();
			if (!conn.isClosed())
				conn.close();
			return re;
		
	}
	public void close() throws SQLException{
		if(!conn.isClosed())
			conn.close();
		Getscn.close();
		
		
	}

}
