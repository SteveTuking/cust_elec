package cn.cust.elec.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.cust.elec.dao.IElecDeviceDao;
import cn.cust.elec.dao.IElecRiskDao;
import cn.cust.elec.dao.IElecSystemDDLDao;
import cn.cust.elec.domain.ElecDevice;
import cn.cust.elec.domain.ElecRisk;
import cn.cust.elec.domain.ElecRiskVO;
import cn.cust.elec.service.IElecRiskService;

@Service
public class ElecRiskServiceImpl implements IElecRiskService {

	@Autowired
	private IElecRiskDao elecRiskDao;
	@Autowired
	private IElecSystemDDLDao elecSystemDDLDao;
	@Autowired
	private IElecDeviceDao elecDeviceDao;
	
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
	@Transactional
	public void save(ElecRisk elecRisk) throws Exception {
		/*String riskID = UUID.randomUUID().toString().replace("-", "");
		elecRisk.setRiskID(riskID);*/
		elecRiskDao.save(elecRisk);
		if(elecRisk.getDeviceDate()!=null){
			this.saveDevices(elecRisk);
		}
	}
	public void saveDevices(ElecRisk elecRisk){
		if(elecRisk.getDeviceDate().length>0){
			for(int i = 0; i < elecRisk.getDeviceDate().length; i++){
				ElecDevice elecDevice = new ElecDevice();
				elecDevice.setRiskID(elecRisk.getRiskID());
				//elecDevice.setDeviceID(elecRisk.getDeviceID()[i]);
				elecDevice.setElecRisk(elecRisk);
				elecDevice.setDeviceNum(elecRisk.getDeviceNum()[i]);
				elecDevice.setDeviceDate(elecRisk.getDeviceDate()[i]);
				elecDevice.setDeviceDetail(elecRisk.getDeviceDetail()[i]);
				elecDeviceDao.save(elecDevice);
			}
		}
	}
	public List<ElecRiskVO> calcElecRisk(ElecRisk elecRisk) throws Exception {
		List<ElecRiskVO> elecRiskVOs = new ArrayList<ElecRiskVO>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int start = this.getMon(elecRisk.getStartDate());
		int end = this.getMon(elecRisk.getEndDate());
		int year = this.getYear(elecRisk.getStartDate());
		for(int i = start; i <= end; i++){
			String fstart = "";
			String fend = "";
			if(i<10){
				fstart = year+"-"+"0"+i+"-01";
			}else{
				fstart = year+"-"+i+"-01";
			}
			if(i+1<10){
				fend = year+"-"+"0"+(i+1)+"-01";
			}else{
				fend = year+"-"+(i+1)+"-01";
			}
			
			List<ElecRisk> risks = this.findByDate(this.string2Date(fstart),this.string2Date(fend));
			ElecRiskVO elecRiskVO = new ElecRiskVO();
			elecRiskVO.setTime(this.string2Date(fstart));
			for(ElecRisk er : risks){
				calcVo(er,elecRiskVO);
			}
			elecRiskVOs.add(elecRiskVO);
		}
		return elecRiskVOs;
	}
	public List<ElecRisk> findByDate(Date begin,Date end){
		String condition = "";
		List<Object> paramsList = new ArrayList<Object>();
		Date beginDate = begin;
		if (beginDate != null) {
			condition += " and o.riskdate >= ?";
			paramsList.add(beginDate);
		}
		// 入职结束时间
		Date endDate = end;
		if (endDate != null) {
			condition += " and o.riskdate < ?";
			paramsList.add(endDate);
		}
		Object[] params = paramsList.toArray();
		// 排序（按照入职时间的升序排列）
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.riskdate", "asc");
		List<ElecRisk> list = elecRiskDao.findCollectionByConditionNoPage(condition, params, orderby);
		return list;
	}
	
	public Integer getMon(Date date){
		return Integer.parseInt(new SimpleDateFormat("yyyy-MM-dd").format(date).split("-")[1]);
	}
	public Integer getYear(Date date){
		return Integer.parseInt(new SimpleDateFormat("yyyy-MM-dd").format(date).split("-")[0]);
	}
	public Date string2Date(String date) throws ParseException{
		return new SimpleDateFormat("yyyy-MM-dd").parse(date);
	}
	public void calcVo(ElecRisk elecRisk,ElecRiskVO elecRiskVO){
		switch(elecRisk.getRisktype()){
			case "1" : elecRiskVO.setBreakRule(elecRiskVO.getBreakRule()+1);break;
			case "2" : elecRiskVO.setPeopleFourOne(elecRiskVO.getPeopleFourOne()+1);;break;
			case "3" : elecRiskVO.setElecFiveOne(elecRiskVO.getElecFiveOne()+1);;break;
			case "4" : elecRiskVO.setDeviceFourOne(elecRiskVO.getDeviceFourOne()+1);;break;
			case "5" : elecRiskVO.setSimplePeople(elecRiskVO.getSimplePeople()+1);;break;
			case "6" : elecRiskVO.setMidPeople(elecRiskVO.getMidPeople()+1);;break;
			case "7" : elecRiskVO.setSimpleElec(elecRiskVO.getSimpleElec()+1);;break;
			case "8" : elecRiskVO.setMidElec(elecRiskVO.getMidElec()+1);;break;
			case "9" : elecRiskVO.setSimpleDevice(elecRiskVO.getSimpleDevice()+1);;break;
			case "10" : elecRiskVO.setMidDevice(elecRiskVO.getMidDevice()+1);;break;
			case "11" : elecRiskVO.setSuperPeople(elecRiskVO.getSuperPeople()+1);;break;
			case "12" : elecRiskVO.setSsuperPeople(elecRiskVO.getSsuperPeople()+1);;break;
			case "13" : elecRiskVO.setSuperElec(elecRiskVO.getSuperElec()+1);;break;
			case "14" : elecRiskVO.setSuperDevice(elecRiskVO.getSuperDevice()+1);;break;
			case "15" : elecRiskVO.setSsuperDevice(elecRiskVO.getSsuperDevice()+1);;break;
		}
	}
	@Override
	public Map<String, Double> getSourceDate(Date parse, Date parse2) throws Exception { 
		Map<String,Double> result = new LinkedHashMap<String,Double>();
		ElecRisk er = new ElecRisk();
		er.setStartDate(parse);
		er.setEndDate(parse2);
		for(int i = 1; i <= 3; i++){
			er.setRiskrank(""+i);
			String rank = elecSystemDDLDao.findDdlNameByKeywordAndDdlCode("事故级别", i+"");
			List<ElecRisk> ers = this.findRiksListByCondition(er);
			int size = ers.size();
			result.put(rank+size, (double) size);
		}
		return result;
	}
}
