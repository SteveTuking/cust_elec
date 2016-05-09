package cn.cust.elec.web.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.cust.elec.domain.ElecCommonMsg;
import cn.cust.elec.service.IElecCommonMsgService;
import cn.cust.elec.utils.ValueUtils;
import cn.cust.elec.web.form.MenuForm;


@SuppressWarnings("serial")
@Controller
@Scope(value="prototype")
public class ElecMenuAction extends BaseAction<MenuForm> {
	
	MenuForm menuForm = this.getModel();
	
	/**注入运行监控Service*/
	@Autowired
	IElecCommonMsgService elecCommonMsgService;
	//日志
	private Log log = LogFactory.getLog(this.getClass());
	
	/**  
	* @Name: menuHome
	* @Description: 跳转到系统登录的首页
	* @Return: String：跳转到menu/home.jsp
	*/
	public String menuHome(){
		System.out.println(menuForm.getName()+"   "+menuForm.getPassword());
		return "menuHome";
	}
	
	/**标题*/
	public String title(){
		return "title";
	}
	
	/**菜单*/
	public String left(){
		return "left";
	}
	
	/**框架大小改变*/
	public String change(){
		return "change";
	}
	
	/**  
	* @Name: loading
	* @Description: 功能页面的显示
	* @Return: String：跳转到menu/loading.jsp
	*/
	public String loading(){
		//查询设备运行情况，放置到浮动框中
		//1：查询数据库运行监控表的数据，返回惟一ElecCommonMsg
		/*ElecCommonMsg commonMsg = elecCommonMsgService.findCommonMsg();
		//2：将ElecCommonMsg对象压入栈顶，支持表单回显
		ValueUtils.putValueStack(commonMsg);*/
		return "loading";
	}
	
	/**  
	* @Name: logout
	* @Description: 重新登录
	* @Return: String：重定向到index.jsp
	*/
	public String logout(){
		/**清空Session*/
		//指定Session的名称清空
//		request.getSession().removeAttribute(arg0);
		//清空所有Session
		request.getSession().invalidate();
		return "logout";
	}
	
	/**  
	* @Name: alermStation
	* @Description: 显示站点运行情况
	* @Return: String：跳转到menu/alermStation.jsp
	*/
	public String alermStation(){
		ElecCommonMsg commonMsg = null;
		try {
			commonMsg = elecCommonMsgService.findCommonMsgs();
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			e.printStackTrace();
		}
		ValueUtils.push(commonMsg);
		return "alermStation";
	}

	/**  
	* @Name: alermDevice
	* @Description: 显示设备运行情况
	* @Return: String：跳转到menu/alermDevice.jsp
	*/
	public String alermDevice(){
		//1：查询数据库运行监控表的数据，返回惟一ElecCommonMsg
		ElecCommonMsg commonMsg = null;
		try {
			commonMsg = elecCommonMsgService.findCommonMsgs();
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			e.printStackTrace();
		}
		//2：将ElecCommonMsg对象压入栈顶，支持表单回显
		ValueUtils.push(commonMsg);
		return "alermDevice";
	}
	
}
