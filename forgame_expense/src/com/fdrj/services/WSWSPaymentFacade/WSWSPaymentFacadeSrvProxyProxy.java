package com.fdrj.services.WSWSPaymentFacade;

public class WSWSPaymentFacadeSrvProxyProxy implements com.fdrj.services.WSWSPaymentFacade.WSWSPaymentFacadeSrvProxy {
  private String _endpoint = null;
  private com.fdrj.services.WSWSPaymentFacade.WSWSPaymentFacadeSrvProxy wSWSPaymentFacadeSrvProxy = null;
  
  public WSWSPaymentFacadeSrvProxyProxy() {
    _initWSWSPaymentFacadeSrvProxyProxy();
  }
  
  public WSWSPaymentFacadeSrvProxyProxy(String endpoint) {
    _endpoint = endpoint;
    _initWSWSPaymentFacadeSrvProxyProxy();
  }
  
  private void _initWSWSPaymentFacadeSrvProxyProxy() {
    try {
      wSWSPaymentFacadeSrvProxy = (new com.fdrj.services.WSWSPaymentFacade.WSWSPaymentFacadeSrvProxyServiceLocator()).getWSWSPaymentFacade();
      if (wSWSPaymentFacadeSrvProxy != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wSWSPaymentFacadeSrvProxy)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wSWSPaymentFacadeSrvProxy)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wSWSPaymentFacadeSrvProxy != null)
      ((javax.xml.rpc.Stub)wSWSPaymentFacadeSrvProxy)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.fdrj.services.WSWSPaymentFacade.WSWSPaymentFacadeSrvProxy getWSWSPaymentFacadeSrvProxy() {
    if (wSWSPaymentFacadeSrvProxy == null)
      _initWSWSPaymentFacadeSrvProxyProxy();
    return wSWSPaymentFacadeSrvProxy;
  }
  
  public java.lang.String generateVoucher(java.lang.String objectValue) throws java.rmi.RemoteException, com.fdrj.services.WSWSPaymentFacade.client.WSInvokeException{
    if (wSWSPaymentFacadeSrvProxy == null)
      _initWSWSPaymentFacadeSrvProxyProxy();
    return wSWSPaymentFacadeSrvProxy.generateVoucher(objectValue);
  }
  
  public java.lang.String insertPaymentBill(java.lang.String objectValue) throws java.rmi.RemoteException, com.fdrj.services.WSWSPaymentFacade.client.WSInvokeException{
    if (wSWSPaymentFacadeSrvProxy == null)
      _initWSWSPaymentFacadeSrvProxyProxy();
    return wSWSPaymentFacadeSrvProxy.insertPaymentBill(objectValue);
  }
  
  
}