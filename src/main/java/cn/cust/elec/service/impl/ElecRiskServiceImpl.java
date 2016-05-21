package cn.cust.elec.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cust.elec.dao.IElecRiskDao;
import cn.cust.elec.dao.IElecSystemDDLDao;
import cn.cust.elec.domain.ElecRisk;
import cn.cust.elec.service.IElecRiskService;

@Service
public class ElecRiskServiceImpl implements IElecRiskService {

	@Autowired
	private IElecRiskDao elecRiskDao;
	@Autowired
	private IElecSystemDDLDao elecSystemDDLDao;
	
	public List<ElecRisk> findRiksListByCondition(ElecRisk elecRisk) throws Exception {

		// 组织查询条件
		String condition = "";
		List<Object> paramsList = new ArrayList<Object>();
		// 用户名称
		String riskrank = elecRisk.getRiskrank();
		if (StringUtils.isNotBlank(riskrank)) {
			condition += " and o.riskrank like ?";
			paramsList.add("%" + riskrank + "%");
		}
		// 所属单位
		String risktype = elecRisk.getRisktype();
		if (StringUtils.isNotBlank(risktype)) {
			condition += " and o.risktype = ?";
			paramsList.add(risktype);
		}
		// 入职开始时间
		Date beginDate = elecRisk.getStartDate();
		if (beginDate != null) {
			condition += " and o.riskdate >= ?";
			paramsList.add(beginDate);
		}
		// 入职结束时间
		Date endDate = elecRisk.getEndDate();
		if (endDate != null) {
			condition += " and o.riskdate <= ?";
			paramsList.add(endDate);
		}
		Object[] params = paramsList.toArray();
		// 排序（按照入职时间的升序排列）
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.riskdate", "asc");
		List<ElecRisk> list = elecRiskDao.findCollectionByConditionNoPage(condition, params, orderby);
		/**
		 * 3：数据字典的转换 使用数据类型和数据项的编号，查询数据字典，获取数据项的值
		 */
		this.convertSystemDDL(list);
		return list;
	}
	private void convertSystemDDL(List<ElecRisk> list) {
		if (list != null && list.size() > 0) {
			for (ElecRisk risk : list) {
				// 性别
				String riskrank = elecSystemDDLDao.findDdlNameByKeywordAndDdlCode("事故级别",risk.getRiskrank() );
				risk.setRiskrank(riskrank);
				// 职位
				String risktype = elecSystemDDLDao.findDdlNameByKeywordAndDdlCode("风险类别", risk.getRisktype());
				risk.setRisktype(risktype);
			}
		}
	}

}
