

/**
 * SIO_OA_AFS_OA004Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */

    package out.deal.afs.kq004;

    /*
     *  SIO_OA_AFS_OA004Service java interface
     */

    public interface SIO_OA_AFS_OA004Service {
          

        /**
          * Auto generated method signature
          * 
                    * @param mT_OA_AFS_OA0040
                
         */

         
                     public out.deal.afs.kq004.MT_OA_AFS_OA004_REP sIO_OA_AFS_OA004(

                        out.deal.afs.kq004.MT_OA_AFS_OA004 mT_OA_AFS_OA0040)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param mT_OA_AFS_OA0040
            
          */
        public void startsIO_OA_AFS_OA004(

            out.deal.afs.kq004.MT_OA_AFS_OA004 mT_OA_AFS_OA0040,

            final out.deal.afs.kq004.SIO_OA_AFS_OA004ServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    