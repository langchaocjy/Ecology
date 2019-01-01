
package out.deal.afs.rs018;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 部门参考岗位&空岗
 * 
 * <p>DT_OA_AFS_OA018_EXT complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="DT_OA_AFS_OA018_EXT"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SHEET" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="XQBM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="SQRQ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DT_OA_AFS_OA018_EXT", propOrder = {
    "sheet"
})
public class DTOAAFSOA018EXT {

    @XmlElement(name = "SHEET")
    protected DTOAAFSOA018EXT.SHEET sheet;

    /**
     * 获取sheet属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DTOAAFSOA018EXT.SHEET }
     *     
     */
    public DTOAAFSOA018EXT.SHEET getSHEET() {
        return sheet;
    }

    /**
     * 设置sheet属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DTOAAFSOA018EXT.SHEET }
     *     
     */
    public void setSHEET(DTOAAFSOA018EXT.SHEET value) {
        this.sheet = value;
    }


    /**
     * <p>anonymous complex type的 Java 类。
     * 
     * <p>以下模式片段指定包含在此类中的预期内容。
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="XQBM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="SQRQ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "xqbm",
        "sqrq"
    })
    public static class SHEET {

        @XmlElement(name = "XQBM")
        protected String xqbm;
        @XmlElement(name = "SQRQ")
        protected String sqrq;

        /**
         * 获取xqbm属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getXQBM() {
            return xqbm;
        }

        /**
         * 设置xqbm属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setXQBM(String value) {
            this.xqbm = value;
        }

        /**
         * 获取sqrq属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSQRQ() {
            return sqrq;
        }

        /**
         * 设置sqrq属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSQRQ(String value) {
            this.sqrq = value;
        }

    }

}
