package cn.cust.elec.service;

import java.util.List;

import cn.cust.elec.domain.ElecPopedom;
import cn.cust.elec.domain.ElecRole;

public interface IElecRoleService {

	public List<ElecRole> findRoleList() throws Exception;

	public List<ElecPopedom> findPopedomList() throws Exception;

}
