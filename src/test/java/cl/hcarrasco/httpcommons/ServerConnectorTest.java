package cl.hcarrasco.httpcommons;

import org.junit.Test;

import cl.hcarrasco.httpcommons.ServerData;
import junit.framework.TestCase;

/**
 * Unit test for Application.
 */
public class ServerConnectorTest extends TestCase{

    @Test
    public void checkCorrectConnectionByHost(){
    	String host = "hcarrasco.cl";
    	ServerData sconnector = new ServerData();
    	assertNotNull(sconnector.getIPfromHostName(host));
    }
    
    @Test
    public void checkCorrectConnectionByIP(){
    	String IPstr = "127.0.0.1";
    	ServerData sconnector = new ServerData();
    	assertNotNull(sconnector.getHostNameFromIP(IPstr));
    }
    
}
