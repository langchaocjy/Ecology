
package out.deal.afs.kq001;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 3.0.16
 * 2018-12-16T11:49:33.392+08:00
 * Generated source version: 3.0.16
 * 
 */
 
public class SIOOAAFSOA001_HTTPPort_Server{

    protected SIOOAAFSOA001_HTTPPort_Server() throws java.lang.Exception {
        System.out.println("Starting Server");
        Object implementor = new HTTP_PortImpl();
        String address = "http://piprd02.topscore.com.cn:50000/XISOAPAdapter/MessageServlet?senderParty=&senderService=BS_BK2&receiverParty=&receiverService=&interface=SIO_OA_AFS_OA001&interfaceNamespace=urn%3Abk2%3Aafs%3Adeal%3Aout";
        Endpoint.publish(address, implementor);
    }
    
    public static void main(String args[]) throws java.lang.Exception { 
        new SIOOAAFSOA001_HTTPPort_Server();
        System.out.println("Server ready..."); 
        
        Thread.sleep(5 * 60 * 1000); 
        System.out.println("Server exiting");
        System.exit(0);
    }
}
