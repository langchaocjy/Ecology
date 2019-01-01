
package out.deal.afs.rs019;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 创建岗位-接口
 * 
 * <p>DT_OA_AFS_OA019_EXT complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="DT_OA_AFS_OA019_EXT"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SHEET" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="XQBM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="GWSXRQ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="ZPGW" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="HXZZ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="RZTJ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
@XmlType(name = "DT_OA_AFS_OA019_EXT", propOrder = {
    "sheet"
})
public class DTOAAFSOA019EXT {

    @XmlElement(name = "SHEET")
    protected DTOAAFSOA019EXT.SHEET sheet;

    /**
     * 获取sheet属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DTOAAFSOA019EXT.SHEET }
     *     
     */
    public DTOAAFSOA019EXT.SHEET getSHEET() {
        return sheet;
    }

    /**
     * 设置sheet属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DTOAAFSOA019EXT.SHEET }
     *     
     */
    public void setSHEET(DTOAAFSOA019EXT.SHEET value) {
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
     *         &lt;element name="GWSXRQ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="ZPGW" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="HXZZ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="RZTJ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
        "gwsxrq",
        "zpgw",
        "hxzz",
        "rztj"
    })
    public static class SHEET {

        @XmlElement(name = "XQBM")
        protected String xqbm;
        @XmlElement(name = "GWSXRQ")
        protected String gwsxrq;
        @XmlElement(name = "ZPGW")
        protected String zpgw;
        @XmlElement(name = "HXZZ")
        protected String hxzz;
        @XmlElement(name = "RZTJ")
        protected String rztj;

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
         * 获取gwsxrq属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getGWSXRQ() {
            return gwsxrq;
        }

        /**
         * 设置gwsxrq属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setGWSXRQ(String value) {
            this.gwsxrq = value;
        }

        /**
         * 获取zpgw属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getZPGW() {
            return zpgw;
        }

        /**
         * 设置zpgw属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setZPGW(String value) {
            this.zpgw = value;
        }

        /**
         * 获取hxzz属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getHXZZ() {
            return hxzz;
        }

        /**
         * 设置hxzz属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setHXZZ(String value) {
            this.hxzz = value;
        }

        /**
         * 获取rztj属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRZTJ() {
            return rztj;
        }

        /**
         * 设置rztj属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRZTJ(String value) {
            this.rztj = value;
        }

    }

}
