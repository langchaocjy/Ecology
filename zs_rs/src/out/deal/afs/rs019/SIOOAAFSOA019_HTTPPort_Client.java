
package out.deal.afs.rs019;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.0.16
 * 2018-09-25T10:11:01.983+08:00
 * Generated source version: 3.0.16
 * 
 */
public final class SIOOAAFSOA019_HTTPPort_Client {

    private static final QName SERVICE_NAME = new QName("urn:bk2:afs:deal:out", "SIO_OA_AFS_OA019Service");

    private SIOOAAFSOA019_HTTPPort_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = SIOOAAFSOA019Service.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        SIOOAAFSOA019Service ss = new SIOOAAFSOA019Service(wsdlURL, SERVICE_NAME);
        SIOOAAFSOA019 port = ss.getHTTPPort();  
        
        {
        System.out.println("Invoking sioOAAFSOA019...");
        out.deal.afs.rs019.DTOAAFSOA019EXT _sioOAAFSOA019_mtOAAFSOA019EXT = null;
        out.deal.afs.rs019.DTOAAFSOA019EXTREP _sioOAAFSOA019__return = port.sioOAAFSOA019(_sioOAAFSOA019_mtOAAFSOA019EXT);
        System.out.println("sioOAAFSOA019.result=" + _sioOAAFSOA019__return);


        }

        System.exit(0);
    }

}
