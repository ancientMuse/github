package whu.com.action;

import java.util.ArrayList;
import java.util.HashMap;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import whu.com.MySQLOperation.MySQLOperation;

import Dao.SysData;

import com.opensymphony.xwork2.ActionSupport;

public class Get_sysdata extends ActionSupport {

	/**
	 * 可以忽略
	 */
	private static final long serialVersionUID = 1L;
    public static final String e="e";//电力系统
    public static final String d="d";//损管系统
    public static final String p="p";//动力系统
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		MySQLOperation mysqlop=new MySQLOperation();
		HashMap<String, ArrayList<HashMap<String,String>>> root= new HashMap<String, ArrayList<HashMap<String,String>>>();
		
		ArrayList<SysData> rslist=new ArrayList<SysData>();
		ArrayList<HashMap<String,String>> line_e=new ArrayList<HashMap<String,String>>();
		ArrayList<HashMap<String,String>> line_d=new ArrayList<HashMap<String,String>>();
		ArrayList<HashMap<String,String>> line_p=new ArrayList<HashMap<String,String>>();
		rslist=mysqlop.get_Sys_Data(e);
		for(int i=0;i<rslist.size();i++){
			HashMap<String,String> dao=new HashMap<String,String>();
			dao.put("name", rslist.get(i).getSysKey());
			dao.put("value", rslist.get(i).getSysValue());	
			line_e.add(dao);
		}
		rslist=mysqlop.get_Sys_Data(d);
		for(int i=0;i<rslist.size();i++){
			HashMap<String,String> dao=new HashMap<String,String>();
			dao.put("name", rslist.get(i).getSysKey());
			dao.put("value", rslist.get(i).getSysValue());	
			line_d.add(dao);
		}
		rslist=mysqlop.get_Sys_Data(p);
		for(int i=0;i<rslist.size();i++){
			HashMap<String,String> dao=new HashMap<String,String>();
			dao.put("name", rslist.get(i).getSysKey());
			dao.put("value", rslist.get(i).getSysValue());	
			line_p.add(dao);
		}
		root.put(e, line_e);
		root.put(d, line_d);
		root.put(p, line_p);
		JSONArray jsonp = JSONArray.fromObject(root); 
		mysqlop.close();
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		System.out.println("平台数据："+jsonp.toString());
		ServletActionContext.getResponse().getWriter().print(jsonp.toString());
		
		
		return null;
	}

}
