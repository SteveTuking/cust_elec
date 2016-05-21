package cn.cust.elec.service;

import java.util.List;

import cn.cust.elec.domain.ElecRisk;

public interface IElecRiskService {

	public List<ElecRisk> findRiksListByCondition(ElecRisk elecRisk) throws Exception;

}
