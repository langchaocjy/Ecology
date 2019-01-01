

/**
 * SIO_OA_AFS_OA002Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */

    package out.deal.afs.user;

    /*
     *  SIO_OA_AFS_OA002Service java interface
     */

    public interface SIO_OA_AFS_OA002Service {
          

        /**
          * Auto generated method signature
          * 
                    * @param mT_OA_AFS_OA0020
                
         */

         
                     public out.deal.afs.user.MT_OA_AFS_OA002_REP sIO_OA_AFS_OA002(

                        out.deal.afs.user.MT_OA_AFS_OA002 mT_OA_AFS_OA0020)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param mT_OA_AFS_OA0020
            
          */
        public void startsIO_OA_AFS_OA002(

            out.deal.afs.user.MT_OA_AFS_OA002 mT_OA_AFS_OA0020,

            final out.deal.afs.user.SIO_OA_AFS_OA002ServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    