package cn.cust.elec.web.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import cn.cust.elec.domain.ElecText;
import cn.cust.elec.service.IElecTextService;

@SuppressWarnings("serial")
@Controller
@Scope(value="prototype")
public class ElecTextAction extends BaseAction<ElecText> {
	
	@Autowired
	private IElecTextService elecTextService;
	private Log log = LogFactory.getLog(this.getClass());
	ElecText elecText = this.getModel();
	public String save(){
		
		try {
			elecTextService.saveElecText(elecText);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return "save";
	}


}
