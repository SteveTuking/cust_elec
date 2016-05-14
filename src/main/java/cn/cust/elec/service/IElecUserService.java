package cn.cust.elec.service;

import java.util.List;

import cn.cust.elec.domain.ElecUser;

public interface IElecUserService {

	public List<ElecUser> findUserListByCondition(ElecUser elecUser) throws Exception;

	public String checkUser(String logonName) throws Exception;

	public void saveUser(ElecUser elecUser) throws Exception;

}
