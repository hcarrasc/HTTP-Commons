package cl.hcarrasco.httpcommons;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;
import cl.hcarrasco.httpcommons.http.ServerData;
import cl.hcarrasco.httpcommons.monitorserver.Monitor;
import cl.hcarrasco.httpcommons.monitorserver.emailservice.EmailService;
import cl.hcarrasco.httpcommons.tcpserver.ServerSetup;

/**
 * Unit test for Application.
 */
public class ServerConnectorTest {
	
	final static Logger logger = Logger.getLogger(ServerSetup.class);

    @Test
    public void checkSendEmail(){
    	logger.info("Testing sendEmail:");
    	EmailService emailService = new EmailService();
    	emailService.sendEmail(null);
    	logger.info("-----");
    }
	
    @Test
    public void checkCorrectConnectionByHost(){
    	logger.info("Testing checkCorrectConnectionByHost:");
    	String host = "hcarrasco.cl";
    	ServerData sconnector = new ServerData();
    	assertNotNull(sconnector.getIPfromHostName(host));
    	logger.info("-----");
    }
    
    @Test
    public void checkLoadConfigurations(){
    	logger.info("Testing loadConfigurations:");
    	Monitor monitor = new Monitor();
    	monitor.loadConfigurations();
    	logger.info("-----");
    }
    
    @Test
    public void checkCorrectConnectionByIP(){
    	logger.info("Testing checkCorrectConnectionByIP:");
    	String IPstr = "108.163.166.178";
    	ServerData sconnector = new ServerData();
    	assertNotNull(sconnector.getHostNameFromIP(IPstr));
    	logger.info("-----");
    }
    
    @Test
    public void chechServerAvailability(){
    	logger.info("Testing chechServerAvailability:");
    	boolean availability = false;
    	String hostName = "hcarrasco.cl";
    	ServerData sconnector = new ServerData();
    	availability = sconnector.isServerAvailable(hostName);
    	assertTrue(availability);
    	logger.info("-----");
    }
    
}
