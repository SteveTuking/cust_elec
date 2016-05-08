package cn.cust.elec.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cn.cust.elec.domain.ElecCommonMsg;
import cn.cust.elec.service.IElecCommonMsgService;

@SuppressWarnings("serial")
@Controller
public class ElecCommonMsgAction extends BaseAction<ElecCommonMsg> {
	ElecCommonMsg elecCommonMsg = this.getModel();
	
	@Autowired
	private IElecCommonMsgService elecCommonMsgService;
	
	public String home(){
		return "home";
	}
}
