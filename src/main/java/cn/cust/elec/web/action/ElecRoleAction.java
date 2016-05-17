package cn.cust.elec.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.cust.elec.domain.ElecPopedom;
import cn.cust.elec.service.IElecRoleService;

@SuppressWarnings("serial")
@Controller
@Scope(value="prototype")
public class ElecRoleAction extends BaseAction<ElecPopedom> {
	@Autowired
	private IElecRoleService elecRoleService;
	
	public String home(){
		return "home";
	}
}
