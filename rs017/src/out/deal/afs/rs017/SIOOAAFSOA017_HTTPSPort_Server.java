
package out.deal.afs.rs017;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 3.0.16
 * 2018-06-02T23:11:56.313+08:00
 * Generated source version: 3.0.16
 * 
 */
 
public class SIOOAAFSOA017_HTTPSPort_Server{

    protected SIOOAAFSOA017_HTTPSPort_Server() throws java.lang.Exception {
        System.out.println("Starting Server");
        Object implementor = new HTTPS_PortImpl();
        String address = "https://piqas.topscore.com.cn:50001/XISOAPAdapter/MessageServlet?senderParty=&senderService=BS_BK2&receiverParty=&receiverService=&interface=SIO_OA_AFS_OA017&interfaceNamespace=urn%3Abk2%3Aafs%3Adeal%3Aout";
        Endpoint.publish(address, implementor);
    }
    
    public static void main(String args[]) throws java.lang.Exception { 
        new SIOOAAFSOA017_HTTPSPort_Server();
        System.out.println("Server ready..."); 
        
        Thread.sleep(5 * 60 * 1000); 
        System.out.println("Server exiting");
        System.exit(0);
    }
}
