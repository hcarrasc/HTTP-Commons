package cl.hcarrasco.httpcommons.tcpserver;

/**
 * @author Hector Carrasco
 */
public class TCPConnection {
	
	
	public void startTCPServer(){
		ServerSetup server = new ServerSetup();
		server.setPort(1234);
		server.setServerStatusFlag("on");
		server.run();
	}
	
}
