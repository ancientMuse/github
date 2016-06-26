package whu.com.action;

import java.util.ArrayList;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;

import whu.com.MySQLOperation.MySQLOperation;

import Dao.IData;

import com.opensymphony.xwork2.ActionSupport;

public class Get_InsertDataItem extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String InsertToken;

	public String getInsertToken() {
		return InsertToken;
	}

	public void setInsertToken(String insertToken) {
		InsertToken = insertToken;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		MySQLOperation mysqlop=new MySQLOperation();
		
		ArrayList<IData> rs=mysqlop.getInsertData_Bytoken(InsertToken);
		JSONArray jsonp = JSONArray.fromObject(rs); 
		mysqlop.close();
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		ServletActionContext.getResponse().getWriter().print(jsonp.toString());
		return null;
	}

}
