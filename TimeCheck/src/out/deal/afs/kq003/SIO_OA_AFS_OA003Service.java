

/**
 * SIO_OA_AFS_OA003Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */

    package out.deal.afs.kq003;

    /*
     *  SIO_OA_AFS_OA003Service java interface
     */

    public interface SIO_OA_AFS_OA003Service {
          

        /**
          * Auto generated method signature
          * 
                    * @param mT_OA_AFS_OA0030
                
         */

         
                     public out.deal.afs.kq003.MT_OA_AFS_OA003_REP sIO_OA_AFS_OA003(

                        out.deal.afs.kq003.MT_OA_AFS_OA003 mT_OA_AFS_OA0030)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param mT_OA_AFS_OA0030
            
          */
        public void startsIO_OA_AFS_OA003(

            out.deal.afs.kq003.MT_OA_AFS_OA003 mT_OA_AFS_OA0030,

            final out.deal.afs.kq003.SIO_OA_AFS_OA003ServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    