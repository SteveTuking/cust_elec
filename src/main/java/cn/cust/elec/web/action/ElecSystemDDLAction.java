package cn.cust.elec.web.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.cust.elec.domain.ElecSystemDDL;
import cn.cust.elec.service.IElecSystemDDLService;

@SuppressWarnings("serial")
@Controller
@Scope(value = "prototype")
public class ElecSystemDDLAction extends BaseAction<ElecSystemDDL> {
	ElecSystemDDL elecSystemDDL = this.getModel();

	@Autowired
	IElecSystemDDLService elecSystemDDlService;
	private Log log = LogFactory.getLog(this.getClass());

	public String home() {
		try {
			List<ElecSystemDDL> elecSystemDDLs = elecSystemDDlService.findElecSystemDDLDistinct();
			request.setAttribute("list", elecSystemDDLs);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return "home";
	}

	public String edit() {
		// 1：获取数据类型
		String keyword = elecSystemDDL.getKeyword();
		// 2：使用数据类型作为条件，查询数据字典，返回List<ElecSystemDDL>
		List<ElecSystemDDL> list = null;
		try {
			list = elecSystemDDlService.findSystemDDLListByKeyword(keyword);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		request.setAttribute("list", list);
		return "edit";
	}


	public String save() {
		try {
			elecSystemDDlService.saveSystemDDL(elecSystemDDL);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return "save";
	}
}
