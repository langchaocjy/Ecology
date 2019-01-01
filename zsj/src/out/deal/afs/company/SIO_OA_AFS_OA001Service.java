

/**
 * SIO_OA_AFS_OA001Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */

    package out.deal.afs.company;

    /*
     *  SIO_OA_AFS_OA001Service java interface
     */

    public interface SIO_OA_AFS_OA001Service {
          

        /**
          * Auto generated method signature
          * 
                    * @param mT_OA_AFS_OA0010
                
         */

         
                     public out.deal.afs.company.MT_OA_AFS_OA001_REP sIO_OA_AFS_OA001(

                        out.deal.afs.company.MT_OA_AFS_OA001 mT_OA_AFS_OA0010)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param mT_OA_AFS_OA0010
            
          */
        public void startsIO_OA_AFS_OA001(

            out.deal.afs.company.MT_OA_AFS_OA001 mT_OA_AFS_OA0010,

            final out.deal.afs.company.SIO_OA_AFS_OA001ServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    