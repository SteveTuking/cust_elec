package cn.cust.elec.domain;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class ElecDevice implements Serializable {
	private String deviceID;
	private Date deviceDate;
	private String deviceDetail;
	private String riskID;
	private String deviceNum;
	
	public String getDeviceNum() {
		return deviceNum;
	}
	public void setDeviceNum(String deviceNum) {
		this.deviceNum = deviceNum;
	}
	public String getRiskID() {
		return riskID;
	}
	public void setRiskID(String riskID) {
		this.riskID = riskID;
	}
	private ElecRisk elecRisk;
	
	public ElecRisk getElecRisk() {
		return elecRisk;
	}
	public void setElecRisk(ElecRisk elecRisk) {
		this.elecRisk = elecRisk;
	}
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
