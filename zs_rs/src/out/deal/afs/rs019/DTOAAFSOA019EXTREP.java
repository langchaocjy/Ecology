
package out.deal.afs.rs019;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * ������λ-�ӿ�
 * 
 * <p>DT_OA_AFS_OA019_EXT_REP complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="DT_OA_AFS_OA019_EXT_REP"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SHEET" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="GWBM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="CODE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="MESG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
@XmlType(name = "DT_OA_AFS_OA019_EXT_REP", propOrder = {
    "sheet"
})
public class DTOAAFSOA019EXTREP {

    @XmlElement(name = "SHEET")
    protected DTOAAFSOA019EXTREP.SHEET sheet;

    /**
     * ��ȡsheet���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link DTOAAFSOA019EXTREP.SHEET }
     *     
     */
    public DTOAAFSOA019EXTREP.SHEET getSHEET() {
        return sheet;
    }

    /**
     * ����sheet���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link DTOAAFSOA019EXTREP.SHEET }
     *     
     */
    public void setSHEET(DTOAAFSOA019EXTREP.SHEET value) {
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
     *         &lt;element name="GWBM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="CODE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="MESG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
        "code",
        "mesg"
    })
    public static class SHEET {

        @XmlElement(name = "GWBM")
        protected String gwbm;
        @XmlElement(name = "CODE")
        protected String code;
        @XmlElement(name = "MESG")
        protected String mesg;

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
         * ��ȡcode���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCODE() {
            return code;
        }

        /**
         * ����code���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCODE(String value) {
            this.code = value;
        }

        /**
         * ��ȡmesg���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMESG() {
            return mesg;
        }

        /**
         * ����mesg���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMESG(String value) {
            this.mesg = value;
        }

    }

}
