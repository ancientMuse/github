package whu.com.action;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.struts2.ServletActionContext;

import whu.com.MySQLOperation.MySQLOperation;

import com.opensymphony.xwork2.ActionSupport;

public class Sector_I_Query extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String sectorcode;
	public String getSectorcode() {
		return sectorcode;
	}

	public void setSectorcode(String sectorcode) {
		this.sectorcode = sectorcode;
	}

	public String getI() {
		return i;
	}

	public void setI(String i) {
		this.i = i;
	}

	private String i;
	
	public String get_Sector_name() throws SQLException, IOException{
		MySQLOperation mysqloperation=new MySQLOperation();
		
		String sectorname=mysqloperation.getSectorName(sectorcode);
		mysqloperation.close();
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		ServletActionContext.getResponse().getWriter().print(sectorname);
		return null;
	}
	public String get_I_code() throws SQLException, IOException{
		MySQLOperation mysqloperation=new MySQLOperation();
		
		String i_code=mysqloperation.getI_mapcode(i);
		mysqloperation.close();
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		String str="[{'I':'"+i_code+"','I_MAP':'"+i+"'}]";
		ServletActionContext.getResponse().getWriter().print(str);
		return null;
	}


}
