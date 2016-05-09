package cn.cust.elec.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cust.elec.dao.IElecCommonMsgDao;
import cn.cust.elec.domain.ElecCommonMsg;
import cn.cust.elec.service.IElecCommonMsgService;

@Service
public class ElecCommonMsgServiceImpl implements IElecCommonMsgService {
	
	@Autowired
	private IElecCommonMsgDao elecCommonMsgDao;

	public ElecCommonMsg findCommonMsgs() throws Exception {
		List<ElecCommonMsg> ecms = elecCommonMsgDao.findCollectionByConditionNoPage("", null, null);
		//只返回运行监控的第一条数据
		if(ecms.size()>0){
			return ecms.get(0);
		}else{
			return null;
		}
	}

	@Transactional
	public void save(ElecCommonMsg elecCommonMsg) throws Exception {
		List<ElecCommonMsg> list = elecCommonMsgDao.findCollectionByConditionNoPage("", null, null);
		//如果list!=null:数据表表中存在数据，获取页面传递的2个参数，组织PO对象，执行更新（update）
		if(list!=null && list.size()>0){
			//方案一：先删除再创建
			//方案二：组织PO对象，执行update
			ElecCommonMsg commonMsg = list.get(0);
			commonMsg.setDevRun(elecCommonMsg.getDevRun());
			commonMsg.setStationRun(elecCommonMsg.getStationRun());
			commonMsg.setCreateDate(new Date());
		}
		//如果list==null:数据表表中不存在数据，获取页面传递的2个参数，组织PO对象，执行新增（save）
		else{
			ElecCommonMsg commonMsg = new ElecCommonMsg();
			commonMsg.setCreateDate(new Date());
			elecCommonMsgDao.save(commonMsg);
		}
	}
	
}
