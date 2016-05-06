

import java.util.Date;
import java.util.UUID;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.cust.elec.dao.IElecTextDao;
import cn.cust.elec.domain.ElecText;

public class test {
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
		IElecTextDao ed = (IElecTextDao)ac.getBean("elecTextDaoImpl");
		
		ElecText elecText = new ElecText();
		elecText.setTextDate(new Date());
		elecText.setTextID(UUID.randomUUID().toString());
		elecText.setTextName("测试dao层的数据");
		elecText.setTextRemark("hello world");
		
		ed.save(elecText);
		
	}
}
