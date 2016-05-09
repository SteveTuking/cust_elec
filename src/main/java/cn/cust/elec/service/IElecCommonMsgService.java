package cn.cust.elec.service;

import cn.cust.elec.domain.ElecCommonMsg;

public interface IElecCommonMsgService {

	public ElecCommonMsg findCommonMsgs() throws Exception;

	public void save(ElecCommonMsg elecCommonMsg) throws Exception;
	
}
