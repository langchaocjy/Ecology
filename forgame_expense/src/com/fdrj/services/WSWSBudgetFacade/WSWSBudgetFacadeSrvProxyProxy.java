package com.fdrj.services.WSWSBudgetFacade;

public class WSWSBudgetFacadeSrvProxyProxy implements com.fdrj.services.WSWSBudgetFacade.WSWSBudgetFacadeSrvProxy {
  private String _endpoint = null;
  private com.fdrj.services.WSWSBudgetFacade.WSWSBudgetFacadeSrvProxy wSWSBudgetFacadeSrvProxy = null;
  
  public WSWSBudgetFacadeSrvProxyProxy() {
    _initWSWSBudgetFacadeSrvProxyProxy();
  }
  
  public WSWSBudgetFacadeSrvProxyProxy(String endpoint) {
    _endpoint = endpoint;
    _initWSWSBudgetFacadeSrvProxyProxy();
  }
  
  private void _initWSWSBudgetFacadeSrvProxyProxy() {
    try {
      wSWSBudgetFacadeSrvProxy = (new com.fdrj.services.WSWSBudgetFacade.WSWSBudgetFacadeSrvProxyServiceLocator()).getWSWSBudgetFacade();
      if (wSWSBudgetFacadeSrvProxy != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wSWSBudgetFacadeSrvProxy)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wSWSBudgetFacadeSrvProxy)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wSWSBudgetFacadeSrvProxy != null)
      ((javax.xml.rpc.Stub)wSWSBudgetFacadeSrvProxy)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.fdrj.services.WSWSBudgetFacade.WSWSBudgetFacadeSrvProxy getWSWSBudgetFacadeSrvProxy() {
    if (wSWSBudgetFacadeSrvProxy == null)
      _initWSWSBudgetFacadeSrvProxyProxy();
    return wSWSBudgetFacadeSrvProxy;
  }
  
  public java.lang.String requestBudget(java.lang.String objectValue) throws java.rmi.RemoteException, com.fdrj.services.WSWSBudgetFacade.client.WSInvokeException{
    if (wSWSBudgetFacadeSrvProxy == null)
      _initWSWSBudgetFacadeSrvProxyProxy();
    return wSWSBudgetFacadeSrvProxy.requestBudget(objectValue);
  }
  
  public java.lang.String getBudget(java.lang.String objectValue) throws java.rmi.RemoteException, com.fdrj.services.WSWSBudgetFacade.client.WSInvokeException{
    if (wSWSBudgetFacadeSrvProxy == null)
      _initWSWSBudgetFacadeSrvProxyProxy();
    return wSWSBudgetFacadeSrvProxy.getBudget(objectValue);
  }
  
  public java.lang.String returnBudget(java.lang.String objectValue) throws java.rmi.RemoteException, com.fdrj.services.WSWSBudgetFacade.client.WSInvokeException{
    if (wSWSBudgetFacadeSrvProxy == null)
      _initWSWSBudgetFacadeSrvProxyProxy();
    return wSWSBudgetFacadeSrvProxy.returnBudget(objectValue);
  }
  
  public java.lang.String getExpenseTypeList(java.lang.String objectValue) throws java.rmi.RemoteException, com.fdrj.services.WSWSBudgetFacade.client.WSInvokeException{
    if (wSWSBudgetFacadeSrvProxy == null)
      _initWSWSBudgetFacadeSrvProxyProxy();
    return wSWSBudgetFacadeSrvProxy.getExpenseTypeList(objectValue);
  }
  
  
}