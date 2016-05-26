package cn.cust.elec.service;

import java.util.List;

import cn.cust.elec.domain.ElecUser;
import cn.cust.elec.domain.ElecUserFile;

public interface IElecUserService {

	public List<ElecUser> findUserListByCondition(ElecUser elecUser) throws Exception;

	public String checkUser(String logonName) throws Exception;

	public void saveUser(ElecUser elecUser) throws Exception;

	public ElecUser findUserListByUserID(String userID) throws Exception;

	public ElecUserFile findUserFileByID(String fileID) throws Exception;

	public void deleteByUserID(String userID) throws Exception;

	public ElecUser findUserByLogonName(String name);

}
