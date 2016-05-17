package cn.cust.elec.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cust.elec.dao.IElecPopedomDao;
import cn.cust.elec.dao.IElecRoleDao;
import cn.cust.elec.dao.IElecRolePopedomDao;
import cn.cust.elec.dao.IElecUserDao;
import cn.cust.elec.service.IElecRoleService;

@Service
public class ElecRoleServiceImpl implements IElecRoleService {
	@Autowired
	private IElecUserDao elecUserDao;
	@Autowired
	private IElecRoleDao elecRoleDao;
	@Autowired
	private IElecPopedomDao elecPopedomDao;
	@Autowired
	private IElecRolePopedomDao elecRolePopedomDao;
}
