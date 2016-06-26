package Bean;

public class MSGBean {
// 具体每条信息的 bean  
	
	public String tablename;  // 消息的表名
	public String Str;        //消息的某一条的  字段名  
	public String value;	  // 消息的字段名对应的 值
	public String getStr() {
		return Str;
	}
	public void setStr(String str) {
		Str = str;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
}

//  SQL查询语句  select A.table_name,A.tableline_string,A.tableline_value from  lib_tableinfo A ,(select * from lib_table_i where T_to_sector_name='A' and I_id='3') B where A.table_name=B.table_name;
