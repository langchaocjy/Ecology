
package out.deal.afs.rs016;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 写入核心职责、任职条件接口
 * 
 * <p>DT_OA_AFS_OA016 complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="DT_OA_AFS_OA016"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SHEET" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="gwsxrq" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="zpgw" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="gwbm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="ckgw" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="hxzz" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="rztj" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
@XmlType(name = "DT_OA_AFS_OA016", propOrder = {
    "sheet"
})
public class DTOAAFSOA016 {

    @XmlElement(name = "SHEET")
    protected List<DTOAAFSOA016 .SHEET> sheet;

    /**
     * Gets the value of the sheet property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sheet property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSHEET().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DTOAAFSOA016 .SHEET }
     * 
     * 
     */
    public List<DTOAAFSOA016 .SHEET> getSHEET() {
        if (sheet == null) {
            sheet = new ArrayList<DTOAAFSOA016 .SHEET>();
        }
        return this.sheet;
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
     *         &lt;element name="gwsxrq" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="zpgw" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="gwbm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="ckgw" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="hxzz" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="rztj" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
        "gwsxrq",
        "zpgw",
        "gwbm",
        "ckgw",
        "hxzz",
        "rztj"
    })
    public static class SHEET {

        protected String gwsxrq;
        protected String zpgw;
        protected String gwbm;
        protected String ckgw;
        protected String hxzz;
        protected String rztj;

        /**
         * 获取gwsxrq属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getGwsxrq() {
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
        public void setGwsxrq(String value) {
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
        public String getZpgw() {
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
        public void setZpgw(String value) {
            this.zpgw = value;
        }

        /**
         * 获取gwbm属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getGwbm() {
            return gwbm;
        }

        /**
         * 设置gwbm属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setGwbm(String value) {
            this.gwbm = value;
        }

        /**
         * 获取ckgw属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCkgw() {
            return ckgw;
        }

        /**
         * 设置ckgw属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCkgw(String value) {
            this.ckgw = value;
        }

        /**
         * 获取hxzz属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getHxzz() {
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
        public void setHxzz(String value) {
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
        public String getRztj() {
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
        public void setRztj(String value) {
            this.rztj = value;
        }

    }

}
