package cn.cust.elec.domain;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class ElecRisk implements Serializable {
	private String riskID;
	private Date riskdate;
	private String risktype;
	private String riskrank;
	private String riskdetail;
	
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
}
