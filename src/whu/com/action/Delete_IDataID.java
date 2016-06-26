package whu.com.action;

import org.apache.struts2.ServletActionContext;

import whu.com.MySQLOperation.MySQLOperation;

import com.opensymphony.xwork2.ActionSupport;

public class Delete_IDataID extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String Delete_ID;
	public String getDelete_ID() {
		return Delete_ID;
	}
	public void setDelete_ID(String delete_ID) {
		Delete_ID = delete_ID;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
		MySQLOperation mysqlop=new MySQLOperation();
		System.out.println("删除的  ID"+Delete_ID);
		mysqlop.Delete_Idata_ByID(Delete_ID);
		mysqlop.close();
		ServletActionContext.getResponse().getWriter().print("");
		return null;
	}

}
