package cn.cust.elec.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.cust.elec.dao.IElecSystemDDLDao;
import cn.cust.elec.dao.IElecUserDao;
import cn.cust.elec.dao.IElecUserFileDao;
import cn.cust.elec.domain.ElecUser;
import cn.cust.elec.domain.ElecUserFile;
import cn.cust.elec.service.IElecUserService;
import cn.cust.elec.utils.FileUtils;
import cn.cust.elec.utils.MD5keyBean;

@Service
public class ElecUserServiceImpl implements IElecUserService {

	@Autowired
	private IElecUserDao elecUserDao;
	@Autowired
	private IElecSystemDDLDao elecSystemDDLDao;
	@Autowired
	private IElecUserFileDao elecUserFileDao;

	public List<ElecUser> findUserListByCondition(ElecUser elecUser) throws Exception {
		// 组织查询条件
		String condition = "";
		List<Object> paramsList = new ArrayList<Object>();
		// 用户名称
		String userName = elecUser.getUserName();
		if (StringUtils.isNotBlank(userName)) {
			condition += " and o.userName like ?";
			paramsList.add("%" + userName + "%");
		}
		// 所属单位
		String jctID = elecUser.getJctID();
		if (StringUtils.isNotBlank(jctID)) {
			condition += " and o.jctID = ?";
			paramsList.add(jctID);
		}
		// 入职开始时间
		Date onDutyDateBegin = elecUser.getOnDutyDateBegin();
		if (onDutyDateBegin != null) {
			condition += " and o.onDutyDate >= ?";
			paramsList.add(onDutyDateBegin);
		}
		// 入职结束时间
		Date onDutyDateEnd = elecUser.getOnDutyDateEnd();
		if (onDutyDateEnd != null) {
			condition += " and o.onDutyDate <= ?";
			paramsList.add(onDutyDateEnd);
		}
		Object[] params = paramsList.toArray();
		// 排序（按照入职时间的升序排列）
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.onDutyDate", "asc");
		List<ElecUser> list = elecUserDao.findCollectionByConditionNoPage(condition, params, orderby);
		/**
		 * 3：数据字典的转换 使用数据类型和数据项的编号，查询数据字典，获取数据项的值
		 */
		this.convertSystemDDL(list);
		return list;
	}

	private void convertSystemDDL(List<ElecUser> list) {
		if (list != null && list.size() > 0) {
			for (ElecUser user : list) {
				// 性别
				String sexID = elecSystemDDLDao.findDdlNameByKeywordAndDdlCode("性别", user.getSexID());
				user.setSexID(sexID);
				// 职位
				String postID = elecSystemDDLDao.findDdlNameByKeywordAndDdlCode("职位", user.getPostID());
				user.setPostID(postID);
			}
		}
	}

	public String checkUser(String logonName) throws Exception {
		String message = "";
		if (StringUtils.isNotBlank(logonName)) {
			// 以登录名作为查询条件，查询数据库
			String condition = " and o.logonName = ?";
			Object[] params = { logonName };
			List<ElecUser> list = elecUserDao.findCollectionByConditionNoPage(condition, params, null);
			// 表示数据库存在登录名的记录
			if (list != null && list.size() > 0) {
				message = "2";
			}
			// 表示数据库不存在登录名的记录，可以保存
			else {
				message = "3";
			}
		}
		// 为空
		else {
			message = "1";
		}
		return message;
	}

	@Transactional
	public void saveUser(ElecUser elecUser) throws Exception {
		// 1：遍历多个附件，组织附件的PO对象，完成文件上传，保存用户的附件（多条数据），建立附件表和用户表的关联关系
		this.saveUserFiles(elecUser);
		// 添加md5的密码加密
		this.md5password(elecUser);
		// 获取页面传递的userID
		String userID = elecUser.getUserID();
		// 更新（update）
		if (StringUtils.isNotBlank(userID)) {
			// 组织PO对象，执行更新（1条）
			elecUserDao.update(elecUser);
		}
		// 新增（save）
		else {
			// 2：组织PO对象，保存用户（1条数据）
			elecUserDao.save(elecUser);
		}

	}
	private void md5password(ElecUser elecUser) {
		//获取加密前的密码
		String logonPwd = elecUser.getLogonPwd();
		//加密后的密码
		String md5password = "";
		//如果没有填写密码，设置初始密码为123
		if(StringUtils.isBlank(logonPwd)){
			logonPwd = "123";
		}
		//获取修改用户之前的密码
		String password = elecUser.getPassword();
		//编辑的时候，没有修改密码的时候，是不需要加密的
		if(password!=null && password.equals(logonPwd)){
			md5password = logonPwd;
		}
		//新增的时候，或者是编辑的时候修改密码的时候，需要加密的
		else{
			//使用md5加密的时候
			MD5keyBean md5keyBean = new MD5keyBean();
			md5password = md5keyBean.getkeyBeanofStr(logonPwd);
		}
		//放置到ElecUser对象中
		elecUser.setLogonPwd(md5password);
	}

