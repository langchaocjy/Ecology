
package out.deal.afs.rs017;

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

    private final static QName _MTOAAFSOA017REP_QNAME = new QName("urn:bk2:afs:deal:out", "MT_OA_AFS_OA017_REP");
    private final static QName _MTOAAFSOA017_QNAME = new QName("urn:bk2:afs:deal:out", "MT_OA_AFS_OA017");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bk2.afs.deal.out
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DTOAAFSOA017 }
     * 
     */
    public DTOAAFSOA017 createDTOAAFSOA017() {
        return new DTOAAFSOA017();
    }

    /**
     * Create an instance of {@link DTOAAFSOA017REP }
     * 
     */
    public DTOAAFSOA017REP createDTOAAFSOA017REP() {
        return new DTOAAFSOA017REP();
    }

    /**
     * Create an instance of {@link DTOAAFSOA017 .SHEET }
     * 
     */
    public DTOAAFSOA017 .SHEET createDTOAAFSOA017SHEET() {
        return new DTOAAFSOA017 .SHEET();
    }

    /**
     * Create an instance of {@link DTOAAFSOA017REP.RETURN }
     * 
     */
    public DTOAAFSOA017REP.RETURN createDTOAAFSOA017REPRETURN() {
        return new DTOAAFSOA017REP.RETURN();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTOAAFSOA017REP }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:bk2:afs:deal:out", name = "MT_OA_AFS_OA017_REP")
    public JAXBElement<DTOAAFSOA017REP> createMTOAAFSOA017REP(DTOAAFSOA017REP value) {
        return new JAXBElement<DTOAAFSOA017REP>(_MTOAAFSOA017REP_QNAME, DTOAAFSOA017REP.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTOAAFSOA017 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:bk2:afs:deal:out", name = "MT_OA_AFS_OA017")
    public JAXBElement<DTOAAFSOA017> createMTOAAFSOA017(DTOAAFSOA017 value) {
        return new JAXBElement<DTOAAFSOA017>(_MTOAAFSOA017_QNAME, DTOAAFSOA017 .class, null, value);
    }

}
