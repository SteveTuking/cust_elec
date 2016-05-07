package cn.cust.elec.web.action;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.cust.elec.domain.ElecText;

@Controller
public class ElecTextAction extends ActionSupport implements ModelDriven<ElecText> {
	
	private static final long serialVersionUID = -6957518430159378978L;
	ElecText elecText = new ElecText();
	public String save(){
		return "save";
	}

	public ElecText getModel() {
		return elecText;
	}
}
