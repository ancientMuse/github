package whu.com.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;

import whu.com.MySQLOperation.MySQLOperation;

import com.opensymphony.xwork2.ActionSupport;

public class Instruction_Sector_Map extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String sector;
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		List<String> t=new ArrayList<String>();
		MySQLOperation mysqloperation=new MySQLOperation();
		ArrayList root=new ArrayList();
	
		
		 t=mysqloperation.getSectorfrom(sector);
		
		for(int i=0;i<t.size();i++){
			HashMap json= new HashMap();
			json.put("Sector",(String)t.get(i));
			String to_sector=(String) t.get(i);
			ArrayList<String> as=(ArrayList<String>) mysqloperation.get_toI_from(sector, to_sector);
			json.put("From_I", as);
			root.add(json);
		}
		 
		  JSONArray jsonp = JSONArray.fromObject(root); 
		  
		  System.out.println(jsonp.toString());
		 // System.out.println(b)
		  mysqloperation.close();
		  ServletActionContext.getResponse().getWriter().print(jsonp);
		
		
		return null;
	}

}
