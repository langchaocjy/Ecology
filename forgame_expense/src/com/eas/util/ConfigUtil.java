/**  
 * All rights Reserved, Designed By forgame
 * @Title:  ConfigUtil.java   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: fengguosen@forgame.com
 * @date:   2018年4月23日 下午4:46:00   
 * @version V1.0 
 * 注意：本内容仅限于云游控股有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.eas.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * @author Employee
 *
 */
public class ConfigUtil {
	
	private static Properties props;
    static{
        loadProps();
    }

	synchronized static private void loadProps(){
        props = new Properties();
        InputStream in = null;
        try {
        	/**
        	 * 第一种，通过类加载器进行获取properties文件流
        	 */
            in = ConfigUtil.class.getClassLoader().getResourceAsStream("config.properties");
  			/**
  			 * 第二种，通过类进行获取properties文件流
  			 */
//            in = PropertyUtil.class.getResourceAsStream("/config.properties");
            props.load(in);
        } catch (FileNotFoundException e) {
//            logger.error("config.properties文件未找到");
        	e.printStackTrace();
        } catch (IOException e) {
//            logger.error("出现IOException");
        	e.printStackTrace();
        } finally {
            try {
                if(null != in) {
                    in.close();
                }
            } catch (IOException e) {
//                logger.error("config.properties文件流关闭出现异常");
                e.printStackTrace();
            }
        }
    }
	
	public static String getProperty(String key){
        if(null == props) {
            loadProps();
        }
        return props.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        if(null == props) {
            loadProps();
        }
        return props.getProperty(key, defaultValue);
    }
    
    public static String getEasUrl() {
    	String url = getProperty("eas.service.url","http://192.168.1.118:6888");
    	return url;
    }
    
	/**   
	 * @Title: main   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param args      
	 * @return: void      
	 * @throws   
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = getProperty("eas.service.url");
		System.out.println(url);
		
		String test = getProperty("test1");
		System.out.println(test);
	}
	

}
