/**
 * WSWSPaymentFacadeSrvProxy.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.fdrj.services.WSWSPaymentFacade;

public interface WSWSPaymentFacadeSrvProxy extends java.rmi.Remote {
    public java.lang.String generateVoucher(java.lang.String objectValue) throws java.rmi.RemoteException, com.fdrj.services.WSWSPaymentFacade.client.WSInvokeException;
    public java.lang.String insertPaymentBill(java.lang.String objectValue) throws java.rmi.RemoteException, com.fdrj.services.WSWSPaymentFacade.client.WSInvokeException;
}
