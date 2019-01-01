

/**
 * SIO_OA_AFS_OA007Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */

    package out.deal.afs.kq007;

    /*
     *  SIO_OA_AFS_OA007Service java interface
     */

    public interface SIO_OA_AFS_OA007Service {
          

        /**
          * Auto generated method signature
          * 
                    * @param mT_OA_AFS_OA0070
                
         */

         
                     public out.deal.afs.kq007.MT_OA_AFS_OA007_REP sIO_OA_AFS_OA007(

                        out.deal.afs.kq007.MT_OA_AFS_OA007 mT_OA_AFS_OA0070)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param mT_OA_AFS_OA0070
            
          */
        public void startsIO_OA_AFS_OA007(

            out.deal.afs.kq007.MT_OA_AFS_OA007 mT_OA_AFS_OA0070,

            final out.deal.afs.kq007.SIO_OA_AFS_OA007ServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    