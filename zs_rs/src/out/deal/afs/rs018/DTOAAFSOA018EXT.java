
package out.deal.afs.rs018;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * ���Ųο���λ&�ո�
 * 
 * <p>DT_OA_AFS_OA018_EXT complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
     * ��ȡsheet���Ե�ֵ��
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
     * ����sheet���Ե�ֵ��
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
         * ��ȡsqrq���Ե�ֵ��
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
         * ����sqrq���Ե�ֵ��
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
