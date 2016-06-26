package SectorControlWebSocket;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import net.sf.json.JSONArray;

import org.json.JSONException;
import org.json.JSONObject;

import whu.com.MySQLOperation.MySQLOperation;
/*
 * * 自动上线系统
 */
@ServerEndpoint(value = "/AutoMission", configurator = GetHttpSessionConfigurator.class)
public class AutoMissionStart {
	
	public static  Queue<SectorBean> st = new LinkedList<SectorBean>();
	public static  Queue<SectorBean> ALL_st = new LinkedList<SectorBean>();
	private static String[] ActionType_content=new String[]{"NextSector","Response","Show","online","offline","End"};
	private static String ActionType="ActionType";
	private static String ToSector="ToSector";
	private static String FromSector_I="FromSector_I";
	private static String FromSector="FromSector";
	private static String TimeInsertData="TimeInsertData";
	private static String InsertToken="InsertToken";
	
	@OnOpen
	public void onOpen(Session session, EndpointConfig config)throws IOException, EncodeException {
		System.out.println("自动系统 WebSocket  上线");	
		ALL_st.clear();
	}
	@OnMessage
	public void onMessage(String message, Session session) throws IOException,
	InterruptedException, JSONException, SQLException {
		JSONObject myJsonObject = new JSONObject(message);
		MySQLOperation 	mysqloperation=new MySQLOperation();		
	    String	actionType = myJsonObject.getString(ActionType);
	    System.out.println(message);	
		if(actionType.equals(ActionType_content[0]))
		{
			String fromsector=myJsonObject.getString(FromSector);
			String fromSector_i=myJsonObject.getString(FromSector_I);
		    SectorBean formsb=new SectorBean();
		    formsb.setTo_sector(fromsector);
		    formsb.setReturn_I(fromSector_i);
	     
	    	 
			
	     
	     
	    
	    	ArrayList<SectorBean> rs=mysqloperation.get_AutoNextSector(fromsector, fromSector_i);
	     
			System.out.println("数据库 返回的 size:"+rs.size());
			System.out.println("队列的  大小："+st.size());
			
			
		
			
			if(rs.size()==0){
				

				if(st.isEmpty()){
					
					Send_EndMSG(session);
					
				}
				else{
					SectorBean dao1 = st.poll();
					
					
						while(IS_Done(dao1)&&!st.isEmpty()){
							dao1 = st.poll();
							
						}
						
						
					
					if(st.isEmpty()){
						
						Send_EndMSG(session);
						
					}else{
					SectorBean sb = new SectorBean();
					sb.setActionType(ActionType_content[0]);
					sb.setFrom_Sector(dao1.getFrom_Sector());
					sb.setTo_sector(dao1.getTo_sector());
					sb.setI(dao1.getI());
					sb.setReturn_I(dao1.getReturn_I());
					Date d = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					// public final String format(Date date)
					String time = sdf.format(d);
					sb.setTime_InsertData(time);
					sb.setInsertToken("XXX");
					JSONArray json = JSONArray.fromObject(sb);
					ALL_st.add(sb);
					
					session.getBasicRemote().sendText(json.toString());
					
					
					
					ArrayList<SectorBean> rs_st=mysqloperation.get_AutoNextSector(dao1.getTo_sector(), dao1.getReturn_I());
					System.out.println("出站查询后的 数据库返回 size："+rs_st.size());
					
					 if(rs_st.size()>0)
					{
						
						
						for(int i=0;i<rs_st.size();i++){
							if(!IS_Contain(rs_st.get(i))){
								st.add(rs_st.get(i));	
								//ALL_st.add(rs_st.get(i));
							}
							}
						}
						
						//System.out.println("添加后的 size：："+st.size());
					}
				}
					
				}
			else{
				int k=1000;
				for(int i=0;i<rs.size();i++)
				{
					if(!IS_Done(rs.get(i))){
						k=i;
						break;
						
					}		
				}
				if(k!=1000)
				{
				SectorBean dao1=rs.get(k);
				SectorBean sb = new SectorBean();
				sb.setActionType(ActionType_content[0]);
				sb.setFrom_Sector(dao1.getFrom_Sector());
				sb.setTo_sector(dao1.getTo_sector());
				sb.setI(dao1.getI());
				sb.setReturn_I(dao1.getReturn_I());
				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				// public final String format(Date date)
				String time = sdf.format(d);
				sb.setTime_InsertData(time);
				sb.setInsertToken("XXX");
				JSONArray json = JSONArray.fromObject(sb);
				
				ALL_st.add(sb);
				session.getBasicRemote().sendText(json.toString());
				for(int i=0;i<rs.size();i++){
					if(!IS_Done(rs.get(i))){
						st.add(rs.get(i));	
						//ALL_st.add(rs.get(i));
					}
					}
				}
				else{
					
					if(st.isEmpty()){
						
						Send_EndMSG(session);
						
					}
					
					else{
						SectorBean dao1 = st.poll();
						
						while(IS_Done(dao1)&&!st.isEmpty()){
							dao1 = st.poll();
							
						}
						
						if(st.isEmpty()){
							
							Send_EndMSG(session);
							
						}else{
						SectorBean sb = new SectorBean();
						sb.setActionType(ActionType_content[0]);
						sb.setFrom_Sector(dao1.getFrom_Sector());
						sb.setTo_sector(dao1.getTo_sector());
						sb.setI(dao1.getI());
						sb.setReturn_I(dao1.getReturn_I());
						Date d = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						// public final String format(Date date)
						String time = sdf.format(d);
						sb.setTime_InsertData(time);
						sb.setInsertToken("XXX");
						JSONArray json = JSONArray.fromObject(sb);
						
						ALL_st.add(sb);
						session.getBasicRemote().sendText(json.toString());
						
						
						
						ArrayList<SectorBean> rs_st=mysqloperation.get_AutoNextSector(dao1.getTo_sector(), dao1.getReturn_I());
						System.out.println("出站查询后的 数据库返回 size："+rs_st.size());
						
						 if(rs_st.size()>0)
						{
							
							
							for(int i=0;i<rs.size();i++){
								if(!IS_Contain(rs.get(i))){
									st.add(rs.get(i));	
									//ALL_st.add(rs.get(i));
								}
								}
							}
							
							//System.out.println("添加后的 size：："+st.size());
						}
						
					}
					
				}
				}
				
			
		}	
			
			
		
		mysqloperation.close();
		
	}
	
	public Boolean IS_Done(SectorBean dao){
		 SectorBean temp;
		 Iterator<SectorBean> it = ALL_st.iterator();
	        while(it.hasNext())
	        {
	        	temp=it.next();
	            if(dao.getFrom_Sector().equals(temp.getFrom_Sector())&&dao.getI().equals(temp.getI())&&dao.getTo_sector().equals(temp.getTo_sector()))
	            	return true;
	        }
	        return false;
	}
	public Boolean IS_Contain(SectorBean dao){
		
		 SectorBean temp;
		 Iterator<SectorBean> it = st.iterator();
	        while(it.hasNext())
	        {
	        	temp=it.next();
	            if(dao.getReturn_I().equals(temp.getReturn_I())&&dao.getTo_sector().equals(temp.getTo_sector()))
	            	return true;
	        }
		   
		 return false;
		
		
	}
	public void Send_EndMSG(Session session) throws IOException{
		
		SectorBean sb = new SectorBean();
		sb.setActionType(ActionType_content[5]);
		JSONArray json = JSONArray.fromObject(sb);
		System.out.println(" End 自动 Mission 发送到前台的："+json);
		session.getBasicRemote().sendText(json.toString());
	}
	@OnClose
	public void onClose(Session session) {
		
		
	}
}
