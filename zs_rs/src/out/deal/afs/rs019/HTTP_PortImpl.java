
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package out.deal.afs.rs019;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.0.16
 * 2018-09-25T10:11:02.014+08:00
 * Generated source version: 3.0.16
 * 
 */

@javax.jws.WebService(
                      serviceName = "SIO_OA_AFS_OA019Service",
                      portName = "HTTP_Port",
                      targetNamespace = "urn:bk2:afs:deal:out",
                      wsdlLocation = "file:/D:/eclipse-myeclipse-code/eclipse-code/zs_rs/rs019.wsdl",
                      endpointInterface = "bk2.afs.deal.out.SIOOAAFSOA019")
                      
public class HTTP_PortImpl implements SIOOAAFSOA019 {

    private static final Logger LOG = Logger.getLogger(HTTP_PortImpl.class.getName());

    /* (non-Javadoc)
     * @see bk2.afs.deal.out.SIOOAAFSOA019#sioOAAFSOA019(bk2.afs.deal.out.DTOAAFSOA019EXT mtOAAFSOA019EXT)*
     */
    public out.deal.afs.rs019.DTOAAFSOA019EXTREP sioOAAFSOA019(DTOAAFSOA019EXT mtOAAFSOA019EXT) { 
        LOG.info("Executing operation sioOAAFSOA019");
        System.out.println(mtOAAFSOA019EXT);
        try {
            out.deal.afs.rs019.DTOAAFSOA019EXTREP _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
