package cn.cust.elec.dao;

import java.util.List;

import cn.cust.elec.domain.ElecSystemDDL;

public interface IElecSystemDDLDao extends ICommonDao<ElecSystemDDL> {

	public List<ElecSystemDDL> findElecSystemDDLsDistinct() throws Exception;

	public String findDdlNameByKeywordAndDdlCode(String string, String sexID);

}
