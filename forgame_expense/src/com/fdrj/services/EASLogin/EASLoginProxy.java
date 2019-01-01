/**
 * EASLoginProxy.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.fdrj.services.EASLogin;

public interface EASLoginProxy extends java.rmi.Remote {
    public com.fdrj.services.EASLogin.client.WSContext login(java.lang.String userName, java.lang.String password, java.lang.String slnName, java.lang.String dcName, java.lang.String language, int dbType, java.lang.String authPattern) throws java.rmi.RemoteException;
    public com.fdrj.services.EASLogin.client.WSContext login(java.lang.String userName, java.lang.String password, java.lang.String slnName, java.lang.String dcName, java.lang.String language, int dbType) throws java.rmi.RemoteException;
    public com.fdrj.services.EASLogin.client.WSContext login(java.lang.String userName, java.lang.String password, java.lang.String slnName, java.lang.String dcName, java.lang.String language, int dbType, java.lang.String authPattern, int isEncodePwd) throws java.rmi.RemoteException;
    public boolean logout(java.lang.String userName, java.lang.String slnName, java.lang.String dcName, java.lang.String language) throws java.rmi.RemoteException;
}
