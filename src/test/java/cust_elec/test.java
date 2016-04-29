package cust_elec;

import java.util.Date;
import java.util.UUID;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

import cn.cust.elec.domain.ElecText;

public class test {
	@Test
	public void save(){
		Configuration conf = new Configuration();
		conf.configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session s = sf.openSession();
		Transaction tr = s.beginTransaction();
		ElecText elecText = new ElecText();
		elecText.setTextDate(new Date());
		elecText.setTextID(UUID.randomUUID().toString());
		elecText.setTextName("◊∑∑Á¡ı…Ÿ");
		s.save(elecText);
		
		tr.commit();
		s.close();
	}
}
