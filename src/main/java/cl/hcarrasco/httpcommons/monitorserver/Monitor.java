package cl.hcarrasco.httpcommons.monitorserver;

import java.io.IOException;
import java.util.Properties;

import cl.hcarrasco.httpcommons.monitorserver.emailservice.EmailService;

public class Monitor implements Runnable {
	
	private ServerModel serverModel;
	

	public void loadConfigurations(){
		
		Properties prop = new Properties();
		ServerModel serverModel = new ServerModel();
		try {
		    prop.load(Monitor.class.getClassLoader().getResourceAsStream("monitorserver.properties"));
		    serverModel.setIp(prop.getProperty("monitorserver.ip"));
		    serverModel.setPort(Integer.parseInt(prop.getProperty("monitorserver.port")));
		    serverModel.setDelayStartTime(Integer.parseInt(prop.getProperty("monitorserver.delayStartTime")));
		    serverModel.setIntervalCheckTime(Integer.parseInt(prop.getProperty("monitorserver.intervalCheckTime")));
		}
		catch (IOException ex) {
		    ex.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		loadConfigurations();
		serverModel.getIp();
		EmailService emailService = new EmailService();
		emailService.sendEmail(null);
	}
	
}
