package com.tcss.zc_ut;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.tcss.zc.zc031;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.interfaces.schedule.BaseCronJob;

public class Zclb extends BaseCronJob{
    public void execute(){
    	zc031 zc = new zc031();
    	JSONArray jsons = zc.zplb("tops");
    	new BaseBean().writeLog(jsons);
    	
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
  	    String dat=df.format(new Date());//2018-07-13 16:02
 	    String date=dat.substring(0,10);
 	    String time = dat.substring(11);
    	RecordSet rs = new RecordSet();
    	rs.executeSql("delete from uf_zclb where id > 0");
    	if(jsons.size() > 0){
    		for(int i = 0; i < jsons.size(); i++){
    			JSONObject json = jsons.getJSONObject(i);
    			String afapl = json.getString("AFAPL");
    			String anlkl = json.getString("ANLKL");
    			String txk20 = json.getString("TXK20");
    			String zkeds = json.getString("ZKEDS");
    			rs.executeSql("insert into uf_zclb "
    					+ "(zjb,zclbbm,zclbms,msjbm,modedatacreater,modedatacreatedate,modedatacreatetime,formmodeid,modedatacreatertype) "
    					+ "values ('"+afapl+"','"+anlkl+"','"+txk20+"','"+zkeds+"','1','"+date+"','"+time+"','402','0')");
    		}
    	}
    }
}
