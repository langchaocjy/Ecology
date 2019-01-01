
package out.deal.afs.kq002;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * OA人员架构接口的返回值
 * 
 * <p>DT_OA_AFS_OA002_REP complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="DT_OA_AFS_OA002_REP"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SHEET" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="LASTNAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="SEX" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="BIRTHDAY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="TELEPHONE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="MOBILE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="EMAIL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="LOCATIONID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="JOBTITLE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="DEPARTMENTID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="SUBCOMPANYID1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="MANAGERID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="field2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="WORKCODE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="STATUS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="WERKS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="BTRTL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="PERSG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="PERSK" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="ANSVH" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="ZTERF" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="SCHKZ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="BEGDA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
@XmlType(name = "DT_OA_AFS_OA002_REP", propOrder = {
    "sheet"
})
public class DTOAAFSOA002REP {

    @XmlElement(name = "SHEET")
    protected List<DTOAAFSOA002REP.SHEET> sheet;

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
     * {@link DTOAAFSOA002REP.SHEET }
     * 
     * 
     */
    public List<DTOAAFSOA002REP.SHEET> getSHEET() {
        if (sheet == null) {
            sheet = new ArrayList<DTOAAFSOA002REP.SHEET>();
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
     *         &lt;element name="LASTNAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="SEX" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="BIRTHDAY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="TELEPHONE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="MOBILE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="EMAIL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="LOCATIONID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="JOBTITLE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="DEPARTMENTID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="SUBCOMPANYID1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="MANAGERID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="field2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="WORKCODE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="STATUS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="WERKS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="BTRTL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="PERSG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="PERSK" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="ANSVH" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="ZTERF" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="SCHKZ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="BEGDA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
        "lastname",
        "sex",
        "birthday",
        "telephone",
        "mobile",
        "email",
        "locationid",
        "jobtitle",
        "departmentid",
        "subcompanyid1",
        "managerid",
        "field2",
        "workcode",
        "status",
        "werks",
        "btrtl",
        "persg",
        "persk",
        "ansvh",
        "zterf",
        "schkz",
        "begda"
    })
    public static class SHEET {

        @XmlElement(name = "LASTNAME")
        protected String lastname;
        @XmlElement(name = "SEX")
        protected String sex;
        @XmlElement(name = "BIRTHDAY")
        protected String birthday;
        @XmlElement(name = "TELEPHONE")
        protected String telephone;
        @XmlElement(name = "MOBILE")
        protected String mobile;
        @XmlElement(name = "EMAIL")
        protected String email;
        @XmlElement(name = "LOCATIONID")
        protected String locationid;
        @XmlElement(name = "JOBTITLE")
        protected String jobtitle;
        @XmlElement(name = "DEPARTMENTID")
        protected String departmentid;
        @XmlElement(name = "SUBCOMPANYID1")
        protected String subcompanyid1;
        @XmlElement(name = "MANAGERID")
        protected String managerid;
        protected String field2;
        @XmlElement(name = "WORKCODE")
        protected String workcode;
        @XmlElement(name = "STATUS")
        protected String status;
        @XmlElement(name = "WERKS")
        protected String werks;
        @XmlElement(name = "BTRTL")
        protected String btrtl;
        @XmlElement(name = "PERSG")
        protected String persg;
        @XmlElement(name = "PERSK")
        protected String persk;
        @XmlElement(name = "ANSVH")
        protected String ansvh;
        @XmlElement(name = "ZTERF")
        protected String zterf;
        @XmlElement(name = "SCHKZ")
        protected String schkz;
        @XmlElement(name = "BEGDA")
        protected String begda;

        /**
         * 获取lastname属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLASTNAME() {
            return lastname;
        }

        /**
         * 设置lastname属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLASTNAME(String value) {
            this.lastname = value;
        }

        /**
         * 获取sex属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSEX() {
            return sex;
        }

        /**
         * 设置sex属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSEX(String value) {
            this.sex = value;
        }

        /**
         * 获取birthday属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBIRTHDAY() {
            return birthday;
        }

        /**
         * 设置birthday属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBIRTHDAY(String value) {
            this.birthday = value;
        }

        /**
         * 获取telephone属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTELEPHONE() {
            return telephone;
        }

        /**
         * 设置telephone属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTELEPHONE(String value) {
            this.telephone = value;
        }

        /**
         * 获取mobile属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMOBILE() {
            return mobile;
        }

        /**
         * 设置mobile属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMOBILE(String value) {
            this.mobile = value;
        }

        /**
         * 获取email属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEMAIL() {
            return email;
        }

        /**
         * 设置email属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEMAIL(String value) {
            this.email = value;
        }

        /**
         * 获取locationid属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLOCATIONID() {
            return locationid;
        }

        /**
         * 设置locationid属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLOCATIONID(String value) {
            this.locationid = value;
        }

        /**
         * 获取jobtitle属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getJOBTITLE() {
            return jobtitle;
        }

        /**
         * 设置jobtitle属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setJOBTITLE(String value) {
            this.jobtitle = value;
        }

        /**
         * 获取departmentid属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDEPARTMENTID() {
            return departmentid;
        }

        /**
         * 设置departmentid属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDEPARTMENTID(String value) {
            this.departmentid = value;
        }

        /**
         * 获取subcompanyid1属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSUBCOMPANYID1() {
            return subcompanyid1;
        }

        /**
         * 设置subcompanyid1属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSUBCOMPANYID1(String value) {
            this.subcompanyid1 = value;
        }

        /**
         * 获取managerid属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMANAGERID() {
            return managerid;
        }

        /**
         * 设置managerid属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMANAGERID(String value) {
            this.managerid = value;
        }

        /**
         * 获取field2属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getField2() {
            return field2;
        }

        /**
         * 设置field2属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setField2(String value) {
            this.field2 = value;
        }

        /**
         * 获取workcode属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getWORKCODE() {
            return workcode;
        }

        /**
         * 设置workcode属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setWORKCODE(String value) {
            this.workcode = value;
        }

        /**
         * 获取status属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSTATUS() {
            return status;
        }

        /**
         * 设置status属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSTATUS(String value) {
            this.status = value;
        }

        /**
         * 获取werks属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getWERKS() {
            return werks;
        }

        /**
         * 设置werks属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setWERKS(String value) {
            this.werks = value;
        }

        /**
         * 获取btrtl属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBTRTL() {
            return btrtl;
        }

        /**
         * 设置btrtl属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBTRTL(String value) {
            this.btrtl = value;
        }

        /**
         * 获取persg属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPERSG() {
            return persg;
        }

        /**
         * 设置persg属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPERSG(String value) {
            this.persg = value;
        }

        /**
         * 获取persk属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPERSK() {
            return persk;
        }

        /**
         * 设置persk属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPERSK(String value) {
            this.persk = value;
        }

        /**
         * 获取ansvh属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getANSVH() {
            return ansvh;
        }

        /**
         * 设置ansvh属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setANSVH(String value) {
            this.ansvh = value;
        }

        /**
         * 获取zterf属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getZTERF() {
            return zterf;
        }

        /**
         * 设置zterf属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setZTERF(String value) {
            this.zterf = value;
        }

        /**
         * 获取schkz属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSCHKZ() {
            return schkz;
        }

        /**
         * 设置schkz属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSCHKZ(String value) {
            this.schkz = value;
        }

        /**
         * 获取begda属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBEGDA() {
            return begda;
        }

        /**
         * 设置begda属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBEGDA(String value) {
            this.begda = value;
        }

    }

}
