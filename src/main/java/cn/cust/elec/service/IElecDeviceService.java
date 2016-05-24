package cn.cust.elec.service;

import java.util.List;

import cn.cust.elec.domain.ElecDevice;

public interface IElecDeviceService {

	public void save(ElecDevice elecDevice) throws Exception;

	public List<ElecDevice> findDeviceByCondition(ElecDevice elecDevice) throws Exception;

}
