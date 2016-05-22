package cn.cust.elec.domain;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class ElecDevice implements Serializable {
	private String deviceID;
	private Date deviceDate;
	private String deviceDetail;
	
	public String getDeviceID() {
		return deviceID;
	}
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}
	public Date getDeviceDate() {
		return deviceDate;
	}
	public void setDeviceDate(Date deviceDate) {
		this.deviceDate = deviceDate;
	}
	public String getDeviceDetail() {
		return deviceDetail;
	}
	public void setDeviceDetail(String deviceDetail) {
		this.deviceDetail = deviceDetail;
	}
	
}
