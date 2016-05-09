package cn.cust.elec.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cust.elec.dao.IElecTextDao;
import cn.cust.elec.domain.ElecText;
import cn.cust.elec.service.IElecTextService;

@Service
public class ElecTextServiceImpl implements IElecTextService {

	@Autowired
	private IElecTextDao elecTextDao;

	@Transactional
	public void saveElecText(ElecText elecText) throws Exception {
		elecTextDao.save(elecText);
	}

	public List<ElecText> findCollectionByConditionNoPage(ElecText elecText) {
		// 查询条件
		String condition = "";
		// 查询条件对应的参数
		List<Object> paramsList = new ArrayList<Object>();
		if (StringUtils.isNotBlank(elecText.getTextName())) {
			condition += " and o.textName like ?";
			paramsList.add("%" + elecText.getTextName() + "%");
		}
		if (StringUtils.isNotBlank(elecText.getTextRemark())) {
			condition += " and o.textRemark like ?";
			paramsList.add("%" + elecText.getTextRemark() + "%");
		}
		// 传递可变参数
		Object[] params = paramsList.toArray();
		// 排序
		Map<String, String> orderby = new LinkedHashMap<String, String>();// 有序
		orderby.put("o.textDate", "asc");
		orderby.put("o.textName", "desc");
		// 查询
		List<ElecText> list = elecTextDao.findCollectionByConditionNoPage(condition, params, orderby);
		return list;
	}

	public List<ElecText> findElecText() {
		List<ElecText> list = elecTextDao.findCollectionByConditionNoPage("", null, null);
		return list;
	}

}
