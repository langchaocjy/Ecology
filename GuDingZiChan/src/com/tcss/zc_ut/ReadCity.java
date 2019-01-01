package com.tcss.zc_ut;

import com.tcss.zc.zc032;

import net.sf.json.JSONArray;
import weaver.interfaces.schedule.BaseCronJob;

public class ReadCity extends BaseCronJob{
     public void execute(){
    	 zc032 zc = new zc032();
    	 JSONArray json = zc.readCity("");
    	 
    	 
     }
}
