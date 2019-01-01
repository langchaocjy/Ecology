
package out.deal.afs.kq001;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * OA组织架构接口
 * 
 * <p>DT_OA_AFS_OA001_REP complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="DT_OA_AFS_OA001_REP"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SHEET" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="hrmsubcompany" maxOccurs="unbounded" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="subcompanyname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="subcompanydesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="subcompanycode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="hrmdepartment" maxOccurs="unbounded" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="departmentmark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="departmentname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="subcompanydescbm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="departmentnamesjbm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="departmentcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="cjsx" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="gh" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="hrmjobtitles" maxOccurs="unbounded" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="jobtitlemark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="jobtitlename" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="jobtitlecode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="departmentnamebm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="kgbs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="bukrs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="bk1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="bk2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="formtable_main_30" maxOccurs="unbounded" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="zzmc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="zzbm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="bedat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="endat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="cbzxmc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="cbzxbm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="cbfzrmc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="cbfzrbm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="frgsmc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="frgsbm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="zccbzx" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                             &lt;element name="cjsx" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
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
@XmlType(name = "DT_OA_AFS_OA001_REP", propOrder = {
    "sheet"
})
public class DTOAAFSOA001REP {

    @XmlElement(name = "SHEET")
    protected DTOAAFSOA001REP.SHEET sheet;

    /**
     * 获取sheet属性的值。
     * 
     * @return
     *     possible object is
     *     {@link DTOAAFSOA001REP.SHEET }
     *     
     */
    public DTOAAFSOA001REP.SHEET getSHEET() {
        return sheet;
    }

