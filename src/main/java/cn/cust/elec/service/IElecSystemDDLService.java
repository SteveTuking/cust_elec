package cn.cust.elec.service;

import java.util.List;

import cn.cust.elec.domain.ElecSystemDDL;

public interface IElecSystemDDLService {

	public List<ElecSystemDDL> findElecSystemDDLDistinct() throws Exception;

	public List<ElecSystemDDL> findSystemDDLListByKeyword(String keyword) throws Exception;

	public void saveSystemDDL(ElecSystemDDL elecSystemDDL) throws Exception;

}
