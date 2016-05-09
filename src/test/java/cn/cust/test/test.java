package cn.cust.test;


import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.cust.elec.domain.ElecCommonMsg;
import cn.cust.elec.domain.ElecText;
import cn.cust.elec.service.IElecTextService;
import cn.cust.elec.service.impl.ElecCommonMsgServiceImpl;

public class test {
	
	Log log = LogFactory.getLog(test.class);
	@Test
	public void save(){
		/*Configuration conf = new Configuration();
		conf.configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session s = sf.openSession();
		Transaction tr = s.beginTransaction();
		ElecText elecText = new ElecText();
		elecText.setTextDate(new Date());
		elecText.setTextID(UUID.randomUUID().toString());
		elecText.setTextName("刘少");
		s.save(elecText);
		System.out.println("hello world");
		tr.commit();
		s.close();*/
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		System.out.println("hellow wor ");
		IElecTextService es = (IElecTextService)ac.getBean("elecTextServiceImpl");
		
		ElecText elecText = new ElecText();
		elecText.setTextDate(new Date());
		elecText.setTextID(UUID.randomUUID().toString());
		elecText.setTextName("测试service层的数据");
		elecText.setTextRemark("hello world");
		try {
			es.saveElecText(elecText);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			e.printStackTrace();
		}
		
	}
	@Test
	public void findCollectionByConditionNoPage(){
		//加载spring容器
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		IElecTextService elecTextService = (IElecTextService) ac.getBean("elecTextServiceImpl");
		
		//模型驱动中封装结果
		ElecText elecText = new ElecText();
//		elecText.setTextName("张");
//		elecText.setTextRemark("张");
		List<ElecText> list = elecTextService.findCollectionByConditionNoPage(elecText);
		if(list!=null && list.size()>0){
			for(ElecText text:list){
				System.out.println(text.getTextName()+"   "+text.getTextRemark());
			}
		}
		
	}
	
	@Test
	public void findCollectionByCondition() throws Exception{
		//加载spring容器
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		ElecCommonMsgServiceImpl elecTextService = (ElecCommonMsgServiceImpl) ac.getBean("elecCommonMsgServiceImpl");
		
		//模型驱动中封装结果
		//ElecText elecText = new ElecText();
//		elecText.setTextName("张");
//		elecText.setTextRemark("张");
		ElecCommonMsg e = elecTextService.findCommonMsgs();
		
		System.out.println(e.getStationRun());
		
	}
	
	@Test
	public void findColslectionByCondition() throws Exception{
		//加载spring容器
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		IElecTextService elecTextService = (IElecTextService) ac.getBean("elecTextServiceImpl");
		
		//模型驱动中封装结果
		/*ElecText elecText = new ElecText();
		elecText.setTextName("张");
		elecText.setTextRemark("张");
		List<ElecText> list = elecTextService.findCollectionByConditionNoPage(elecText);*/
		List<ElecText> findElecText = elecTextService.findElecText();
		List<ElecText> list = findElecText;
		if(list!=null && list.size()>0){
			for(ElecText text:list){
				System.out.println(text.getTextName()+"   "+text.getTextRemark());
			}
		}
		//模型驱动中封装结果
		//ElecText elecText = new ElecText();
//		elecText.setTextName("张");
//		elecText.setTextRemark("张");
		
		
	}
	
}
