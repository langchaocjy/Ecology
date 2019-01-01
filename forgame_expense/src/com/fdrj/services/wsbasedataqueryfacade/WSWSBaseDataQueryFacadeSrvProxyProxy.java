package com.fdrj.services.wsbasedataqueryfacade;

public class WSWSBaseDataQueryFacadeSrvProxyProxy implements com.fdrj.services.wsbasedataqueryfacade.WSWSBaseDataQueryFacadeSrvProxy {
  private String _endpoint = null;
  private com.fdrj.services.wsbasedataqueryfacade.WSWSBaseDataQueryFacadeSrvProxy wSWSBaseDataQueryFacadeSrvProxy = null;
  
  public WSWSBaseDataQueryFacadeSrvProxyProxy() {
    _initWSWSBaseDataQueryFacadeSrvProxyProxy();
  }
  
  public WSWSBaseDataQueryFacadeSrvProxyProxy(String endpoint) {
    _endpoint = endpoint;
    _initWSWSBaseDataQueryFacadeSrvProxyProxy();
  }
  
  private void _initWSWSBaseDataQueryFacadeSrvProxyProxy() {
    try {
      wSWSBaseDataQueryFacadeSrvProxy = (new com.fdrj.services.wsbasedataqueryfacade.WSWSBaseDataQueryFacadeSrvProxyServiceLocator()).getWSWSBaseDataQueryFacade();
      if (wSWSBaseDataQueryFacadeSrvProxy != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wSWSBaseDataQueryFacadeSrvProxy)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wSWSBaseDataQueryFacadeSrvProxy)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wSWSBaseDataQueryFacadeSrvProxy != null)
      ((javax.xml.rpc.Stub)wSWSBaseDataQueryFacadeSrvProxy)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.fdrj.services.wsbasedataqueryfacade.WSWSBaseDataQueryFacadeSrvProxy getWSWSBaseDataQueryFacadeSrvProxy() {
    if (wSWSBaseDataQueryFacadeSrvProxy == null)
      _initWSWSBaseDataQueryFacadeSrvProxyProxy();
    return wSWSBaseDataQueryFacadeSrvProxy;
  }
  
  public java.lang.String getPersonList(java.lang.String objectValue) throws java.rmi.RemoteException, com.fdrj.services.wsbasedataqueryfacade.client.WSInvokeException{
    if (wSWSBaseDataQueryFacadeSrvProxy == null)
      _initWSWSBaseDataQueryFacadeSrvProxyProxy();
    return wSWSBaseDataQueryFacadeSrvProxy.getPersonList(objectValue);
  }
  
  public java.lang.String getDepartmentList(java.lang.String objectValue) throws java.rmi.RemoteException, com.fdrj.services.wsbasedataqueryfacade.client.WSInvokeException{
    if (wSWSBaseDataQueryFacadeSrvProxy == null)
      _initWSWSBaseDataQueryFacadeSrvProxyProxy();
    return wSWSBaseDataQueryFacadeSrvProxy.getDepartmentList(objectValue);
  }
  
  public java.lang.String getJobList(java.lang.String objectValue) throws java.rmi.RemoteException, com.fdrj.services.wsbasedataqueryfacade.client.WSInvokeException{
    if (wSWSBaseDataQueryFacadeSrvProxy == null)
      _initWSWSBaseDataQueryFacadeSrvProxyProxy();
    return wSWSBaseDataQueryFacadeSrvProxy.getJobList(objectValue);
  }
  
  
}