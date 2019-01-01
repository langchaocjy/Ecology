package com.email.SingleLogin;
import turbomail.api.remote.Env;
import turbomail.api.remote.action.R_Action;
import turbomail.api.remote.user.R_UserAccount;
import turbomail.util.UserAccount;

public class SingleLogin extends Env{
	static String domain="saliai.com";
	static int iTimeout=60;
	static String Email_url="http://mail.saliai.com/mailmain";
	static String user = "admin";
	static String pass = "admin";
    public static String Check(String loginid){
        Env env = Env.getEnv(Email_url,user,pass);
        String username = loginid;
        String password = SingleLogin.getPassword(env, username, domain);
        String url = null;
        if (password != null) {
        	  int sore = SingleLogin.Auth(env, username, password);
              StringBuffer sbRet = new StringBuffer();
              if (sore == 0) {
              	 int success = SingleLogin.getLoginSid(env, username, domain, password, iTimeout, sbRet);
              	 if (success==0) {
      				url="http://mail.saliai.com/mailmain?type=login&sid="+sbRet+"";
      			 }
      		  }else{
      			    url="-1";
      		  }
		}else{
			url = "-2";
		}
   	 return url;
   	}
    public static int getNewMsgNum(String loginid){
    	Env env = Env.getEnv(Email_url,user,pass);
    	R_UserAccount rua = new R_UserAccount();
    	rua.m_useraccount= new UserAccount();
    	UserAccount ua = rua.m_useraccount;
    	ua.username=loginid;
    	ua.m_domain=domain;
    	int iRet = R_UserAccount.getNewMsgNum(env, rua);
		return iRet;
    }
	public static int Auth(Env env,String username,String password){
		int iRet = R_UserAccount.auth(env, username,domain , password);
		return iRet;
	}
	public static String getPassword(Env env,String username,String domain){
		String password = R_UserAccount.getPassword(env, username, domain);
		return password;
	}
    public static int getLoginSid(Env env,String username,String domain,String password,int iTimeout,StringBuffer sbRet){
    	int success = R_Action.getLoginSid(env, username, domain, iTimeout, sbRet);
		return success;
    }
    

}
