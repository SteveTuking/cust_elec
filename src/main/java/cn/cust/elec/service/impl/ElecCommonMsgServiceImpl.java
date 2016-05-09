package cn.cust.elec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cust.elec.dao.IElecCommonMsgDao;
import cn.cust.elec.domain.ElecCommonMsg;
import cn.cust.elec.service.IElecCommonMsgService;

@Service
public class ElecCommonMsgServiceImpl implements IElecCommonMsgService {
	
	@Autowired
	private IElecCommonMsgDao elecCommonMsgDao;

	public ElecCommonMsg findCommonMsgs() throws Exception {
		List<ElecCommonMsg> ecms = elecCommonMsgDao.findCollectionByConditionNoPage("", null, null);
		//只返回运行监控的第一条数据
		if(ecms.size()>0){
			return ecms.get(0);
		}else{
			return null;
		}
	}
	
}