    /**
     * 设置sheet属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link DTOAAFSOA001REP.SHEET }
     *     
     */
    public void setSHEET(DTOAAFSOA001REP.SHEET value) {
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
     *         &lt;element name="hrmsubcompany" maxOccurs="unbounded" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="subcompanyname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="subcompanydesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="subcompanycode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="hrmdepartment" maxOccurs="unbounded" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="departmentmark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="departmentname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="subcompanydescbm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="departmentnamesjbm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="departmentcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="cjsx" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="gh" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="hrmjobtitles" maxOccurs="unbounded" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="jobtitlemark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="jobtitlename" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="jobtitlecode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="departmentnamebm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="kgbs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="bukrs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="bk1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="bk2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="formtable_main_30" maxOccurs="unbounded" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="zzmc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="zzbm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="bedat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="endat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="cbzxmc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="cbzxbm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="cbfzrmc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="cbfzrbm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="frgsmc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="frgsbm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="zccbzx" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                   &lt;element name="cjsx" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    @XmlType(name = "", propOrder = {
        "hrmsubcompany",
        "hrmdepartment",
        "hrmjobtitles",
        "formtableMain30"
    })
    public static class SHEET {

        protected List<DTOAAFSOA001REP.SHEET.Hrmsubcompany> hrmsubcompany;
        protected List<DTOAAFSOA001REP.SHEET.Hrmdepartment> hrmdepartment;
        protected List<DTOAAFSOA001REP.SHEET.Hrmjobtitles> hrmjobtitles;
        @XmlElement(name = "formtable_main_30")
        protected List<DTOAAFSOA001REP.SHEET.FormtableMain30> formtableMain30;

        /**
         * Gets the value of the hrmsubcompany property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the hrmsubcompany property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getHrmsubcompany().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DTOAAFSOA001REP.SHEET.Hrmsubcompany }
         * 
         * 
         */
        public List<DTOAAFSOA001REP.SHEET.Hrmsubcompany> getHrmsubcompany() {
            if (hrmsubcompany == null) {
                hrmsubcompany = new ArrayList<DTOAAFSOA001REP.SHEET.Hrmsubcompany>();
            }
            return this.hrmsubcompany;
        }

        /**
         * Gets the value of the hrmdepartment property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the hrmdepartment property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getHrmdepartment().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DTOAAFSOA001REP.SHEET.Hrmdepartment }
         * 
         * 
         */
        public List<DTOAAFSOA001REP.SHEET.Hrmdepartment> getHrmdepartment() {
            if (hrmdepartment == null) {
                hrmdepartment = new ArrayList<DTOAAFSOA001REP.SHEET.Hrmdepartment>();
            }
            return this.hrmdepartment;
        }

        /**
         * Gets the value of the hrmjobtitles property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the hrmjobtitles property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getHrmjobtitles().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DTOAAFSOA001REP.SHEET.Hrmjobtitles }
         * 
         * 
         */
        public List<DTOAAFSOA001REP.SHEET.Hrmjobtitles> getHrmjobtitles() {
            if (hrmjobtitles == null) {
                hrmjobtitles = new ArrayList<DTOAAFSOA001REP.SHEET.Hrmjobtitles>();
            }
            return this.hrmjobtitles;
        }

        /**
         * Gets the value of the formtableMain30 property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the formtableMain30 property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getFormtableMain30().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DTOAAFSOA001REP.SHEET.FormtableMain30 }
         * 
         * 
         */
        public List<DTOAAFSOA001REP.SHEET.FormtableMain30> getFormtableMain30() {
            if (formtableMain30 == null) {
                formtableMain30 = new ArrayList<DTOAAFSOA001REP.SHEET.FormtableMain30>();
            }
            return this.formtableMain30;
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
         *         &lt;element name="zzmc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="zzbm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="bedat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="endat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="cbzxmc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="cbzxbm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="cbfzrmc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="cbfzrbm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="frgsmc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="frgsbm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="zccbzx" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="cjsx" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
            "zzmc",
            "zzbm",
            "bedat",
            "endat",
            "cbzxmc",
            "cbzxbm",
            "cbfzrmc",
            "cbfzrbm",
            "frgsmc",
            "frgsbm",
            "zccbzx",
            "cjsx"
        })
        public static class FormtableMain30 {

            protected String zzmc;
            protected String zzbm;
            protected String bedat;
            protected String endat;
            protected String cbzxmc;
            protected String cbzxbm;
            protected String cbfzrmc;
            protected String cbfzrbm;
            protected String frgsmc;
            protected String frgsbm;
            protected String zccbzx;
            protected String cjsx;

            /**
             * 获取zzmc属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getZzmc() {
                return zzmc;
            }

            /**
             * 设置zzmc属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setZzmc(String value) {
                this.zzmc = value;
            }

            /**
             * 获取zzbm属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getZzbm() {
                return zzbm;
            }

            /**
             * 设置zzbm属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setZzbm(String value) {
                this.zzbm = value;
            }

            /**
             * 获取bedat属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBedat() {
                return bedat;
            }

            /**
             * 设置bedat属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBedat(String value) {
                this.bedat = value;
            }

            /**
             * 获取endat属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getEndat() {
                return endat;
            }

            /**
             * 设置endat属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setEndat(String value) {
                this.endat = value;
            }

            /**
             * 获取cbzxmc属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCbzxmc() {
                return cbzxmc;
            }

            /**
             * 设置cbzxmc属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCbzxmc(String value) {
                this.cbzxmc = value;
            }

            /**
             * 获取cbzxbm属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCbzxbm() {
                return cbzxbm;
            }

            /**
             * 设置cbzxbm属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCbzxbm(String value) {
                this.cbzxbm = value;
            }

            /**
             * 获取cbfzrmc属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCbfzrmc() {
                return cbfzrmc;
            }

            /**
             * 设置cbfzrmc属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCbfzrmc(String value) {
                this.cbfzrmc = value;
            }

            /**
             * 获取cbfzrbm属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCbfzrbm() {
                return cbfzrbm;
            }

            /**
             * 设置cbfzrbm属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCbfzrbm(String value) {
                this.cbfzrbm = value;
            }

            /**
             * 获取frgsmc属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFrgsmc() {
                return frgsmc;
            }

            /**
             * 设置frgsmc属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFrgsmc(String value) {
                this.frgsmc = value;
            }

            /**
             * 获取frgsbm属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFrgsbm() {
                return frgsbm;
            }

            /**
             * 设置frgsbm属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFrgsbm(String value) {
                this.frgsbm = value;
            }

            /**
             * 获取zccbzx属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getZccbzx() {
                return zccbzx;
            }

            /**
             * 设置zccbzx属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setZccbzx(String value) {
                this.zccbzx = value;
            }

            /**
             * 获取cjsx属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCjsx() {
                return cjsx;
            }

            /**
             * 设置cjsx属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCjsx(String value) {
                this.cjsx = value;
            }

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
         *         &lt;element name="departmentmark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="departmentname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="subcompanydescbm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="departmentnamesjbm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="departmentcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="cjsx" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="gh" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
            "departmentmark",
            "departmentname",
            "subcompanydescbm",
            "departmentnamesjbm",
            "departmentcode",
            "cjsx",
            "gh",
            "name"
        })
        public static class Hrmdepartment {

            protected String departmentmark;
            protected String departmentname;
            protected String subcompanydescbm;
            protected String departmentnamesjbm;
            protected String departmentcode;
            protected String cjsx;
            protected String gh;
            protected String name;

            /**
             * 获取departmentmark属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDepartmentmark() {
                return departmentmark;
            }

            /**
             * 设置departmentmark属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDepartmentmark(String value) {
                this.departmentmark = value;
            }

            /**
             * 获取departmentname属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDepartmentname() {
                return departmentname;
            }

            /**
             * 设置departmentname属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDepartmentname(String value) {
                this.departmentname = value;
            }

            /**
             * 获取subcompanydescbm属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSubcompanydescbm() {
                return subcompanydescbm;
            }

            /**
             * 设置subcompanydescbm属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSubcompanydescbm(String value) {
                this.subcompanydescbm = value;
            }

            /**
             * 获取departmentnamesjbm属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDepartmentnamesjbm() {
                return departmentnamesjbm;
            }

            /**
             * 设置departmentnamesjbm属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDepartmentnamesjbm(String value) {
                this.departmentnamesjbm = value;
            }

            /**
             * 获取departmentcode属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDepartmentcode() {
                return departmentcode;
            }

            /**
             * 设置departmentcode属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDepartmentcode(String value) {
                this.departmentcode = value;
            }

            /**
             * 获取cjsx属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCjsx() {
                return cjsx;
            }

            /**
             * 设置cjsx属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCjsx(String value) {
                this.cjsx = value;
            }

            /**
             * 获取gh属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getGh() {
                return gh;
            }

            /**
             * 设置gh属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setGh(String value) {
                this.gh = value;
            }

            /**
             * 获取name属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getName() {
                return name;
            }

            /**
             * 设置name属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setName(String value) {
                this.name = value;
            }

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
         *         &lt;element name="jobtitlemark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="jobtitlename" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="jobtitlecode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="departmentnamebm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="kgbs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="bukrs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="bk1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="bk2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
            "jobtitlemark",
            "jobtitlename",
            "jobtitlecode",
            "departmentnamebm",
            "kgbs",
            "bukrs",
            "bk1",
            "bk2"
        })
        public static class Hrmjobtitles {

            protected String jobtitlemark;
            protected String jobtitlename;
            protected String jobtitlecode;
            protected String departmentnamebm;
            protected String kgbs;
            protected String bukrs;
            protected String bk1;
            protected String bk2;

            /**
             * 获取jobtitlemark属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getJobtitlemark() {
                return jobtitlemark;
            }

            /**
             * 设置jobtitlemark属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setJobtitlemark(String value) {
                this.jobtitlemark = value;
            }

            /**
             * 获取jobtitlename属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getJobtitlename() {
                return jobtitlename;
            }

            /**
             * 设置jobtitlename属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setJobtitlename(String value) {
                this.jobtitlename = value;
            }

            /**
             * 获取jobtitlecode属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getJobtitlecode() {
                return jobtitlecode;
            }

            /**
             * 设置jobtitlecode属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setJobtitlecode(String value) {
                this.jobtitlecode = value;
            }

            /**
             * 获取departmentnamebm属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDepartmentnamebm() {
                return departmentnamebm;
            }

            /**
             * 设置departmentnamebm属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDepartmentnamebm(String value) {
                this.departmentnamebm = value;
            }

            /**
             * 获取kgbs属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getKgbs() {
                return kgbs;
            }

            /**
             * 设置kgbs属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setKgbs(String value) {
                this.kgbs = value;
            }

            /**
             * 获取bukrs属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBukrs() {
                return bukrs;
            }

            /**
             * 设置bukrs属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBukrs(String value) {
                this.bukrs = value;
            }

            /**
             * 获取bk1属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBk1() {
                return bk1;
            }

            /**
             * 设置bk1属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBk1(String value) {
                this.bk1 = value;
            }

            /**
             * 获取bk2属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getBk2() {
                return bk2;
            }

            /**
             * 设置bk2属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setBk2(String value) {
                this.bk2 = value;
            }

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
         *         &lt;element name="subcompanyname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="subcompanydesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *         &lt;element name="subcompanycode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
            "subcompanyname",
            "subcompanydesc",
            "subcompanycode"
        })
        public static class Hrmsubcompany {

            protected String subcompanyname;
            protected String subcompanydesc;
            protected String subcompanycode;

            /**
             * 获取subcompanyname属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSubcompanyname() {
                return subcompanyname;
            }

            /**
             * 设置subcompanyname属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSubcompanyname(String value) {
                this.subcompanyname = value;
            }

            /**
             * 获取subcompanydesc属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSubcompanydesc() {
                return subcompanydesc;
            }

            /**
             * 设置subcompanydesc属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSubcompanydesc(String value) {
                this.subcompanydesc = value;
            }

            /**
             * 获取subcompanycode属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSubcompanycode() {
                return subcompanycode;
            }

            /**
             * 设置subcompanycode属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSubcompanycode(String value) {
                this.subcompanycode = value;
            }

        }

    }

}
