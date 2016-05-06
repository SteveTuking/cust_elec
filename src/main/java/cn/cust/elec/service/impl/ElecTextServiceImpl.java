package cn.cust.elec.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cust.elec.dao.IElecTextDao;
import cn.cust.elec.domain.ElecText;
import cn.cust.elec.service.IElecTextService;

@Service
public class ElecTextServiceImpl implements IElecTextService {
	
	@Autowired
	private IElecTextDao elecTextDao;
	
	@Transactional
	public void saveElecText(ElecText elecText) throws Exception {
		elecTextDao.save(elecText);
	}

}
