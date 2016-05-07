package cn.cust.elec.web.action;

import java.lang.reflect.ParameterizedType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


@SuppressWarnings("serial")
public class BaseAction<T> extends ActionSupport implements ModelDriven<T>,ServletRequestAware,ServletResponseAware {
	T entity ;
	private Log log = LogFactory.getLog(this.getClass());
	HttpServletRequest request;
	HttpServletResponse response;
	
	@SuppressWarnings("unchecked")
	public BaseAction(){
		/**
		 * 泛型转换
		 */
		ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
		@SuppressWarnings("rawtypes")
		Class bean = (Class) parameterizedType.getActualTypeArguments()[0];
		try {
			entity = (T) bean.newInstance();
		} catch (Exception e) {
			log.error("the genericity is cast exception ",e);
			e.printStackTrace();
		}
	}
	
	public T getModel() {
		return entity;
	}

	public void setServletResponse(HttpServletResponse res) {
		this.response = res;
	}

	public void setServletRequest(HttpServletRequest req) {
		this.request = req;
	}

}
