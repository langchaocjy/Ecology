
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package out.deal.afs.rs017;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.0.16
 * 2018-09-25T10:10:00.865+08:00
 * Generated source version: 3.0.16
 * 
 */

@javax.jws.WebService(
                      serviceName = "SIO_OA_AFS_OA017Service",
                      portName = "HTTPS_Port",
                      targetNamespace = "urn:bk2:afs:deal:out",
                      wsdlLocation = "file:/D:/eclipse-myeclipse-code/eclipse-code/zs_rs/rs017.wsdl",
                      endpointInterface = "bk2.afs.deal.out.SIOOAAFSOA017")
                      
public class HTTPS_PortImpl implements SIOOAAFSOA017 {

    private static final Logger LOG = Logger.getLogger(HTTPS_PortImpl.class.getName());

    /* (non-Javadoc)
     * @see bk2.afs.deal.out.SIOOAAFSOA017#sioOAAFSOA017(bk2.afs.deal.out.DTOAAFSOA017 mtOAAFSOA017)*
     */
    public out.deal.afs.rs017.DTOAAFSOA017REP sioOAAFSOA017(DTOAAFSOA017 mtOAAFSOA017) { 
        LOG.info("Executing operation sioOAAFSOA017");
        System.out.println(mtOAAFSOA017);
        try {
            out.deal.afs.rs017.DTOAAFSOA017REP _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
