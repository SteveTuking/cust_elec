package cn.cust.elec.dao.impl;

import org.springframework.stereotype.Repository;

import cn.cust.elec.dao.IElecTextDao;
import cn.cust.elec.domain.ElecText;


/**
 * @Repository(IElecTextDao.SERVICE_NAME)
 * 相当于在spring容器中定义：
 * <bean id="com.itheima.elec.dao.impl.ElecTextDaoImpl" class="com.itheima.elec.dao.impl.ElecTextDaoImpl">
 *
 */
@Repository
public class ElecTextDaoImpl  extends CommonDaoImpl<ElecText> implements IElecTextDao {

}
