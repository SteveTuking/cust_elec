package cn.cust.elec.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cust.elec.dao.IElecDeviceDao;
import cn.cust.elec.domain.ElecDevice;
import cn.cust.elec.service.IElecDeviceService;

@Service
public class ElecDeviceServiceImpl implements IElecDeviceService {

	@Autowired
	private IElecDeviceDao elecDeviceDao;
	
	public void save(ElecDevice elecDevice) throws Exception {
		elecDeviceDao.save(elecDevice);
	}

	public List<ElecDevice> findDeviceByCondition(ElecDevice elecDevice) throws Exception{
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.deviceDate", "asc");
		List<ElecDevice> list = elecDeviceDao.findCollectionByConditionNoPage("", null, orderby);
		return list;
	}

}
