/**
 * WSWSBudgetFacadeSrvProxyServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.fdrj.services.WSWSBudgetFacade;

public class WSWSBudgetFacadeSrvProxyServiceLocator extends org.apache.axis.client.Service implements com.fdrj.services.WSWSBudgetFacade.WSWSBudgetFacadeSrvProxyService {

    public WSWSBudgetFacadeSrvProxyServiceLocator() {
    }


    public WSWSBudgetFacadeSrvProxyServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WSWSBudgetFacadeSrvProxyServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WSWSBudgetFacade
    private java.lang.String WSWSBudgetFacade_address = "http://oa.home.forgame.com:6999/ormrpc/services/WSWSBudgetFacade";

    public java.lang.String getWSWSBudgetFacadeAddress() {
        return WSWSBudgetFacade_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WSWSBudgetFacadeWSDDServiceName = "WSWSBudgetFacade";

    public java.lang.String getWSWSBudgetFacadeWSDDServiceName() {
        return WSWSBudgetFacadeWSDDServiceName;
    }

    public void setWSWSBudgetFacadeWSDDServiceName(java.lang.String name) {
        WSWSBudgetFacadeWSDDServiceName = name;
    }

    public com.fdrj.services.WSWSBudgetFacade.WSWSBudgetFacadeSrvProxy getWSWSBudgetFacade() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WSWSBudgetFacade_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWSWSBudgetFacade(endpoint);
    }

    public com.fdrj.services.WSWSBudgetFacade.WSWSBudgetFacadeSrvProxy getWSWSBudgetFacade(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.fdrj.services.WSWSBudgetFacade.WSWSBudgetFacadeSoapBindingStub _stub = new com.fdrj.services.WSWSBudgetFacade.WSWSBudgetFacadeSoapBindingStub(portAddress, this);
            _stub.setPortName(getWSWSBudgetFacadeWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWSWSBudgetFacadeEndpointAddress(java.lang.String address) {
        WSWSBudgetFacade_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.fdrj.services.WSWSBudgetFacade.WSWSBudgetFacadeSrvProxy.class.isAssignableFrom(serviceEndpointInterface)) {
                com.fdrj.services.WSWSBudgetFacade.WSWSBudgetFacadeSoapBindingStub _stub = new com.fdrj.services.WSWSBudgetFacade.WSWSBudgetFacadeSoapBindingStub(new java.net.URL(WSWSBudgetFacade_address), this);
                _stub.setPortName(getWSWSBudgetFacadeWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("WSWSBudgetFacade".equals(inputPortName)) {
            return getWSWSBudgetFacade();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://oa.home.forgame.com:6999/ormrpc/services/WSWSBudgetFacade", "WSWSBudgetFacadeSrvProxyService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://oa.home.forgame.com:6999/ormrpc/services/WSWSBudgetFacade", "WSWSBudgetFacade"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WSWSBudgetFacade".equals(portName)) {
            setWSWSBudgetFacadeEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
