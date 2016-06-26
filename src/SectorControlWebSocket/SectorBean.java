package SectorControlWebSocket;

public class SectorBean {
	private String username;
	private String sector;
	private String ActionType;//1.online  上线 
	                          //2.offline 下线
	                          //3.NextSector 发送消息
	private String MSG;
	private String logintime;
	
	private String From_Sector;
	private String To_sector;
	private String I;
	private String Time_InsertData;
	private String InsertToken;
	public String getReturn_I() {
		return return_I;
	}
	public void setReturn_I(String return_I) {
		this.return_I = return_I;
	}
	private String return_I;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getActionType() {
		return ActionType;
	}
	public void setActionType(String actionType) {
		ActionType = actionType;
	}
	public String getMSG() {
		return MSG;
	}
	public void setMSG(String mSG) {
		MSG = mSG;
	}
	public String getLogintime() {
		return logintime;
	}
	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}
	public String getFrom_Sector() {
		return From_Sector;
	}
	public void setFrom_Sector(String from_Sector) {
		From_Sector = from_Sector;
	}
	public String getTo_sector() {
		return To_sector;
	}
	public void setTo_sector(String to_sector) {
		To_sector = to_sector;
	}
	public String getI() {
		return I;
	}
	public void setI(String i) {
		I = i;
	}
	public String getTime_InsertData() {
		return Time_InsertData;
	}
	public void setTime_InsertData(String time_InsertData) {
		Time_InsertData = time_InsertData;
	}
	public String getInsertToken() {
		return InsertToken;
	}
	public void setInsertToken(String insertToken) {
		InsertToken = insertToken;
	}
	

}
