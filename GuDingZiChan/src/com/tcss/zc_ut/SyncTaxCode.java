package com.tcss.zc_ut;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.tcss.zc.zc039;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.interfaces.schedule.BaseCronJob;

public class SyncTaxCode extends BaseCronJob{
      public void execute(){
    	  zc039 zc = new zc039();
    	  JSONArray jsons = zc.readTaxCode("");
    	  
    	  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    	  String dat=df.format(new Date());//2018-07-13 16:02
   	      String date=dat.substring(0,10);
   	      String time = dat.substring(11);
   	      
   	      RecordSet rs = new RecordSet();
   	      if(jsons.size() > 0){
   	    	  for (int i = 0; i <jsons.size(); i++) {
				JSONObject json = jsons.getJSONObject(i);
				String mwskz = json.getString("mwskz");
				String mwart = json.getString("mwart");
				String kbetr = json.getString("kbetr");
				String text1 = json.getString("text1");
				
				String sql1 = "select * from uf_shuima where sm = '"+mwskz+"'";
				rs.executeSql(sql1);
				if (rs.next()) {
					String sql2 = "update uf_shuima set slx = '"+mwart+"',sms = '"+text1+"',"
							+ "shuilv='"+kbetr+"' where sm = '"+mwskz+"'";
					rs.executeSql(sql2);
				} else {
                    String sql3 ="insert into uf_shuima "
        					+ "(sm,slx,sms,shuilv,"
        					+ "modedatacreater,modedatacreatedate,modedatacreatetime,formmodeid,modedatacreatertype) "
        					+ "values ('"+mwskz+"','"+mwart+"','"+text1+"','"+kbetr+"','1','"+date+"','"+time+"','422','0')";
                    rs.executeSql(sql3);
				}
			}
   	      }else{
   	    	  new BaseBean().writeLog("039接口没有返回json数据");
   	      }
      }
}
