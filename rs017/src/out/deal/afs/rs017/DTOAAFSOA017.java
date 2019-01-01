
package out.deal.afs.rs017;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * ��ȡ����ְ�����ְ����
 * 
 * <p>DT_OA_AFS_OA017 complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="DT_OA_AFS_OA017"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SHEET" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="GWBM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="SQRQ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="CKGW" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
@XmlType(name = "DT_OA_AFS_OA017", propOrder = {
    "sheet"
})
public class DTOAAFSOA017 {

    @XmlElement(name = "SHEET")
    protected List<DTOAAFSOA017 .SHEET> sheet;

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
     * {@link DTOAAFSOA017 .SHEET }
     * 
     * 
     */
    public List<DTOAAFSOA017 .SHEET> getSHEET() {
        if (sheet == null) {
            sheet = new ArrayList<DTOAAFSOA017 .SHEET>();
        }
        return this.sheet;
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
     *         &lt;element name="GWBM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="SQRQ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="CKGW" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
        "gwbm",
        "sqrq",
        "ckgw"
    })
    public static class SHEET {

        @XmlElement(name = "GWBM")
        protected String gwbm;
        @XmlElement(name = "SQRQ")
        protected String sqrq;
        @XmlElement(name = "CKGW")
        protected String ckgw;

        /**
         * ��ȡgwbm���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getGWBM() {
            return gwbm;
        }

        /**
         * ����gwbm���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setGWBM(String value) {
            this.gwbm = value;
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

        /**
         * ��ȡckgw���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCKGW() {
            return ckgw;
        }

        /**
         * ����ckgw���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCKGW(String value) {
            this.ckgw = value;
        }

    }

}
