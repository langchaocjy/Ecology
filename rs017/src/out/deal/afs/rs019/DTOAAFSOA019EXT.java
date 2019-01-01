
package out.deal.afs.rs019;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * ������λ-�ӿ�
 * 
 * <p>DT_OA_AFS_OA019_EXT complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
     * ��ȡsheet���Ե�ֵ��
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
     * ����sheet���Ե�ֵ��
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
     * <p>anonymous complex type�� Java �ࡣ
     * 
     * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
         * ��ȡxqbm���Ե�ֵ��
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
         * ����xqbm���Ե�ֵ��
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
         * ��ȡgwsxrq���Ե�ֵ��
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
         * ����gwsxrq���Ե�ֵ��
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
         * ��ȡzpgw���Ե�ֵ��
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
         * ����zpgw���Ե�ֵ��
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
         * ��ȡhxzz���Ե�ֵ��
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
         * ����hxzz���Ե�ֵ��
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
         * ��ȡrztj���Ե�ֵ��
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
         * ����rztj���Ե�ֵ��
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
