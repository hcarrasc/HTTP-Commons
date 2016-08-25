package cl.hcarrasco.httpcommons.monitorserver;

public class ServerModel {
	private String ip;
	private int port;
	private int delayStartTime;
	private int intervalCheckTime;
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getDelayStartTime() {
		return delayStartTime;
	}
	public void setDelayStartTime(int delayStartTime) {
		this.delayStartTime = delayStartTime;
	}
	public int getIntervalCheckTime() {
		return intervalCheckTime;
	}
	public void setIntervalCheckTime(int intervalCheckTime) {
		this.intervalCheckTime = intervalCheckTime;
	}
	

}
