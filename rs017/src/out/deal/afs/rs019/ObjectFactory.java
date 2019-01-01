
package out.deal.afs.rs019;

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

    private final static QName _MTOAAFSOA019EXTREP_QNAME = new QName("urn:bk2:afs:deal:out", "MT_OA_AFS_OA019_EXT_REP");
    private final static QName _MTOAAFSOA019EXT_QNAME = new QName("urn:bk2:afs:deal:out", "MT_OA_AFS_OA019_EXT");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bk2.afs.deal.out
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DTOAAFSOA019EXT }
     * 
     */
    public DTOAAFSOA019EXT createDTOAAFSOA019EXT() {
        return new DTOAAFSOA019EXT();
    }

    /**
     * Create an instance of {@link DTOAAFSOA019EXTREP }
     * 
     */
    public DTOAAFSOA019EXTREP createDTOAAFSOA019EXTREP() {
        return new DTOAAFSOA019EXTREP();
    }

    /**
     * Create an instance of {@link DTOAAFSOA019EXT.SHEET }
     * 
     */
    public DTOAAFSOA019EXT.SHEET createDTOAAFSOA019EXTSHEET() {
        return new DTOAAFSOA019EXT.SHEET();
    }

    /**
     * Create an instance of {@link DTOAAFSOA019EXTREP.SHEET }
     * 
     */
    public DTOAAFSOA019EXTREP.SHEET createDTOAAFSOA019EXTREPSHEET() {
        return new DTOAAFSOA019EXTREP.SHEET();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTOAAFSOA019EXTREP }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:bk2:afs:deal:out", name = "MT_OA_AFS_OA019_EXT_REP")
    public JAXBElement<DTOAAFSOA019EXTREP> createMTOAAFSOA019EXTREP(DTOAAFSOA019EXTREP value) {
        return new JAXBElement<DTOAAFSOA019EXTREP>(_MTOAAFSOA019EXTREP_QNAME, DTOAAFSOA019EXTREP.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTOAAFSOA019EXT }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:bk2:afs:deal:out", name = "MT_OA_AFS_OA019_EXT")
    public JAXBElement<DTOAAFSOA019EXT> createMTOAAFSOA019EXT(DTOAAFSOA019EXT value) {
        return new JAXBElement<DTOAAFSOA019EXT>(_MTOAAFSOA019EXT_QNAME, DTOAAFSOA019EXT.class, null, value);
    }

}
