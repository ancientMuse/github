package whu.com.action;

import org.apache.struts2.ServletActionContext;

import whu.com.MySQLOperation.MySQLOperation;
import Dao.IData;

import com.opensymphony.xwork2.ActionSupport;

public class Update_IData extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String[] ChangeID;
	private String[] ChangeKey;
	private String[] ChangeValue;

	public String[] getChangeID() {
		return ChangeID;
	}

	public void setChangeID(String[] changeID) {
		ChangeID = changeID;
	}

	public String[] getChangeKey() {
		return ChangeKey;
	}

	public void setChangeKey(String[] changeKey) {
		ChangeKey = changeKey;
	}

	public String[] getChangeValue() {
		return ChangeValue;
	}

	public void setChangeValue(String[] changeValue) {
		ChangeValue = changeValue;
	}

	@Override
	public String execute() throws Exception {
		MySQLOperation mysqlop=new MySQLOperation();
		for(int i=0;i<ChangeID.length;i++){
			IData dao=new IData();
			dao.setId(Integer.parseInt(ChangeID[i]));
			dao.setIDataKey(ChangeKey[i]);
			dao.setIDataValue(ChangeValue[i]);
			System.out.println("更新数据："+ChangeID[i]+"  "+ChangeKey[i]+"   "+ChangeValue[i]);
			mysqlop.Updata_IData(dao);
			
		}
		mysqlop.close();
		// TODO Auto-generated method stub
		ServletActionContext.getResponse().getWriter().print("");
		return null;
	}
	

}
