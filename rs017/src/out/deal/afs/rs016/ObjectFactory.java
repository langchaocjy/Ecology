
package out.deal.afs.rs016;

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

    private final static QName _MTOAAFSOA016REP_QNAME = new QName("urn:bk2:afs:deal:out", "MT_OA_AFS_OA016_REP");
    private final static QName _MTOAAFSOA016_QNAME = new QName("urn:bk2:afs:deal:out", "MT_OA_AFS_OA016");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bk2.afs.deal.out
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DTOAAFSOA016 }
     * 
     */
    public DTOAAFSOA016 createDTOAAFSOA016() {
        return new DTOAAFSOA016();
    }

    /**
     * Create an instance of {@link DTOAAFSOA016REP }
     * 
     */
    public DTOAAFSOA016REP createDTOAAFSOA016REP() {
        return new DTOAAFSOA016REP();
    }

    /**
     * Create an instance of {@link DTOAAFSOA016 .SHEET }
     * 
     */
    public DTOAAFSOA016 .SHEET createDTOAAFSOA016SHEET() {
        return new DTOAAFSOA016 .SHEET();
    }

    /**
     * Create an instance of {@link DTOAAFSOA016REP.RETURN }
     * 
     */
    public DTOAAFSOA016REP.RETURN createDTOAAFSOA016REPRETURN() {
        return new DTOAAFSOA016REP.RETURN();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTOAAFSOA016REP }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:bk2:afs:deal:out", name = "MT_OA_AFS_OA016_REP")
    public JAXBElement<DTOAAFSOA016REP> createMTOAAFSOA016REP(DTOAAFSOA016REP value) {
        return new JAXBElement<DTOAAFSOA016REP>(_MTOAAFSOA016REP_QNAME, DTOAAFSOA016REP.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTOAAFSOA016 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:bk2:afs:deal:out", name = "MT_OA_AFS_OA016")
    public JAXBElement<DTOAAFSOA016> createMTOAAFSOA016(DTOAAFSOA016 value) {
        return new JAXBElement<DTOAAFSOA016>(_MTOAAFSOA016_QNAME, DTOAAFSOA016 .class, null, value);
    }

}
