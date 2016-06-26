package whu.com.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import whu.com.MySQLOperation.MySQLOperation;

import com.opensymphony.xwork2.ActionSupport;

public class Insert_IData extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Fromsector;
	private String ToSector;
	private String FromSector_I;
	private String[] I_data_key;
	private String[] I_data_value;
	public String getFromsector() {
		return Fromsector;
	}
	public void setFromsector(String fromsector) {
		Fromsector = fromsector;
	}
	public String getToSector() {
		return ToSector;
	}
	public void setToSector(String toSector) {
		ToSector = toSector;
	}
	public String getFromSector_I() {
		return FromSector_I;
	}
	public void setFromSector_I(String fromSector_I) {
		FromSector_I = fromSector_I;
	}
	public String[] getI_data_key() {
		return I_data_key;
	}
	public void setI_data_key(String[] i_data_key) {
		I_data_key = i_data_key;
	}
	public String[] getI_data_value() {
		return I_data_value;
	}
	
	public void setI_data_value(String[] i_data_value) {
		I_data_value = i_data_value;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		//System.out.println("插入数据："+I_data_key[0]);
		//System.out.println("插入值："+I_data_value[0]);
		
		MySQLOperation mysqlop=new MySQLOperation();
		
		Insert_DataToken u=new Insert_DataToken();
		String token=u.getRandomString(12);
		// 给定模式
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// public final String format(Date date)
		String time = sdf.format(d);
		mysqlop.insert_IData(Fromsector, ToSector, FromSector_I, I_data_key, I_data_value,time,token);
		ArrayList<HashMap<String, String>> root=new ArrayList<HashMap<String, String>>();
		HashMap<String, String> json= new HashMap<String, String>();
		json.put("TimeInsertData",time);
		json.put("InsertToken", token);
		root.add(json);
		
		 
		JSONArray jsonp = JSONArray.fromObject(root); 
		mysqlop.close();
		System.out.println(Fromsector+ToSector+FromSector_I+I_data_key+"插入数据返回"+jsonp.toString());
		ServletActionContext.getResponse().getWriter().print(jsonp.toString());
		return null;
	}

}
