

/**
 * SIO_OA_AFS_OA030Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */

    package out.deal.afs.kq030;

    /*
     *  SIO_OA_AFS_OA030Service java interface
     */

    public interface SIO_OA_AFS_OA030Service {
          

        /**
          * Auto generated method signature
          * 
                    * @param mT_OA_AFS_OA0300
                
         */

         
                     public out.deal.afs.kq030.MT_OA_AFS_OA030_REP sIO_OA_AFS_OA030(

                        out.deal.afs.kq030.MT_OA_AFS_OA030 mT_OA_AFS_OA0300)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param mT_OA_AFS_OA0300
            
          */
        public void startsIO_OA_AFS_OA030(

            out.deal.afs.kq030.MT_OA_AFS_OA030 mT_OA_AFS_OA0300,

            final out.deal.afs.kq030.SIO_OA_AFS_OA030ServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    