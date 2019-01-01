package com.tcss.zc_ut;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.tcss.zc.zc025;

import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.interfaces.schedule.BaseCronJob;

public class ReadCard extends BaseBean{
     public void write(String gsbm ,String zcbm){
    	 zc025 zc = new zc025();
    	 JSONObject json = zc.readCard(gsbm , zcbm);
    	 writeLog(json);
    	 ReadCard.seekSql(json);
     }
     
     private static void seekSql(JSONObject json){
         RecordSet rs = new RecordSet();
         if(json.size()>0){
    		 String insert_sql = "insert into uf_gdzckp "
 					+ "(zchrq,zclx,zcbh,gsdm,ywfw,zzph,cbzx,danwei,yjsyqx,chengshi,zjfs,syr,syzczjny,zcms,ysyqx,zcyz,zcjz,modedatacreater,modedatacreatedate,modedatacreatetime,formmodeid,modedatacreatertype) "
 					+ "values ('"+json.getString("aktiv")+"','"+json.getString("anlkl")+"','"+json.getString("anln1")+"','"+json.getString("bukrs")+"',"
 				    + "'"+json.getString("gsber")+"','"+json.getString("kfzkz")+"','"+json.getString("kostl")+"','"+json.getString("meins")+"',"
 				    + "'"+json.getString("ndurj")+"','"+json.getString("ord42")+"','"+json.getString("ord44")+"',"
 				    + "'"+json.getString("pernr")+"','"+json.getString("symon")+"','"+json.getString("txt50")+"','"+json.getString("ysymon")+"',"
 				    + "'"+json.getString("answl")+"','"+json.getString("zzcjz")+"','1',"
 					+ "'"+new SimpleDateFormat("yyyyMMdd").format(new Date())+"',"
 					+ "'"+new SimpleDateFormat("hhmm").format(new Date())+"','403','0')";
    		 rs.executeSql(insert_sql);
         }else{
        	 new BaseBean().writeLog("json没有数据");
         }
     }
}
