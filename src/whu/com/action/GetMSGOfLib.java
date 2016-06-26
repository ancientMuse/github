package whu.com.action;

import java.util.ArrayList;
import java.util.HashMap;



import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import whu.com.MySQLOperation.MySQLOperation;
import Bean.MSGBean;


import com.opensymphony.xwork2.ActionSupport;

public class GetMSGOfLib extends ActionSupport {

	/**
	 * 获取数据库中的模拟消息数据，需要传入 2个参数
	 * 1，部门名称  FromSectorName
	 * 2，部门对应指令 FromI
	 */
	private static final long serialVersionUID = 1L;
	public String FromSectorName;
	public String FromI;
	public String getFromSectorName() {
		return FromSectorName;
	}
	public String getFromI() {
		return FromI;
	}
	public void setFromSectorName(String fromSectorName) {
		FromSectorName = fromSectorName;
	}
	public void setFromI(String fromI) {
		FromI = fromI;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		
        MySQLOperation mysqlop=new MySQLOperation();
        ArrayList root=new ArrayList();
      
		ArrayList<MSGBean> rs=mysqlop.getMSGContentby_FormsectorAndI(FromSectorName, FromI);
		System.out.println("请求数据："+FromSectorName+":"+FromI);
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
