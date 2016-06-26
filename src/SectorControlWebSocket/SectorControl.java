package SectorControlWebSocket;

import java.io.IOException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;
import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONException;
import org.json.JSONObject;

import net.sf.json.JSONArray;
/*
 * * 手动上线系统 
 */
@ServerEndpoint(value = "/SectorControl", configurator = GetHttpSessionConfigurator.class)
public class SectorControl {

	private HttpSession httpSession;
	private static HashMap<String, Session> SectorList = new HashMap<String, Session>();
	private static HashMap<String, EndpointConfig> configList = new HashMap<String, EndpointConfig>();
	private static final String[] Sectorcode = {// 部门模块列表
	   "001","002", "003", "004", "005", "006", "007", "008" };
	
	private static String[] ActionType_content=new String[]{"NextSector","Response","Show","online","offline"};
	private static String[] MSG_Content=new String[]{"上线","下线","数据指令传输"};
	private static String ActionType="ActionType";
	private static String ToSector="ToSector";
	private static String FromSector_I="FromSector_I";
	private static String FromSector="FromSector";
	private static String TimeInsertData="TimeInsertData";
	private static String InsertToken="InsertToken";
	private static String[] ResponseMSG=new String[]{
		"指令发送成功！",
		"指令数据发送成功，响应模块未上线，等待响应。。。"
		};
	private static String MSG="MSG";
	//private static String FromSector="FromSector";
	//private static String FromSector_I="FromSector_I";
	private static String JK_code="000";//监控模块代号 000
	
    public String map_sector_00N(String sc){
    	
    	String qs="";
    	if(sc.equals("000"))
    		qs="000";
    	if (sc.equals("001"))
			qs = "A";
		if (sc.equals("002"))
			qs = "B";
		if (sc.equals("003"))
			qs = "C";
		if (sc.equals("004"))
			qs = "D";
		if (sc.equals("005"))
			qs = "E";
		if (sc.equals("006"))
			qs = "F";

		if (sc.equals("007"))
			qs = "G";
		if (sc.equals("008"))
			qs = "H";
		return qs;	
    }
	@OnMessage
	public void onMessage(String message, Session session) throws IOException,
			InterruptedException, JSONException {
	
		String actionType = null;

		// 将字符串转换成jsonObject对象
		System.out.println("接受的 MSG"+message);
		JSONObject myJsonObject = new JSONObject(message);
		//System.out.println("转换成JSON对象后"+message);
		
		//  来自sector:JSON 
		/*
		 * {
		 *    ActionType:"NextSector"  指令类型(1、NextSector,2、Nothing)
		 *    ToSector:""  目标的Sector
		 *    FromSector:"" 源Sector
		 *    FromSector_I:"" 源Sector的指令
		 * 
		 * }
		 * 
		 * *
		 */
		// 获取对应的值
		actionType = myJsonObject.getString(ActionType);
		if(actionType.equals(ActionType_content[0]))
		{
			String toSector=myJsonObject.getString(ToSector);
			if(SectorList.containsKey(JK_code)){
				SectorBean sb = new SectorBean();
				Session Boss_sesson = (Session) SectorList.get(JK_code);
				
				sb.setActionType(ActionType_content[0]);
				sb.setFrom_Sector(myJsonObject.getString(FromSector));
				sb.setTo_sector(myJsonObject.getString(ToSector));
				sb.setI(myJsonObject.getString(FromSector_I));
				sb.setTime_InsertData(myJsonObject.getString(TimeInsertData));
				sb.setInsertToken(myJsonObject.getString(InsertToken));
				//sb.setMSG(MSG_Content[0]);
				JSONArray json = JSONArray.fromObject(sb);
				System.out.println("Bossion 接受："+json);
				Boss_sesson.getBasicRemote().sendText(json.toString());
				
				
			}
		if(SectorList.containsKey(toSector)){	
			Session To_sesson = (Session) SectorList.get(toSector);
			//JSONArray json = JSONArray.fromObject(sb);
			//System.out.println(json);
			//myJsonObject.
			myJsonObject.put(ActionType, ActionType_content[0]);
			myJsonObject.put(ToSector, myJsonObject.getString(ToSector));
			System.out.println("ActionType=" + actionType+" JSON="+myJsonObject);
			if(To_sesson.isOpen()){
			To_sesson.getBasicRemote().sendText(myJsonObject.toString());//目标
			JSONObject resopnJSON=new JSONObject();;
			resopnJSON.put(ActionType, ActionType_content[1]);
			resopnJSON.put(MSG, ResponseMSG[0]);	
			session.getBasicRemote().sendText(resopnJSON.toString());// 反馈
			}
			else {
				JSONObject resopnJSON=new JSONObject();;
				resopnJSON.put(ActionType, ActionType_content[1]);
				resopnJSON.put(MSG, ResponseMSG[1]);
				session.getBasicRemote().sendText(resopnJSON.toString());// 反馈
				
			}
		}
		else{
			JSONObject resopnJSON=new JSONObject();;
			resopnJSON.put(ActionType, ActionType_content[1]);
			resopnJSON.put(MSG, ResponseMSG[1]);
			session.getBasicRemote().sendText(resopnJSON.toString());// 反馈
			
			
		}
			
			
		}
		
		
		
//		EndpointConfig config = (EndpointConfig) configList
//				.get(session.getId());
//		this.httpSession = (HttpSession) config.getUserProperties().get(
//				HttpSession.class.getName());
//		String sector = (String) httpSession.getAttribute("sector");
//		session.getBasicRemote().sendText(sector);
	}

