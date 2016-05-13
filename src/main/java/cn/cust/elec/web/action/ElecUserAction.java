package cn.cust.elec.web.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.cust.elec.domain.ElecSystemDDL;
import cn.cust.elec.domain.ElecUser;
import cn.cust.elec.service.IElecSystemDDLService;
import cn.cust.elec.service.IElecUserService;
import cn.cust.elec.utils.ValueUtils;

@SuppressWarnings("serial")
@Controller
@Scope(value="prototype")
public class ElecUserAction extends BaseAction<ElecUser> {
	@Autowired
	private IElecUserService elecUserService;
	@Autowired
	private IElecSystemDDLService elecSystemDDLService;
	private Log log = LogFactory.getLog(this.getClass());
	
	ElecUser elecUser = this.getModel();
	
	public String home(){
		try {
			List<ElecSystemDDL> jctList = elecSystemDDLService.findSystemDDLListByKeyword("所属单位");
			request.setAttribute("jctList", jctList);
			List<ElecUser> users = elecUserService.findUserListByCondition(elecUser);
			request.setAttribute("userList", users);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "home";
	}
	//跳转到用户的添加界面
	public String add(){
		initSystemDDL();
		return "add";
	}
	//初始化数据字典
	private void initSystemDDL() {
		List<ElecSystemDDL> sexList;
		try {
			sexList = elecSystemDDLService.findSystemDDLListByKeyword("性别");
			request.setAttribute("sexList", sexList);
			List<ElecSystemDDL> postList = elecSystemDDLService.findSystemDDLListByKeyword("职位");
			request.setAttribute("postList", postList);
			List<ElecSystemDDL> jctList = elecSystemDDLService.findSystemDDLListByKeyword("所属单位");
			request.setAttribute("jctList", jctList);
			List<ElecSystemDDL> isDutyList = elecSystemDDLService.findSystemDDLListByKeyword("是否在职");
			request.setAttribute("isDutyList", isDutyList);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
	}
	public String findJctUnit(){
		// 1：获取所属单位下的数据项的值
		String jctID = elecUser.getJctID();
		// 2：使用该值作为数据类型，查询对应数据字典的值，返回List<ElecSystemDDL>
		List<ElecSystemDDL> list = null;
		try {
			list = elecSystemDDLService.findSystemDDLListByKeyword(jctID);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		// 3：将List<ElecSystemDDL>转换成json的数组，将List集合放置到栈顶
		ValueUtils.push(list);
		return "findJctUnit";
	}
	public String checkUser(){
		//1:获取登录名
		String logonName = elecUser.getLogonName();
		//2:判断登录名是否出现重复
		String message = null;
		try {
			message = elecUserService.checkUser(logonName);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		//放置到栈顶
		elecUser.setMessage(message);//栈顶对象是ElecUser对象
		//ValueUtils.putValueStack(message);//栈顶对象是String类型的属性
		return "checkUser";
	}
	
}
