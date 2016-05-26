package cn.cust.elec.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.cust.elec.domain.ElecRisk;
import cn.cust.elec.domain.ElecRiskVO;

public interface IElecRiskService {

	public List<ElecRisk> findRiksListByCondition(ElecRisk elecRisk) throws Exception;

	public void save(ElecRisk elecRisk) throws Exception;

	public List<ElecRiskVO> calcElecRisk(ElecRisk elecRisk) throws Exception;

	public Map<String, Double> getSourceDate(Date parse, Date parse2) throws Exception;

}
