package cn.cust.elec.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cust.elec.dao.IElecCommonMsgDao;
import cn.cust.elec.service.IElecCommonMsgService;

@Service
public class ElecCommonMsgServiceImpl implements IElecCommonMsgService {
	
	@Autowired
	private IElecCommonMsgDao elecCommonMsgDao;
	
}
