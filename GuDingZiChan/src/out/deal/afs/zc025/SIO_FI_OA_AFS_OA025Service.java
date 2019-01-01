

/**
 * SIO_FI_OA_AFS_OA025Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */

    package out.deal.afs.zc025;

    /*
     *  SIO_FI_OA_AFS_OA025Service java interface
     */

    public interface SIO_FI_OA_AFS_OA025Service {
          

        /**
          * Auto generated method signature
          * 
                    * @param mT_FI_OA_AFS_OA0250
                
         */

         
                     public out.deal.afs.zc025.MT_FI_OA_AFS_OA025_REP sIO_FI_OA_AFS_OA025(

                        out.deal.afs.zc025.MT_FI_OA_AFS_OA025 mT_FI_OA_AFS_OA0250)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param mT_FI_OA_AFS_OA0250
            
          */
        public void startsIO_FI_OA_AFS_OA025(

            out.deal.afs.zc025.MT_FI_OA_AFS_OA025 mT_FI_OA_AFS_OA0250,

            final out.deal.afs.zc025.SIO_FI_OA_AFS_OA025ServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    