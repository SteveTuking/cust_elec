package cn.cust.elec.web.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cn.cust.elec.domain.ElecRisk;
import cn.cust.elec.domain.ElecSystemDDL;
import cn.cust.elec.service.IElecRiskService;
import cn.cust.elec.service.IElecSystemDDLService;

@SuppressWarnings("serial")
@Controller
public class ElecRiskAction extends BaseAction<ElecRisk> {
	ElecRisk elecRisk = this.getModel();
	
	@Autowired
	private IElecSystemDDLService elecSystemDDLService;
	@Autowired
	private IElecRiskService elecRiskService;
	
	private Log log = LogFactory.getLog(this.getClass());
	
	public String risk(){
		//加载数据字典
		try {
			List<ElecSystemDDL> riskranks = elecSystemDDLService.findSystemDDLListByKeyword("事故级别");
			List<ElecSystemDDL> risktypes = elecSystemDDLService.findSystemDDLListByKeyword("风险类别");
			request.setAttribute("riskranks", riskranks);
			request.setAttribute("risktypes", risktypes);
			
			List<ElecRisk> elecRisks = elecRiskService.findRiksListByCondition(elecRisk);
			request.setAttribute("elecRisks", elecRisks);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		return "risk";
	}
	
	public String add(){
		try {
			List<ElecSystemDDL> riskranks = elecSystemDDLService.findSystemDDLListByKeyword("事故级别");
			List<ElecSystemDDL> risktypes = elecSystemDDLService.findSystemDDLListByKeyword("风险类别");
			request.setAttribute("riskranks", riskranks);
			request.setAttribute("risktypes", risktypes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "add";
	}
	
	public String save(){
		try {
			elecRiskService.save(elecRisk);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return "close";
	}
}

