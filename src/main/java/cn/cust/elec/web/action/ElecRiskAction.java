package cn.cust.elec.web.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cn.cust.elec.domain.ElecRisk;
import cn.cust.elec.domain.ElecRiskVO;
import cn.cust.elec.domain.ElecSystemDDL;
import cn.cust.elec.service.IElecRiskService;
import cn.cust.elec.service.IElecSystemDDLService;
import cn.cust.elec.utils.JfreechartUtil;

@SuppressWarnings("serial")
@Controller
public class ElecRiskAction extends BaseAction<ElecRisk> {
	ElecRisk elecRisk = this.getModel();

	@Autowired
	private IElecSystemDDLService elecSystemDDLService;
	@Autowired
	private IElecRiskService elecRiskService;

	private Log log = LogFactory.getLog(this.getClass());

	public String risk() {
		// 加载数据字典
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

	public String add() {
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

	public String save() {
		try {
			elecRiskService.save(elecRisk);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return "close";
	}

	public String calcRisk() {
		if (elecRisk.getEndDate() != null) {
			try {
				List<ElecRiskVO> elecRiskVOs = elecRiskService.calcElecRisk(elecRisk);
				request.setAttribute("elemRiskVOs", elecRiskVOs);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
		return "calcRisk";
	}

	public String piePng() {
		/*String sbtime = (String)request.getAttribute("stime");
		String setime = (String) request.getAttribute("etime");*/
		Map<String, Double> date = null;
		try {
			date = elecRiskService.getSourceDate(elecRisk.getStartDate(), elecRisk.getEndDate());
			String rank = calcRank(date)/330+"";
			DefaultPieDataset dataset = JfreechartUtil.getDataset(date);
			JFreeChart chart = JfreechartUtil.createChart(dataset, "风险系数"+rank);
			ChartUtilities.writeChartAsJPEG(response.getOutputStream(), 1.0f, chart, 400, 300,
				        null);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public double calcRank(Map<String,Double> date){
		double rank = 0.0;
		int i = 0;
		double[] hailixiyin = {1,29,300};
		for(Entry<String, Double> entry : date.entrySet()){
			rank += entry.getValue()*hailixiyin[i];
			i++;
		}
		return rank;
	}
}
