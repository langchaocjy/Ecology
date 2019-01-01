
package out.deal.afs.kq002;

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

    private final static QName _MTOAAFSOA002REP_QNAME = new QName("urn:bk2:afs:deal:out", "MT_OA_AFS_OA002_REP");
    private final static QName _MTOAAFSOA002_QNAME = new QName("urn:bk2:afs:deal:out", "MT_OA_AFS_OA002");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bk2.afs.deal.out
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DTOAAFSOA002REP }
     * 
     */
    public DTOAAFSOA002REP createDTOAAFSOA002REP() {
        return new DTOAAFSOA002REP();
    }

    /**
     * Create an instance of {@link DTOAAFSOA002 }
     * 
     */
    public DTOAAFSOA002 createDTOAAFSOA002() {
        return new DTOAAFSOA002();
    }

    /**
     * Create an instance of {@link DTOAAFSOA002REP.SHEET }
     * 
     */
    public DTOAAFSOA002REP.SHEET createDTOAAFSOA002REPSHEET() {
        return new DTOAAFSOA002REP.SHEET();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTOAAFSOA002REP }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:bk2:afs:deal:out", name = "MT_OA_AFS_OA002_REP")
    public JAXBElement<DTOAAFSOA002REP> createMTOAAFSOA002REP(DTOAAFSOA002REP value) {
        return new JAXBElement<DTOAAFSOA002REP>(_MTOAAFSOA002REP_QNAME, DTOAAFSOA002REP.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTOAAFSOA002 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:bk2:afs:deal:out", name = "MT_OA_AFS_OA002")
    public JAXBElement<DTOAAFSOA002> createMTOAAFSOA002(DTOAAFSOA002 value) {
        return new JAXBElement<DTOAAFSOA002>(_MTOAAFSOA002_QNAME, DTOAAFSOA002 .class, null, value);
    }

}
