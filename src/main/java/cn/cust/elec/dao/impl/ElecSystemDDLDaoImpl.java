package cn.cust.elec.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.cust.elec.dao.IElecSystemDDLDao;
import cn.cust.elec.domain.ElecSystemDDL;

@Repository
public class ElecSystemDDLDaoImpl extends CommonDaoImpl<ElecSystemDDL>implements IElecSystemDDLDao {

	@SuppressWarnings("unchecked")
	public List<ElecSystemDDL> findElecSystemDDLsDistinct() throws Exception {
		// 返回的List集合
		List<ElecSystemDDL> systemList = new ArrayList<ElecSystemDDL>();
		String hql = "SELECT DISTINCT o.keyword FROM ElecSystemDDL o";
		List<Object> list = this.getHibernateTemplate().find(hql);
		// 组织页面返回的结果
		if (list != null && list.size() > 0) {
			for (Object o : list) {
				ElecSystemDDL elecSystemDDL = new ElecSystemDDL();
				// 数据类型
				elecSystemDDL.setKeyword(o.toString());
				systemList.add(elecSystemDDL);
			}
		}
		return systemList;
	}
}
