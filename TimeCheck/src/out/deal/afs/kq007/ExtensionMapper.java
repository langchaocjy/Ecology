
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:50 GMT)
 */

        
            package out.deal.afs.kq007;
        
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
                  "ygh_table_type0".equals(typeName)){
                   
                            return  out.deal.afs.kq007.Ygh_table_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:bk2:afs:deal:out".equals(namespaceURI) &&
                  "DT_OA_AFS_OA007".equals(typeName)){
                   
                            return  out.deal.afs.kq007.DT_OA_AFS_OA007.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:bk2:afs:deal:out".equals(namespaceURI) &&
                  "DT_OA_AFS_OA007_REP".equals(typeName)){
                   
                            return  out.deal.afs.kq007.DT_OA_AFS_OA007_REP.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:bk2:afs:deal:out".equals(namespaceURI) &&
                  "RESLUT_type0".equals(typeName)){
                   
                            return  out.deal.afs.kq007.RESLUT_type0.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    