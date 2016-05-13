package cn.cust.elec.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import cn.cust.elec.dao.IElecSystemDDLDao;
import cn.cust.elec.domain.ElecSystemDDL;

@Repository
public class ElecSystemDDLDaoImpl extends CommonDaoImpl<ElecSystemDDL>implements IElecSystemDDLDao {

	@SuppressWarnings("unchecked")
	public List<ElecSystemDDL> findElecSystemDDLsDistinct() throws Exception {
		// 返回的List集合
		List<ElecSystemDDL> systemList = new ArrayList<ElecSystemDDL>();
		String hql = "SELECT DISTINCT o.keyword FROM ElecSystemDDL o";
		List<Object> list = this.getHibernateTemplate().find(hql);
		// 组织页面返回的结果
		if (list != null && list.size() > 0) {
			for (Object o : list) {
				ElecSystemDDL elecSystemDDL = new ElecSystemDDL();
				// 数据类型
				elecSystemDDL.setKeyword(o.toString());
				systemList.add(elecSystemDDL);
			}
		}
		return systemList;
	}

	public String findDdlNameByKeywordAndDdlCode(final String keyword, final String ddlCode){
		final String hql = "select o.ddlName from ElecSystemDDL o where o.keyword=? and o.ddlCode=?";
		List<Object> list = this.getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setParameter(0, keyword);
				query.setParameter(1, Integer.parseInt(ddlCode));
				return query.list();
			}
			
		});
		//返回数据项的值
		String ddlName = "";
		if(list!=null && list.size()>0){
			Object o = list.get(0);
			ddlName = o.toString();
		}
		return ddlName;
	}
}
