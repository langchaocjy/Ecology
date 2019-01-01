
/**
 * SIO_FI_OA_AFS_OA038ServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */

    package out.deal.afs.zc038;

    /**
     *  SIO_FI_OA_AFS_OA038ServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class SIO_FI_OA_AFS_OA038ServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public SIO_FI_OA_AFS_OA038ServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public SIO_FI_OA_AFS_OA038ServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for sIO_FI_OA_AFS_OA038 method
            * override this method for handling normal response from sIO_FI_OA_AFS_OA038 operation
            */
           public void receiveResultsIO_FI_OA_AFS_OA038(
                    out.deal.afs.zc038.MT_FI_OA_AFS_OA038_REP result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from sIO_FI_OA_AFS_OA038 operation
           */
            public void receiveErrorsIO_FI_OA_AFS_OA038(java.lang.Exception e) {
            }
                


    }
    