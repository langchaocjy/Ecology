package com.fdrj;

import net.sf.json.JSONObject;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;

public class getHl extends BaseBean{
	public static JSONObject gethl(String mx_fssj,String zz_skbb,String mx_djbb){
		JSONObject json = new JSONObject();
		RecordSet rs = new RecordSet();
		String sql = "select skbbhl,rmbhl from uf_fybxdbbhlb where hlrq='"+mx_fssj+"' and skbb='"+zz_skbb+"' and djyb = '"+mx_djbb+"'";
		rs.execute(sql);
		String rmbhl = null,skhl = null;
		if(rs.next())
		{
			rmbhl = rs.getString("rmbhl");
			skhl = rs.getString("skbbhl");
			json.put("rmbhl", rmbhl);
			json.put("skhl", skhl);
		}
		return json;
	}
}
