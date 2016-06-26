package whu.com.action;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

import net.sf.json.JSONArray;

import whu.com.MySQLOperation.MySQLOperation;

import com.opensymphony.xwork2.ActionSupport;

public class NextSector extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Fromsector;
	private String ToSector;
	private String FromSector_I;
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
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		MySQLOperation mysqlop=new MySQLOperation();
		String y_i=mysqlop.get_NextSector_Y_I(Fromsector, ToSector, FromSector_I);
		
		System.out.println("xiayige :"+y_i+Fromsector+ToSector+FromSector_I);
		String sector=ToSector;
		
		ArrayList<String> rs=mysqlop.get_Response_Sector(sector, y_i);
		HashMap json=new HashMap();
		
		if(!rs.isEmpty()){
			
	
				
				json.put("Sector",rs);
				
			///	ArrayList<String> as=(ArrayList<String>) mysqloperation.get_toI_from(sector, to_sector);
				String I_code=mysqlop.getI_mapcode(y_i);
				String I_MAP=mysqlop.getI_mapcode(FromSector_I);
				json.put("I",y_i);
				json.put("FromSector",sector);
				json.put("Source_FromSector",Fromsector);
				json.put("FromSector_I", FromSector_I);
				json.put("Icode", I_code);
				json.put("I_MAP",I_MAP);
				json.put("Status", "201");
				//root.add(json);
				JSONObject jsonp = new JSONObject(json); 
				mysqlop.close();
				
				System.out.println("Response的 JSON对象 ："+jsonp.toString());
				ServletActionContext.getResponse().setStatus(201);
				ServletActionContext.getResponse().getWriter().print(jsonp);
				
				return null;
				  
			
			
		}
		else {
			mysqlop.close();
			json.put("FromSector",sector);
			json.put("FromSector_I", FromSector_I);
			json.put("Status", "202");
			JSONObject jsonp = new JSONObject(json); 
			ServletActionContext.getResponse().getWriter().print(jsonp);
			
			return null;
			
			
			
		}
		 
		
		
	//	return super.execute();
	}
	

}
