
/**
 * SHEET_type0.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:50 GMT)
 */

            
                package out.deal.afs.company;
            

            /**
            *  SHEET_type0 bean class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class SHEET_type0
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = SHEET_type0
                Namespace URI = urn:bk2:afs:deal:out
                Namespace Prefix = ns1
                */
            

                        /**
                        * field for Hrmsubcompany
                        * This was an Array!
                        */

                        
                                    protected out.deal.afs.company.Hrmsubcompany_type0[] localHrmsubcompany ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHrmsubcompanyTracker = false ;

                           public boolean isHrmsubcompanySpecified(){
                               return localHrmsubcompanyTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return out.deal.afs.bk2.Hrmsubcompany_type0[]
                           */
                           public  out.deal.afs.company.Hrmsubcompany_type0[] getHrmsubcompany(){
                               return localHrmsubcompany;
                           }

                           
                        


                               
                              /**
                               * validate the array for Hrmsubcompany
                               */
                              protected void validateHrmsubcompany(out.deal.afs.company.Hrmsubcompany_type0[] param){
                             
                              }


                             /**
                              * Auto generated setter method
                              * @param param Hrmsubcompany
                              */
                              public void setHrmsubcompany(out.deal.afs.company.Hrmsubcompany_type0[] param){
                              
                                   validateHrmsubcompany(param);

                               localHrmsubcompanyTracker = param != null;
                                      
                                      this.localHrmsubcompany=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param out.deal.afs.bk2.Hrmsubcompany_type0
                             */
                             public void addHrmsubcompany(out.deal.afs.company.Hrmsubcompany_type0 param){
                                   if (localHrmsubcompany == null){
                                   localHrmsubcompany = new out.deal.afs.company.Hrmsubcompany_type0[]{};
                                   }

                            
                                 //update the setting tracker
                                localHrmsubcompanyTracker = true;
                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localHrmsubcompany);
                               list.add(param);
                               this.localHrmsubcompany =
                             (out.deal.afs.company.Hrmsubcompany_type0[])list.toArray(
                            new out.deal.afs.company.Hrmsubcompany_type0[list.size()]);

                             }
                             

                        /**
                        * field for Hrmdepartment
                        * This was an Array!
                        */

                        
                                    protected out.deal.afs.company.Hrmdepartment_type0[] localHrmdepartment ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHrmdepartmentTracker = false ;

                           public boolean isHrmdepartmentSpecified(){
                               return localHrmdepartmentTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return out.deal.afs.bk2.Hrmdepartment_type0[]
                           */
                           public  out.deal.afs.company.Hrmdepartment_type0[] getHrmdepartment(){
                               return localHrmdepartment;
                           }

                           
                        


                               
                              /**
                               * validate the array for Hrmdepartment
                               */
                              protected void validateHrmdepartment(out.deal.afs.company.Hrmdepartment_type0[] param){
                             
                              }


                             /**
                              * Auto generated setter method
                              * @param param Hrmdepartment
                              */
                              public void setHrmdepartment(out.deal.afs.company.Hrmdepartment_type0[] param){
                              
                                   validateHrmdepartment(param);

                               localHrmdepartmentTracker = param != null;
                                      
                                      this.localHrmdepartment=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param out.deal.afs.bk2.Hrmdepartment_type0
                             */
                             public void addHrmdepartment(out.deal.afs.company.Hrmdepartment_type0 param){
                                   if (localHrmdepartment == null){
                                   localHrmdepartment = new out.deal.afs.company.Hrmdepartment_type0[]{};
                                   }

                            
                                 //update the setting tracker
                                localHrmdepartmentTracker = true;
                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localHrmdepartment);
                               list.add(param);
                               this.localHrmdepartment =
                             (out.deal.afs.company.Hrmdepartment_type0[])list.toArray(
                            new out.deal.afs.company.Hrmdepartment_type0[list.size()]);

                             }
                             

                        /**
                        * field for Hrmjobtitles
                        * This was an Array!
                        */

                        
                                    protected out.deal.afs.company.Hrmjobtitles_type0[] localHrmjobtitles ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localHrmjobtitlesTracker = false ;

                           public boolean isHrmjobtitlesSpecified(){
                               return localHrmjobtitlesTracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return out.deal.afs.bk2.Hrmjobtitles_type0[]
                           */
                           public  out.deal.afs.company.Hrmjobtitles_type0[] getHrmjobtitles(){
                               return localHrmjobtitles;
                           }

                           
                        


                               
                              /**
                               * validate the array for Hrmjobtitles
                               */
                              protected void validateHrmjobtitles(out.deal.afs.company.Hrmjobtitles_type0[] param){
                             
                              }


                             /**
                              * Auto generated setter method
                              * @param param Hrmjobtitles
                              */
                              public void setHrmjobtitles(out.deal.afs.company.Hrmjobtitles_type0[] param){
                              
                                   validateHrmjobtitles(param);

                               localHrmjobtitlesTracker = param != null;
                                      
                                      this.localHrmjobtitles=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param out.deal.afs.bk2.Hrmjobtitles_type0
                             */
                             public void addHrmjobtitles(out.deal.afs.company.Hrmjobtitles_type0 param){
                                   if (localHrmjobtitles == null){
                                   localHrmjobtitles = new out.deal.afs.company.Hrmjobtitles_type0[]{};
                                   }

                            
                                 //update the setting tracker
                                localHrmjobtitlesTracker = true;
                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localHrmjobtitles);
                               list.add(param);
                               this.localHrmjobtitles =
                             (out.deal.afs.company.Hrmjobtitles_type0[])list.toArray(
                            new out.deal.afs.company.Hrmjobtitles_type0[list.size()]);

                             }
                             

                        /**
                        * field for Formtable_main_30
                        * This was an Array!
                        */

                        
                                    protected out.deal.afs.company.Formtable_main_30_type0[] localFormtable_main_30 ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localFormtable_main_30Tracker = false ;

                           public boolean isFormtable_main_30Specified(){
                               return localFormtable_main_30Tracker;
                           }

                           

                           /**
                           * Auto generated getter method
                           * @return out.deal.afs.bk2.Formtable_main_30_type0[]
                           */
                           public  out.deal.afs.company.Formtable_main_30_type0[] getFormtable_main_30(){
                               return localFormtable_main_30;
                           }

                           
                        


                               
                              /**
                               * validate the array for Formtable_main_30
                               */
                              protected void validateFormtable_main_30(out.deal.afs.company.Formtable_main_30_type0[] param){
                             
                              }


                             /**
                              * Auto generated setter method
                              * @param param Formtable_main_30
                              */
                              public void setFormtable_main_30(out.deal.afs.company.Formtable_main_30_type0[] param){
                              
                                   validateFormtable_main_30(param);

                               localFormtable_main_30Tracker = param != null;
                                      
                                      this.localFormtable_main_30=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param out.deal.afs.bk2.Formtable_main_30_type0
                             */
                             public void addFormtable_main_30(out.deal.afs.company.Formtable_main_30_type0 param){
                                   if (localFormtable_main_30 == null){
                                   localFormtable_main_30 = new out.deal.afs.company.Formtable_main_30_type0[]{};
                                   }

                            
                                 //update the setting tracker
                                localFormtable_main_30Tracker = true;
                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localFormtable_main_30);
                               list.add(param);
                               this.localFormtable_main_30 =
                             (out.deal.afs.company.Formtable_main_30_type0[])list.toArray(
                            new out.deal.afs.company.Formtable_main_30_type0[list.size()]);

                             }
                             

     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               return factory.createOMElement(new org.apache.axis2.databinding.ADBDataSource(this,parentQName));
            
        }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       javax.xml.stream.XMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               javax.xml.stream.XMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();
                    writeStartElement(prefix, namespace, parentQName.getLocalPart(), xmlWriter);
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"urn:bk2:afs:deal:out");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":SHEET_type0",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "SHEET_type0",
                           xmlWriter);
                   }

               
                   }
                if (localHrmsubcompanyTracker){
                                       if (localHrmsubcompany!=null){
                                            for (int i = 0;i < localHrmsubcompany.length;i++){
                                                if (localHrmsubcompany[i] != null){
                                                 localHrmsubcompany[i].serialize(new javax.xml.namespace.QName("","hrmsubcompany"),
                                                           xmlWriter);
                                                } else {
                                                   
                                                        // we don't have to do any thing since minOccures is zero
                                                    
                                                }

                                            }
                                     } else {
                                        
                                               throw new org.apache.axis2.databinding.ADBException("hrmsubcompany cannot be null!!");
                                        
                                    }
                                 } if (localHrmdepartmentTracker){
                                       if (localHrmdepartment!=null){
                                            for (int i = 0;i < localHrmdepartment.length;i++){
                                                if (localHrmdepartment[i] != null){
                                                 localHrmdepartment[i].serialize(new javax.xml.namespace.QName("","hrmdepartment"),
                                                           xmlWriter);
                                                } else {
                                                   
                                                        // we don't have to do any thing since minOccures is zero
                                                    
                                                }

                                            }
                                     } else {
                                        
                                               throw new org.apache.axis2.databinding.ADBException("hrmdepartment cannot be null!!");
                                        
                                    }
                                 } if (localHrmjobtitlesTracker){
                                       if (localHrmjobtitles!=null){
                                            for (int i = 0;i < localHrmjobtitles.length;i++){
                                                if (localHrmjobtitles[i] != null){
                                                 localHrmjobtitles[i].serialize(new javax.xml.namespace.QName("","hrmjobtitles"),
                                                           xmlWriter);
                                                } else {
                                                   
                                                        // we don't have to do any thing since minOccures is zero
                                                    
                                                }

                                            }
                                     } else {
                                        
                                               throw new org.apache.axis2.databinding.ADBException("hrmjobtitles cannot be null!!");
                                        
                                    }
                                 } if (localFormtable_main_30Tracker){
                                       if (localFormtable_main_30!=null){
                                            for (int i = 0;i < localFormtable_main_30.length;i++){
                                                if (localFormtable_main_30[i] != null){
                                                 localFormtable_main_30[i].serialize(new javax.xml.namespace.QName("","formtable_main_30"),
                                                           xmlWriter);
                                                } else {
                                                   
                                                        // we don't have to do any thing since minOccures is zero
                                                    
                                                }

                                            }
                                     } else {
                                        
                                               throw new org.apache.axis2.databinding.ADBException("formtable_main_30 cannot be null!!");
                                        
                                    }
                                 }
                    xmlWriter.writeEndElement();
               

        }

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("urn:bk2:afs:deal:out")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix, java.lang.String namespace, java.lang.String localPart,
                                       javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeStartElement(writerPrefix, localPart, namespace);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }
        
        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
            if (writerPrefix != null) {
                xmlWriter.writeAttribute(writerPrefix, namespace,attName,attValue);
            } else {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
                xmlWriter.writeAttribute(prefix, namespace,attName,attValue);
            }
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                    java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName,attValue);
            } else {
                xmlWriter.writeAttribute(registerPrefix(xmlWriter, namespace), namespace,attName,attValue);
            }
        }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(attributePrefix, namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);
            if (prefix == null) {
                prefix = generatePrefix(namespace);
                javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();
                while (true) {
                    java.lang.String uri = nsContext.getNamespaceURI(prefix);
                    if (uri == null || uri.length() == 0) {
                        break;
                    }
                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
            return prefix;
        }


  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{
        private static org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(Factory.class);

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static SHEET_type0 parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            SHEET_type0 object =
                new SHEET_type0();

            int event;
            javax.xml.namespace.QName currentQName = null;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                currentQName = reader.getName();
                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"SHEET_type0".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (SHEET_type0)out.deal.afs.company.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                
                    
                    reader.next();
                
                        java.util.ArrayList list1 = new java.util.ArrayList();
                    
                        java.util.ArrayList list2 = new java.util.ArrayList();
                    
                        java.util.ArrayList list3 = new java.util.ArrayList();
                    
                        java.util.ArrayList list4 = new java.util.ArrayList();
                    
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","hrmsubcompany").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    
                                    list1.add(out.deal.afs.company.Hrmsubcompany_type0.Factory.parse(reader));
                                                                
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone1 = false;
                                                        while(!loopDone1){
                                                            // We should be at the end element, but make sure
                                                            while (!reader.isEndElement())
                                                                reader.next();
                                                            // Step out of this element
                                                            reader.next();
                                                            // Step to next element event.
                                                            while (!reader.isStartElement() && !reader.isEndElement())
                                                                reader.next();
                                                            if (reader.isEndElement()){
                                                                //two continuous end elements means we are exiting the xml structure
                                                                loopDone1 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("","hrmsubcompany").equals(reader.getName())){
                                                                    list1.add(out.deal.afs.company.Hrmsubcompany_type0.Factory.parse(reader));
                                                                        
                                                                }else{
                                                                    loopDone1 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setHrmsubcompany((out.deal.afs.company.Hrmsubcompany_type0[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                out.deal.afs.company.Hrmsubcompany_type0.class,
                                                                list1));
                                                            
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","hrmdepartment").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    
                                    list2.add(out.deal.afs.company.Hrmdepartment_type0.Factory.parse(reader));
                                                                
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone2 = false;
                                                        while(!loopDone2){
                                                            // We should be at the end element, but make sure
                                                            while (!reader.isEndElement())
                                                                reader.next();
                                                            // Step out of this element
                                                            reader.next();
                                                            // Step to next element event.
                                                            while (!reader.isStartElement() && !reader.isEndElement())
                                                                reader.next();
                                                            if (reader.isEndElement()){
                                                                //two continuous end elements means we are exiting the xml structure
                                                                loopDone2 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("","hrmdepartment").equals(reader.getName())){
                                                                    list2.add(out.deal.afs.company.Hrmdepartment_type0.Factory.parse(reader));
                                                                        
                                                                }else{
                                                                    loopDone2 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setHrmdepartment((out.deal.afs.company.Hrmdepartment_type0[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                out.deal.afs.company.Hrmdepartment_type0.class,
                                                                list2));
                                                            
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","hrmjobtitles").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    
                                    list3.add(out.deal.afs.company.Hrmjobtitles_type0.Factory.parse(reader));
                                                                
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone3 = false;
                                                        while(!loopDone3){
                                                            // We should be at the end element, but make sure
                                                            while (!reader.isEndElement())
                                                                reader.next();
                                                            // Step out of this element
                                                            reader.next();
                                                            // Step to next element event.
                                                            while (!reader.isStartElement() && !reader.isEndElement())
                                                                reader.next();
                                                            if (reader.isEndElement()){
                                                                //two continuous end elements means we are exiting the xml structure
                                                                loopDone3 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("","hrmjobtitles").equals(reader.getName())){
                                                                    list3.add(out.deal.afs.company.Hrmjobtitles_type0.Factory.parse(reader));
                                                                        
                                                                }else{
                                                                    loopDone3 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setHrmjobtitles((out.deal.afs.company.Hrmjobtitles_type0[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                out.deal.afs.company.Hrmjobtitles_type0.class,
                                                                list3));
                                                            
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("","formtable_main_30").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    
                                    list4.add(out.deal.afs.company.Formtable_main_30_type0.Factory.parse(reader));
                                                                
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone4 = false;
                                                        while(!loopDone4){
                                                            // We should be at the end element, but make sure
                                                            while (!reader.isEndElement())
                                                                reader.next();
                                                            // Step out of this element
                                                            reader.next();
                                                            // Step to next element event.
                                                            while (!reader.isStartElement() && !reader.isEndElement())
                                                                reader.next();
                                                            if (reader.isEndElement()){
                                                                //two continuous end elements means we are exiting the xml structure
                                                                loopDone4 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("","formtable_main_30").equals(reader.getName())){
                                                                    list4.add(out.deal.afs.company.Formtable_main_30_type0.Factory.parse(reader));
                                                                        
                                                                }else{
                                                                    loopDone4 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setFormtable_main_30((out.deal.afs.company.Formtable_main_30_type0[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                out.deal.afs.company.Formtable_main_30_type0.class,
                                                                list4));
                                                            
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // 2 - A start element we are not expecting indicates a trailing invalid property
                                
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getName());
                                



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
    