package out.deal.afs.kq002;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.0.16
 * 2018-12-16T11:50:14.685+08:00
 * Generated source version: 3.0.16
 * 
 */
@WebServiceClient(name = "SIO_OA_AFS_OA002Service", 
                  wsdlLocation = "file:/D:/eclipse-myeclipse-code/eclipse-code/zsjcxf/002.wsdl",
                  targetNamespace = "urn:bk2:afs:deal:out") 
public class SIOOAAFSOA002Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("urn:bk2:afs:deal:out", "SIO_OA_AFS_OA002Service");
    public final static QName HTTPSPort = new QName("urn:bk2:afs:deal:out", "HTTPS_Port");
    public final static QName HTTPPort = new QName("urn:bk2:afs:deal:out", "HTTP_Port");
    static {
        URL url = null;
        try {
            url = new URL("file:/D:/eclipse-myeclipse-code/eclipse-code/zsjcxf/002.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(SIOOAAFSOA002Service.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/D:/eclipse-myeclipse-code/eclipse-code/zsjcxf/002.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public SIOOAAFSOA002Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SIOOAAFSOA002Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SIOOAAFSOA002Service() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public SIOOAAFSOA002Service(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public SIOOAAFSOA002Service(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public SIOOAAFSOA002Service(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    

    /**
     *
     * @return
     *     returns SIOOAAFSOA002
     */
    @WebEndpoint(name = "HTTPS_Port")
    public SIOOAAFSOA002 getHTTPSPort() {
        return super.getPort(HTTPSPort, SIOOAAFSOA002.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SIOOAAFSOA002
     */
    @WebEndpoint(name = "HTTPS_Port")
    public SIOOAAFSOA002 getHTTPSPort(WebServiceFeature... features) {
        return super.getPort(HTTPSPort, SIOOAAFSOA002.class, features);
    }
    /**
     *
     * @return
     *     returns SIOOAAFSOA002
     */
    @WebEndpoint(name = "HTTP_Port")
    public SIOOAAFSOA002 getHTTPPort() {
        return super.getPort(HTTPPort, SIOOAAFSOA002.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SIOOAAFSOA002
     */
    @WebEndpoint(name = "HTTP_Port")
    public SIOOAAFSOA002 getHTTPPort(WebServiceFeature... features) {
        return super.getPort(HTTPPort, SIOOAAFSOA002.class, features);
    }

}
