package com.tcss.zc_ut;

public class Message extends IndexOutOfBoundsException{
  public Message(String msg){
	  super("调用006接口出错"+msg);
  }
}
