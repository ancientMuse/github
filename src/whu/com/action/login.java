package whu.com.action;




import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import whu.com.MySQLOperation.MySQLOperation;

import com.opensymphony.xwork2.ActionSupport;

public class login extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String psw;
	private String sector = "";
	private Boolean IS_Roles_Exist = false;
	private static final String MonitorCode = "000";// 000
	// private static final String SectorCode="002";//001-008
	private static final String MonitorURL = "Main.html";
	private static final String SectorURL = "Sector.jsp";

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}
   
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		MySQLOperation 	mysqloperation=new MySQLOperation();
		IS_Roles_Exist=mysqloperation.CheckUserName(username, psw, sector);
		
		System.out.print(username+psw+sector);
		mysqloperation.close();
		Date d = new Date();
		// 给定模式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// public final String format(Date date)
		String time = sdf.format(d);
		if(!IS_Roles_Exist)
			{
			
			ServletActionContext.getResponse().setStatus(411);
			ServletActionContext.getResponse().getWriter().print("");
			return null;
			}
		else
		{
			
			
			
			if(sector.equals(MonitorCode))
				{
			
				ServletActionContext.getRequest().getSession().setAttribute("user", username);
				ServletActionContext.getRequest().getSession().setAttribute("sector",sector);
				ServletActionContext.getResponse().setStatus(202);
				ServletActionContext.getResponse().getWriter().print(MonitorURL);//返回监控页面
				return null;
				}
			else 
			{   
				ServletActionContext.getRequest().getSession().setAttribute("user", username);
				ServletActionContext.getRequest().getSession().setAttribute("sector", sector);
				ServletActionContext.getRequest().getSession().setAttribute("loginTime", time);
				ServletActionContext.getResponse().setStatus(202);
				ServletActionContext.getResponse().getWriter().print(SectorURL);//返回部门界面
				return null;
			}
		}
	}
}
