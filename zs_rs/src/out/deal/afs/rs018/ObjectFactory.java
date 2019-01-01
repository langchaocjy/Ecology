
package out.deal.afs.rs018;

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

    private final static QName _MTOAAFSOA018EXTREP_QNAME = new QName("urn:bk2:afs:deal:out", "MT_OA_AFS_OA018_EXT_REP");
    private final static QName _MTOAAFSOA018EXT_QNAME = new QName("urn:bk2:afs:deal:out", "MT_OA_AFS_OA018_EXT");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bk2.afs.deal.out
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DTOAAFSOA018EXT }
     * 
     */
    public DTOAAFSOA018EXT createDTOAAFSOA018EXT() {
        return new DTOAAFSOA018EXT();
    }

    /**
     * Create an instance of {@link DTOAAFSOA018EXTREP }
     * 
     */
    public DTOAAFSOA018EXTREP createDTOAAFSOA018EXTREP() {
        return new DTOAAFSOA018EXTREP();
    }

    /**
     * Create an instance of {@link DTOAAFSOA018EXT.SHEET }
     * 
     */
    public DTOAAFSOA018EXT.SHEET createDTOAAFSOA018EXTSHEET() {
        return new DTOAAFSOA018EXT.SHEET();
    }

    /**
     * Create an instance of {@link DTOAAFSOA018EXTREP.SHEET }
     * 
     */
    public DTOAAFSOA018EXTREP.SHEET createDTOAAFSOA018EXTREPSHEET() {
        return new DTOAAFSOA018EXTREP.SHEET();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTOAAFSOA018EXTREP }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:bk2:afs:deal:out", name = "MT_OA_AFS_OA018_EXT_REP")
    public JAXBElement<DTOAAFSOA018EXTREP> createMTOAAFSOA018EXTREP(DTOAAFSOA018EXTREP value) {
        return new JAXBElement<DTOAAFSOA018EXTREP>(_MTOAAFSOA018EXTREP_QNAME, DTOAAFSOA018EXTREP.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTOAAFSOA018EXT }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:bk2:afs:deal:out", name = "MT_OA_AFS_OA018_EXT")
    public JAXBElement<DTOAAFSOA018EXT> createMTOAAFSOA018EXT(DTOAAFSOA018EXT value) {
        return new JAXBElement<DTOAAFSOA018EXT>(_MTOAAFSOA018EXT_QNAME, DTOAAFSOA018EXT.class, null, value);
    }

}
