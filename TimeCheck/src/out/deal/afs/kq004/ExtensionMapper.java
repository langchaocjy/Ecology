
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:50 GMT)
 */

        
            package out.deal.afs.kq004;
        
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
                  "RETURN_type0".equals(typeName)){
                   
                            return  out.deal.afs.kq004.RETURN_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:bk2:afs:deal:out".equals(namespaceURI) &&
                  "DT_OA_AFS_OA004".equals(typeName)){
                   
                            return  out.deal.afs.kq004.DT_OA_AFS_OA004.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:bk2:afs:deal:out".equals(namespaceURI) &&
                  "DT_OA_AFS_OA004_REP".equals(typeName)){
                   
                            return  out.deal.afs.kq004.DT_OA_AFS_OA004_REP.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:bk2:afs:deal:out".equals(namespaceURI) &&
                  "table31_dt1_type0".equals(typeName)){
                   
                            return  out.deal.afs.kq004.Table31_dt1_type0.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    