package cn.cust.elec.web.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.cust.elec.domain.ElecText;
import cn.cust.elec.service.IElecTextService;
import cn.cust.test.test;

@SuppressWarnings("serial")
@Controller
@Scope(value="prototype")
public class ElecTextAction extends ActionSupport implements ModelDriven<ElecText> {
	
	@Autowired
	private IElecTextService elecTextService;
	private Log log = LogFactory.getLog(test.class);
	ElecText elecText = new ElecText();
	public String save(){
		
		try {
			elecTextService.saveElecText(elecText);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return "save";
	}

	public ElecText getModel() {
		return elecText;
	}
}
