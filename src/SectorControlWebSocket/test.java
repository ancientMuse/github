package SectorControlWebSocket;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 String jsonMessage = "{'语文':'88','数学':'78'}";
		 String value1 = null;
		  try
		  {
		   //将字符串转换成jsonObject对象
		   JSONObject myJsonObject = new JSONObject(jsonMessage);
		   //获取对应的值
		   value1 = myJsonObject.getString("数学");
		   myJsonObject.put("数学", "2333");
		   value1 = myJsonObject.getString("数学");
		
		  }
		  catch (JSONException e)
		  {
		  }
			Date d = new Date();
			// 给定模式
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
			// public final String format(Date date)
			String time = sdf.format(d);
		  
		  System.out.println("value1="+value1+" time="+ time);
	}

}
