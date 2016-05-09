package cn.cust.elec.web.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cn.cust.elec.domain.ElecCommonMsg;
import cn.cust.elec.service.IElecCommonMsgService;
import cn.cust.elec.utils.ValueUtils;

@SuppressWarnings("serial")
@Controller
public class ElecCommonMsgAction extends BaseAction<ElecCommonMsg> {
	ElecCommonMsg elecCommonMsg = this.getModel();
	
	@Autowired
	private IElecCommonMsgService elecCommonMsgService;
	
	//日志
	private Log log = LogFactory.getLog(this.getClass());
	public String home(){
		try {
			ElecCommonMsg commonMsg = elecCommonMsgService.findCommonMsgs();
			//将对象压入值栈
			ValueUtils.push(commonMsg);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return "home";
	}
	
	public String save(){
		
		try {
			elecCommonMsgService.save(elecCommonMsg);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return "save";
	}
	
	public String actingView(){
		try {
			ElecCommonMsg commonMsg = elecCommonMsgService.findCommonMsgs();
			//将对象压入值栈
			ValueUtils.push(commonMsg);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			e.printStackTrace();
		}
		return "actingView";
	}
	
}