	@OnOpen
	public void onOpen(Session session, EndpointConfig config)
			throws IOException, EncodeException {
		System.out.println("Client connected");

		this.httpSession = (HttpSession) config.getUserProperties().get(
				HttpSession.class.getName());
		String sector = (String) httpSession.getAttribute("sector");
		String logintime=(String)httpSession.getAttribute("loginTime");
		sector=map_sector_00N(sector);
		SectorBean sb = new SectorBean();
		sb.setSector(sector);
		sb.setUsername((String) httpSession.getAttribute("user"));
		sb.setLogintime(logintime);

		System.out.println(sector + "SectorList size:" + SectorList.size());
		if (!sector.equals(JK_code) && SectorList.containsKey(JK_code)) {

			Session Boss_sesson = (Session) SectorList.get(JK_code);
			
			sb.setActionType(ActionType_content[3]);
			sb.setMSG(MSG_Content[0]);
			JSONArray json = JSONArray.fromObject(sb);
			System.out.println(json);
			Boss_sesson.getBasicRemote().sendText(json.toString());
			
			
			
		}
		if (sector.equals(JK_code) && Is_sector_Exist()) {
			Iterator<Entry<String, Session>> iter = SectorList.entrySet()
					.iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				String key = (String) entry.getKey();
				Session val = (Session) entry.getValue();
				SectorBean sb2 = new SectorBean();
				sb2.setSector(key);// sector

				HttpSession hs = (HttpSession) configList.get(val.getId())
						.getUserProperties().get(HttpSession.class.getName());
				;
				sb2.setUsername((String) hs.getAttribute("user"));
				sb2.setLogintime((String) hs.getAttribute("loginTime"));
				sb2.setActionType(ActionType_content[3]);
				sb2.setMSG(MSG_Content[0]);
			
				JSONArray json = JSONArray.fromObject(sb2);
				System.out.println("已经上线的 JSON"+json);
				session.getBasicRemote().sendText(json.toString());
			}

		}

		configList.put(session.getId(), config);
		SectorList.put(sector, session);
	}

	public Boolean Is_sector_Exist() {

		for (int i = 0; i < Sectorcode.length; i++)
			if (SectorList.containsKey(map_sector_00N(Sectorcode[i]))) {
				return true;
			}
		return false;
	}

	@OnClose
	public void onClose(Session session) {
		EndpointConfig config = (EndpointConfig) configList
				.get(session.getId());
		this.httpSession = (HttpSession) config.getUserProperties().get(
				HttpSession.class.getName());
		String sector = (String) httpSession.getAttribute("sector");
		sector=map_sector_00N(sector);
   
		if (!sector.equals(JK_code) && SectorList.containsKey(JK_code)) {
			SectorBean sb = new SectorBean();
			sb.setSector(sector);
			sb.setUsername((String) httpSession.getAttribute("user"));
			sb.setActionType(ActionType_content[4]);
			sb.setMSG(MSG_Content[1]);
			JSONArray json = JSONArray.fromObject(sb);
			Session Boss_sesson = (Session) SectorList.get(JK_code);
			try {
				Boss_sesson.getBasicRemote().sendText(json.toString());
				httpSession.removeAttribute(sector);
				httpSession.invalidate();
			} catch (IOException e) {

				e.printStackTrace();
			}
		} else {

		}
		SectorList.remove(sector);
		configList.remove(session.getId());
		System.out.println(session.getId());

	}

}
