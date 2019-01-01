

/**
 * SIO_OA_AFS_OA009Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */

    package out.deal.afs.kq009;

    /*
     *  SIO_OA_AFS_OA009Service java interface
     */

    public interface SIO_OA_AFS_OA009Service {
          

        /**
          * Auto generated method signature
          * 
                    * @param mT_OA_AFS_OA0090
                
         */

         
                     public out.deal.afs.kq009.MT_OA_AFS_OA009_REP sIO_OA_AFS_OA009(

                        out.deal.afs.kq009.MT_OA_AFS_OA009 mT_OA_AFS_OA0090)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param mT_OA_AFS_OA0090
            
          */
        public void startsIO_OA_AFS_OA009(

            out.deal.afs.kq009.MT_OA_AFS_OA009 mT_OA_AFS_OA0090,

            final out.deal.afs.kq009.SIO_OA_AFS_OA009ServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    