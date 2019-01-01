
package out.deal.afs.kq001;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bk2.afs.deal.out package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _MTOAAFSOA001_QNAME = new QName("urn:bk2:afs:deal:out", "MT_OA_AFS_OA001");
    private final static QName _MTOAAFSOA001REP_QNAME = new QName("urn:bk2:afs:deal:out", "MT_OA_AFS_OA001_REP");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bk2.afs.deal.out
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DTOAAFSOA001REP }
     * 
     */
    public DTOAAFSOA001REP createDTOAAFSOA001REP() {
        return new DTOAAFSOA001REP();
    }

    /**
     * Create an instance of {@link DTOAAFSOA001REP.SHEET }
     * 
     */
    public DTOAAFSOA001REP.SHEET createDTOAAFSOA001REPSHEET() {
        return new DTOAAFSOA001REP.SHEET();
    }

    /**
     * Create an instance of {@link DTOAAFSOA001 }
     * 
     */
    public DTOAAFSOA001 createDTOAAFSOA001() {
        return new DTOAAFSOA001();
    }

    /**
     * Create an instance of {@link DTOAAFSOA001REP.SHEET.Hrmsubcompany }
     * 
     */
    public DTOAAFSOA001REP.SHEET.Hrmsubcompany createDTOAAFSOA001REPSHEETHrmsubcompany() {
        return new DTOAAFSOA001REP.SHEET.Hrmsubcompany();
    }

    /**
     * Create an instance of {@link DTOAAFSOA001REP.SHEET.Hrmdepartment }
     * 
     */
    public DTOAAFSOA001REP.SHEET.Hrmdepartment createDTOAAFSOA001REPSHEETHrmdepartment() {
        return new DTOAAFSOA001REP.SHEET.Hrmdepartment();
    }

    /**
     * Create an instance of {@link DTOAAFSOA001REP.SHEET.Hrmjobtitles }
     * 
     */
    public DTOAAFSOA001REP.SHEET.Hrmjobtitles createDTOAAFSOA001REPSHEETHrmjobtitles() {
        return new DTOAAFSOA001REP.SHEET.Hrmjobtitles();
    }

    /**
     * Create an instance of {@link DTOAAFSOA001REP.SHEET.FormtableMain30 }
     * 
     */
    public DTOAAFSOA001REP.SHEET.FormtableMain30 createDTOAAFSOA001REPSHEETFormtableMain30() {
        return new DTOAAFSOA001REP.SHEET.FormtableMain30();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTOAAFSOA001 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:bk2:afs:deal:out", name = "MT_OA_AFS_OA001")
    public JAXBElement<DTOAAFSOA001> createMTOAAFSOA001(DTOAAFSOA001 value) {
        return new JAXBElement<DTOAAFSOA001>(_MTOAAFSOA001_QNAME, DTOAAFSOA001 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTOAAFSOA001REP }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:bk2:afs:deal:out", name = "MT_OA_AFS_OA001_REP")
    public JAXBElement<DTOAAFSOA001REP> createMTOAAFSOA001REP(DTOAAFSOA001REP value) {
        return new JAXBElement<DTOAAFSOA001REP>(_MTOAAFSOA001REP_QNAME, DTOAAFSOA001REP.class, null, value);
    }

}
