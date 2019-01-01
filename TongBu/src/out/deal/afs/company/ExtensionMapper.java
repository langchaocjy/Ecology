
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:50 GMT)
 */

        
            package out.deal.afs.company;
        
            /**
            *  ExtensionMapper class
            */
            @SuppressWarnings({"unchecked","unused"})
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "urn:bk2:afs:deal:out".equals(namespaceURI) &&
                  "DT_OA_AFS_OA001".equals(typeName)){
                   
                            return  out.deal.afs.company.DT_OA_AFS_OA001.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:bk2:afs:deal:out".equals(namespaceURI) &&
                  "formtable_main_30_type0".equals(typeName)){
                   
                            return  out.deal.afs.company.Formtable_main_30_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:bk2:afs:deal:out".equals(namespaceURI) &&
                  "SHEET_type0".equals(typeName)){
                   
                            return  out.deal.afs.company.SHEET_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:bk2:afs:deal:out".equals(namespaceURI) &&
                  "hrmdepartment_type0".equals(typeName)){
                   
                            return  out.deal.afs.company.Hrmdepartment_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:bk2:afs:deal:out".equals(namespaceURI) &&
                  "hrmsubcompany_type0".equals(typeName)){
                   
                            return  out.deal.afs.company.Hrmsubcompany_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:bk2:afs:deal:out".equals(namespaceURI) &&
                  "hrmjobtitles_type0".equals(typeName)){
                   
                            return  out.deal.afs.company.Hrmjobtitles_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:bk2:afs:deal:out".equals(namespaceURI) &&
                  "DT_OA_AFS_OA001_REP".equals(typeName)){
                   
                            return  out.deal.afs.company.DT_OA_AFS_OA001_REP.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    