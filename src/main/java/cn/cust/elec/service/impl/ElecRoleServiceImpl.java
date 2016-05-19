package cn.cust.elec.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cust.elec.dao.IElecPopedomDao;
import cn.cust.elec.dao.IElecRoleDao;
import cn.cust.elec.dao.IElecRolePopedomDao;
import cn.cust.elec.dao.IElecUserDao;
import cn.cust.elec.domain.ElecPopedom;
import cn.cust.elec.domain.ElecRole;
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
	
	public List<ElecRole> findRoleList() throws Exception {
		Map<String,String> orderby = new LinkedHashMap<String,String>();
		orderby.put("o.roleID", "asc");
		List<ElecRole> roles = elecRoleDao.findCollectionByConditionNoPage("", null, orderby);
		return roles;
	}

	public List<ElecPopedom> findPopedomList() throws Exception {
		String condition = " and o.pid = ?";
		Object [] params = {"0"};
		Map<String,String> orderby = new LinkedHashMap<String,String>();
		orderby.put("o.mid", "asc");
		List<ElecPopedom> popedoms = elecPopedomDao.findCollectionByConditionNoPage(condition, params, orderby);
		if(popedoms!=null && popedoms.size()>0){
			for(ElecPopedom elecPopedom:popedoms){
				//获取权限id的值，这个值是下一个pid所对应的值（父子关系）
				String mid = elecPopedom.getMid();
				String condition1 = " and o.pid=?";
				Object [] params1 = {mid};
				Map<String, String> orderby1 = new LinkedHashMap<String, String>();
				orderby.put("o.mid", "asc");
				List<ElecPopedom> list1 = elecPopedomDao.findCollectionByConditionNoPage(condition1, params1, orderby1);
				//将所有字的集合，添加到父中的集合属性
				elecPopedom.setList(list1);
			}
		}
		return popedoms;
	}
	
	
	
	
}
