/**
 * WSWSBudgetFacadeSrvProxy.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.fdrj.services.WSWSBudgetFacade;

public interface WSWSBudgetFacadeSrvProxy extends java.rmi.Remote {
    public java.lang.String requestBudget(java.lang.String objectValue) throws java.rmi.RemoteException, com.fdrj.services.WSWSBudgetFacade.client.WSInvokeException;
    public java.lang.String getBudget(java.lang.String objectValue) throws java.rmi.RemoteException, com.fdrj.services.WSWSBudgetFacade.client.WSInvokeException;
    public java.lang.String returnBudget(java.lang.String objectValue) throws java.rmi.RemoteException, com.fdrj.services.WSWSBudgetFacade.client.WSInvokeException;
    public java.lang.String getExpenseTypeList(java.lang.String objectValue) throws java.rmi.RemoteException, com.fdrj.services.WSWSBudgetFacade.client.WSInvokeException;
}
