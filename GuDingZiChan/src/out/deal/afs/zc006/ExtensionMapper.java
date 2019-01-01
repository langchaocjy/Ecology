
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:50 GMT)
 */

        
            package out.deal.afs.zc006;
        
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
                  "DOC_HEADER_type0".equals(typeName)){
                   
                            return  out.deal.afs.zc006.DOC_HEADER_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:bk2:afs:deal:out".equals(namespaceURI) &&
                  "DT_FI_OA_AFS_OA006_REP".equals(typeName)){
                   
                            return  out.deal.afs.zc006.DT_FI_OA_AFS_OA006_REP.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:bk2:afs:deal:out".equals(namespaceURI) &&
                  "DT_FI_OA_AFS_OA006".equals(typeName)){
                   
                            return  out.deal.afs.zc006.DT_FI_OA_AFS_OA006.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:bk2:afs:deal:out".equals(namespaceURI) &&
                  "ET_RETURN_type0".equals(typeName)){
                   
                            return  out.deal.afs.zc006.ET_RETURN_type0.Factory.parse(reader);
                        

                  }

              
                  if (
                  "urn:bk2:afs:deal:out".equals(namespaceURI) &&
                  "DOC_ITEM_type0".equals(typeName)){
                   
                            return  out.deal.afs.zc006.DOC_ITEM_type0.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    