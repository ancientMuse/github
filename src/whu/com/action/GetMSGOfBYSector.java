package whu.com.action;

import java.util.ArrayList;
import java.util.HashMap;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import whu.com.MySQLOperation.MySQLOperation;
import Bean.MSGBean;

import com.opensymphony.xwork2.ActionSupport;

public class GetMSGOfBYSector extends ActionSupport {

	
	private static final long serialVersionUID = 1L;
	/**
	 * 获取各个部门 数据库中的模拟消息数据，需要传入 1个参数
	 * 1，部门名称  FromSectorName
	 * 
	 */

	public String FromSectorName;
	public String getFromSectorName() {
		return FromSectorName;
	}
	
	public void setFromSectorName(String fromSectorName) {
		FromSectorName = fromSectorName;
	}
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
        MySQLOperation mysqlop=new MySQLOperation();
        ArrayList root=new ArrayList();
      
		ArrayList<MSGBean> rs=mysqlop.getMSGContentby_Formsector(FromSectorName);
		System.out.println("请求数据："+FromSectorName+":");
		ArrayList<String> tableList=new ArrayList<String>();
	  if(rs.size()>0){	tableList.add(rs.get(0).tablename);
		for(int i=0;i<rs.size();i++){
			if(!tableList.contains(rs.get(i).tablename)){
				tableList.add(rs.get(i).tablename);
				
			}
			
			
		}
		
		
		for(int i=0;i<tableList.size();i++){
			HashMap json=new HashMap();
			String tempName=tableList.get(i);
			ArrayList<MSGBean> tempMsgbean=new ArrayList<MSGBean>();
			
			for(int j=0;j<rs.size();j++){
				if(tempName.equals(rs.get(j).getTablename())){
					MSGBean bean=new MSGBean();
					bean.setStr(rs.get(j).getStr());
					bean.setValue(rs.get(j).getValue());
					System.out.println(rs.get(j).getStr()+":"+rs.get(j).getValue());
					tempMsgbean.add(bean);
					
				}
				
			}
			json.put("tablename", tempName);
			json.put("body", tempMsgbean);
			root.add(json);
			
			
		}
		
		
	
		JSONArray jsonp = JSONArray.fromObject(root); 
		mysqlop.close();
		System.out.println(jsonp.toString());
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		ServletActionContext.getResponse().getWriter().print(jsonp.toString());
	  }
	  else {
		  ServletActionContext.getResponse().getWriter().print("");
	  }
		return null;
	}

}
