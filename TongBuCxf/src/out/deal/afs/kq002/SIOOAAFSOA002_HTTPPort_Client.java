
package out.deal.afs.kq002;

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
 * 2018-12-11T11:58:04.371+08:00
 * Generated source version: 3.0.16
 * 
 */
public final class SIOOAAFSOA002_HTTPPort_Client {

    private static final QName SERVICE_NAME = new QName("urn:bk2:afs:deal:out", "SIO_OA_AFS_OA002Service");

    private SIOOAAFSOA002_HTTPPort_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = SIOOAAFSOA002Service.WSDL_LOCATION;
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
      
        SIOOAAFSOA002Service ss = new SIOOAAFSOA002Service(wsdlURL, SERVICE_NAME);
        SIOOAAFSOA002 port = ss.getHTTPPort();  
        
        {
        System.out.println("Invoking sioOAAFSOA002...");
        out.deal.afs.kq002.DTOAAFSOA002 _sioOAAFSOA002_mtOAAFSOA002 = null;
        out.deal.afs.kq002.DTOAAFSOA002REP _sioOAAFSOA002__return = port.sioOAAFSOA002(_sioOAAFSOA002_mtOAAFSOA002);
        System.out.println("sioOAAFSOA002.result=" + _sioOAAFSOA002__return);


        }

        System.exit(0);
    }

}
