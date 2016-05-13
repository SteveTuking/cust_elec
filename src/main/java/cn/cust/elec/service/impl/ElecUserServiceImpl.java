package cn.cust.elec.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.cust.elec.dao.IElecSystemDDLDao;
import cn.cust.elec.dao.IElecUserDao;
import cn.cust.elec.domain.ElecUser;
import cn.cust.elec.service.IElecUserService;

@Service
public class ElecUserServiceImpl implements IElecUserService {

	@Autowired
	private IElecUserDao elecUserDao;
	@Autowired
	private IElecSystemDDLDao elecSystemDDLDao;
	
	public List<ElecUser> findUserListByCondition(ElecUser elecUser) throws Exception {
		// 组织查询条件
		String condition = "";
		List<Object> paramsList = new ArrayList<Object>();
		// 用户名称
		String userName = elecUser.getUserName();
		if (StringUtils.isNotBlank(userName)) {
			condition += " and o.userName like ?";
			paramsList.add("%" + userName + "%");
		}
		// 所属单位
		String jctID = elecUser.getJctID();
		if (StringUtils.isNotBlank(jctID)) {
			condition += " and o.jctID = ?";
			paramsList.add(jctID);
		}
		// 入职开始时间
		Date onDutyDateBegin = elecUser.getOnDutyDateBegin();
		if (onDutyDateBegin != null) {
			condition += " and o.onDutyDate >= ?";
			paramsList.add(onDutyDateBegin);
		}
		// 入职结束时间
		Date onDutyDateEnd = elecUser.getOnDutyDateEnd();
		if (onDutyDateEnd != null) {
			condition += " and o.onDutyDate <= ?";
			paramsList.add(onDutyDateEnd);
		}
		Object[] params = paramsList.toArray();
		// 排序（按照入职时间的升序排列）
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.onDutyDate", "asc");
		List<ElecUser> list = elecUserDao.findCollectionByConditionNoPage(condition, params, orderby);
		/**
		 * 3：数据字典的转换 使用数据类型和数据项的编号，查询数据字典，获取数据项的值
		 */
		this.convertSystemDDL(list);
		return list;
	}
	
	private void convertSystemDDL(List<ElecUser> list) {
		if(list!=null && list.size()>0){
			for(ElecUser user:list){
				//性别
				String sexID = elecSystemDDLDao.findDdlNameByKeywordAndDdlCode("性别",user.getSexID());
				user.setSexID(sexID);
				//职位
				String postID = elecSystemDDLDao.findDdlNameByKeywordAndDdlCode("职位",user.getPostID());
				user.setPostID(postID);
			}
		}
	}

	public String checkUser(String logonName) throws Exception {
		String message = "";
		if(StringUtils.isNotBlank(logonName)){
			//以登录名作为查询条件，查询数据库
			String condition = " and o.logonName = ?";
			Object [] params = {logonName};
			List<ElecUser> list = elecUserDao.findCollectionByConditionNoPage(condition, params, null);
			//表示数据库存在登录名的记录
			if(list!=null && list.size()>0){
				message = "2";
			}
			//表示数据库不存在登录名的记录，可以保存
			else{
				message = "3";
			}
		}
		//为空
		else{
			message = "1";
		}
		return message;
	}
	
}
