package out.deal.afs.rs017;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.0.16
 * 2018-09-25T10:10:00.894+08:00
 * Generated source version: 3.0.16
 * 
 */
@WebService(targetNamespace = "urn:bk2:afs:deal:out", name = "SIO_OA_AFS_OA017")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface SIOOAAFSOA017 {

    @WebMethod(operationName = "SIO_OA_AFS_OA017", action = "http://sap.com/xi/WebService/soap1.1")
    @WebResult(name = "MT_OA_AFS_OA017_REP", targetNamespace = "urn:bk2:afs:deal:out", partName = "MT_OA_AFS_OA017_REP")
    public DTOAAFSOA017REP sioOAAFSOA017(
        @WebParam(partName = "MT_OA_AFS_OA017", name = "MT_OA_AFS_OA017", targetNamespace = "urn:bk2:afs:deal:out")
        DTOAAFSOA017 mtOAAFSOA017
    );
}
