package out.deal.afs.rs019;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.0.16
 * 2018-07-11T16:12:17.073+08:00
 * Generated source version: 3.0.16
 * 
 */
@WebService(targetNamespace = "urn:bk2:afs:deal:out", name = "SIO_OA_AFS_OA019")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface SIOOAAFSOA019 {

    @WebMethod(operationName = "SIO_OA_AFS_OA019", action = "http://sap.com/xi/WebService/soap1.1")
    @WebResult(name = "MT_OA_AFS_OA019_EXT_REP", targetNamespace = "urn:bk2:afs:deal:out", partName = "MT_OA_AFS_OA019_EXT_REP")
    public DTOAAFSOA019EXTREP sioOAAFSOA019(
        @WebParam(partName = "MT_OA_AFS_OA019_EXT", name = "MT_OA_AFS_OA019_EXT", targetNamespace = "urn:bk2:afs:deal:out")
        DTOAAFSOA019EXT mtOAAFSOA019EXT
    );
}
