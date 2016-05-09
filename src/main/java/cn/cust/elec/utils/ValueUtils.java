package cn.cust.elec.utils;

import org.apache.struts2.ServletActionContext;

public class ValueUtils {
	public static void push(Object object){
		ServletActionContext.getContext().getValueStack().push(object); 
	}
}
