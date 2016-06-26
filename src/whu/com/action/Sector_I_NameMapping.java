package whu.com.action;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import whu.com.MySQLOperation.MySQLOperation;

import Bean.Sector_I_NameBean;

import com.opensymphony.xwork2.ActionSupport;

public class Sector_I_NameMapping extends ActionSupport{

	/**
	 *平台消息管理列表后台 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		MySQLOperation op=new MySQLOperation();
		Sector_I_NameBean bean=new Sector_I_NameBean();
		bean=op.getSector_I_NameMapping();
		op.close();
		JSONArray json = JSONArray.fromObject(bean);
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		System.out.println("平台数据："+json.toString());
		ServletActionContext.getResponse().getWriter().print(json.toString());
		return null;
	}
	
	

}