	//遍历多个附件，组织附件的PO对象，完成文件上传，保存用户的附件（多条数据），建立附件表和用户表的关联关系
	private void saveUserFiles(ElecUser elecUser) {
		//上传时间
		Date progressTime = new Date();
		//获取上传的文件
		File [] uploads = elecUser.getUploads();
		//获取上传的文件名
		String [] fileNames = elecUser.getUploadsFileName();
		//获取上传的文件类型
		String [] contentTypes = elecUser.getUploadsContentType();
		//遍历
		if(uploads!=null && uploads.length>0){
			for(int i=0;i<uploads.length;i++){
				//组织附件的PO对象
				ElecUserFile elecUserFile = new ElecUserFile();
				elecUserFile.setFileName(fileNames[i]);//文件件名
				elecUserFile.setProgressTime(progressTime);//上传时间
				/**将文件上传，同时返回路径path*/
				String fileURL = FileUtils.fileUploadReturnPath(uploads[i],fileNames[i],"用户管理");
				elecUserFile.setFileURL(fileURL);//上传路径（保存，应用与下载）
				elecUserFile.setElecUser(elecUser);//重要：建立关联关系，与用户建立关系，如果不建立，否则外键为null
				elecUserFileDao.save(elecUserFile);
			}
		}
		
	}

	public ElecUser findUserListByUserID(String userID) throws Exception {
		ElecUser elecUser = elecUserDao.findObjectByID(userID);
		return elecUser;
	}

	public ElecUserFile findUserFileByID(String fileID) throws Exception {
		ElecUserFile elecUserFile = this.elecUserFileDao.findObjectByID(fileID);
		return elecUserFile;
	}

	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void deleteByUserID(String userID) throws Exception {
		String [] userIDs = userID.split(", ");
		if(userIDs!=null && userIDs.length>0){
			//获取每个用户ID
			for(String uid:userIDs){
				//使用用户ID，查询用户对象，获取当前用户具有的附件
				ElecUser user = elecUserDao.findObjectByID(uid);
				Set<ElecUserFile> elecUserFiles = user.getElecUserFiles();
				//遍历用户的附件
				if(elecUserFiles!=null && elecUserFiles.size()>0){
					for(ElecUserFile elecUserFile:elecUserFiles){
						//1：删除该用户对应的文件
						//获取路径
						String path = ServletActionContext.getServletContext().getRealPath("")+elecUserFile.getFileURL();
						File file = new File(path);
						if(file.exists()){
							//删除文件
							file.delete();
						}
						//删除每个用户的附件
						//2：删除该用户对应的用户附件表数据(级联删除)
						elecUserFileDao.deleteObjectByIds(elecUserFile.getFileID());
						//<set name="elecUserFiles" table="Elec_User_File" inverse="true" order-by="progressTime desc" cascade="delete">
					}
				}
			}
		}
		
		//3：删除用户表的信息
		elecUserDao.deleteObjectByIds(userIDs);
	}

	@Override
	public ElecUser findUserByLogonName(String name) {
		String condition = "";
		List<Object> paramsList = new ArrayList<Object>();
		if(StringUtils.isNotBlank(name)){
			condition += " and o.logonName=?";
			paramsList.add(name);
		}
		Object [] params = paramsList.toArray();
		//查询用户信息
		List<ElecUser> list = elecUserDao.findCollectionByConditionNoPage(condition, params, null);
		//返回惟一值
		ElecUser elecUser = null;
		if(list!=null && list.size()>0){
			elecUser = list.get(0);
		}
		return elecUser;
	}

}
