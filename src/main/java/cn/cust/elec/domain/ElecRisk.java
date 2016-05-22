package cn.cust.elec.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class ElecRisk implements Serializable {
	private String riskID;
	private Date riskdate;
	private String risktype;
	private String riskrank;
	private String riskdetail;
	
	//private Set<ElecUserFile> elecUserFiles = new HashSet<ElecUserFile>();
	private Set<ElecDevice> elecDevices = new HashSet<ElecDevice>();
	
	public Set<ElecDevice> getElecDevices() {
		return elecDevices;
	}
	public void setElecDevices(Set<ElecDevice> elecDevices) {
		this.elecDevices = elecDevices;
	}
	
	public String getRiskID() {
		return riskID;
	}
	public void setRiskID(String riskID) {
		this.riskID = riskID;
	}
	public Date getRiskdate() {
		return riskdate;
	}
	public void setRiskdate(Date riskdate) {
		this.riskdate = riskdate;
	}
	public String getRisktype() {
		return risktype;
	}
	public void setRisktype(String risktype) {
		this.risktype = risktype;
	}
	public String getRiskrank() {
		return riskrank;
	}
	public void setRiskrank(String riskrank) {
		this.riskrank = riskrank;
	}
	public String getRiskdetail() {
		return riskdetail;
	}
	public void setRiskdetail(String riskdetail) {
		this.riskdetail = riskdetail;
	}
	//vo属性
	private Date startDate;
	private Date endDate;

	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	private String[] deviceID;
	private Date[] deviceDate;
	private String[] deviceDetail;

	public String[] getDeviceID() {
		return deviceID;
	}
	public void setDeviceID(String[] deviceID) {
		this.deviceID = deviceID;
	}
	public Date[] getDeviceDate() {
		return deviceDate;
	}
	public void setDeviceDate(Date[] deviceDate) {
		this.deviceDate = deviceDate;
	}
	public String[] getDeviceDetail() {
		return deviceDetail;
	}
	public void setDeviceDetail(String[] deviceDetail) {
		this.deviceDetail = deviceDetail;
	}
	
	
}
