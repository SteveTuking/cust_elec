package cn.cust.elec.service;

import java.util.List;

import cn.cust.elec.domain.ElecRisk;
import cn.cust.elec.domain.ElecRiskVO;

public interface IElecRiskService {

	public List<ElecRisk> findRiksListByCondition(ElecRisk elecRisk) throws Exception;

	public void save(ElecRisk elecRisk) throws Exception;

	public List<ElecRiskVO> calcElecRisk(ElecRisk elecRisk) throws Exception;

}
