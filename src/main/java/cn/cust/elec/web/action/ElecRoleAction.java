package cn.cust.elec.web.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.cust.elec.domain.ElecPopedom;
import cn.cust.elec.domain.ElecRole;
import cn.cust.elec.service.IElecRoleService;

@SuppressWarnings("serial")
@Controller
@Scope(value="prototype")
public class ElecRoleAction extends BaseAction<ElecPopedom> {
	@Autowired
	private IElecRoleService elecRoleService;
	
	private Log log = LogFactory.getLog(this.getClass());
	public String home(){
		try {
			List<ElecRole> elecRoles = elecRoleService.findRoleList();
			List<ElecPopedom> elecPopedoms = elecRoleService.findPopedomList();
			request.setAttribute("popedomList", elecPopedoms);
			request.setAttribute("roleList", elecRoles);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return "home";
	}
}
