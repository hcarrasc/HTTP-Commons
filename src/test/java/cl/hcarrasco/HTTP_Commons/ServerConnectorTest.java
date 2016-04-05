package cl.hcarrasco.HTTP_Commons;

import org.junit.Test;
import junit.framework.TestCase;

/**
 * Unit test for Application.
 */
public class ServerConnectorTest extends TestCase{

    @Test
    public void checkCorrectConnectionByHost(){
    	String host = "hcarrasco.cl";
    	ServerConnector sconnector = new ServerConnector();
    	assertNotNull(sconnector.getIPfromHostName(host));
    }
    
    @Test
    public void checkCorrectConnectionByIP(){
    	String IPstr = "127.0.0.1";
    	ServerConnector sconnector = new ServerConnector();
    	assertNotNull(sconnector.getHostNameFromIP(IPstr));
    }
    
}
