package out.deal.afs.kq001;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.0.16
 * 2018-12-16T11:49:33.365+08:00
 * Generated source version: 3.0.16
 * 
 */
@WebService(targetNamespace = "urn:bk2:afs:deal:out", name = "SIO_OA_AFS_OA001")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface SIOOAAFSOA001 {

    @WebMethod(operationName = "SIO_OA_AFS_OA001", action = "http://sap.com/xi/WebService/soap1.1")
    @WebResult(name = "MT_OA_AFS_OA001_REP", targetNamespace = "urn:bk2:afs:deal:out", partName = "MT_OA_AFS_OA001_REP")
    public DTOAAFSOA001REP sioOAAFSOA001(
        @WebParam(partName = "MT_OA_AFS_OA001", name = "MT_OA_AFS_OA001", targetNamespace = "urn:bk2:afs:deal:out")
        DTOAAFSOA001 mtOAAFSOA001
    );
}
