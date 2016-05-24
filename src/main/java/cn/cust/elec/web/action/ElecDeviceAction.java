package cn.cust.elec.web.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cn.cust.elec.domain.ElecDevice;
import cn.cust.elec.service.IElecDeviceService;

@SuppressWarnings("serial")
@Controller
public class ElecDeviceAction extends BaseAction<ElecDevice> {
	
	@Autowired
	private IElecDeviceService elecDeviceService;
	
	private Log log = LogFactory.getLog(this.getClass());
	ElecDevice elecDevice = this.getModel();
	
	public String home(){
		try {
			List<ElecDevice> elecDevices = elecDeviceService.findDeviceByCondition(elecDevice);
			request.setAttribute("elecDevices", elecDevices);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return "home";
	}
}
