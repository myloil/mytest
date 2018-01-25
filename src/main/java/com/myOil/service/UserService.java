/**
 * 
 */
package com.myOil.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.myOil.dao.UserDao;
import com.myOil.entity.Authority;
import com.myOil.entity.Department;
import com.myOil.entity.User;

/**
 * @author Shuai.yang
 *
 */
@Service
@EnableTransactionManagement
public class UserService {

	@Autowired
	private UserDao userDao;

	/*
	 * public boolean checkUserNameIsExist(String userName){ boolean result =
	 * false; int count = userDao.checkUserIsExist(userName); if(count>0){
	 * result = true; } return result; }
	 */

	public User checkLoginUser(String userName, String userPassword) {
		User user = userDao.getUser(userName, userPassword);
		return user;
	}

	public List<User> getAllUsers() {
		List<User> resultList = new ArrayList<User>();
		resultList = userDao.getAllUserList();
		return resultList;
	}

	/*public Map<String,List<Authority>> getUserAllAuthority() {
		Map<String,List<Authority>> resultMap = new HashMap<String, List<Authority>>();
		List<Department> resultList = userDao.getUserAllAuthority();
		Department d=resultList.get(0);
		resultMap.put(String.valueOf(d.getDepartmentId()), d.getAuthorityList());
		for (Object[] object : resultList) {
			Department d = (Department) object[0];
			List<Authority> authroity = d.getAuthorityList();
			System.out.println("----department--"+d.getDepartmentName());
			for(int i=0;i<authroity.size();i++){
				Authority au=authroity.get(i);
				System.out.println(au.getAuthorityUrl());
			}
			Authority a = (Authority) object[1];
			String b = a.getAuthorityUrl();
		}
		return resultMap;
	}*/
	public Map<String, Set<Authority>> getUserAllAuthorityMap() {
		Map<String, Set<Authority>> resultMap = new HashMap<String, Set<Authority>>();
//		List<Authority> authorityList = new ArrayList<Authority>();
		Set<Department> resultList = userDao.getUserAllAuthority();
		for (Department d : resultList) {
			  String key = String.valueOf(d.getDepartmentId());
			  Set<Authority> authoritySet = d.getAuthorityList();
//			  authorityList.addAll(authoritySet);
			  if(!resultMap.containsKey(key)){
				  resultMap.put(key, authoritySet);
			  }
		}
		
		return resultMap;
	}
	
	public Map<String, Set<Authority>> getUserAuthorityMapByDepartmentId(int departmentId) {
		Map<String, Set<Authority>> resultMap = new HashMap<String, Set<Authority>>();
//		List<Authority> authorityList = new ArrayList<Authority>();
		Set<Department> resultList = userDao.getUserAuthorityMapByDepartmentId(departmentId);
		for (Department d : resultList) {
			  String key = String.valueOf(d.getDepartmentId());
			  Set<Authority> authoritySet = d.getAuthorityList();
//			  authorityList.addAll(authoritySet);
			  if(!resultMap.containsKey(key)){
				  resultMap.put(key, authoritySet);
			  }
		}
		
		return resultMap;
	}
	/*
	 * public List getAllUsers() { List resultList = new ArrayList(); List
	 * tmpList = userDao.getAllUserList(); populatePoToVoForList(tmpList,
	 * resultList); return resultList; }
	 * 
	 * public int getAllUserCount(int loginUserId){ return
	 * userDao.getAllUserCount(loginUserId); }
	 * 
	 * public UserDto getUserById(int id){ User c = userDao.getUserById(id);
	 * 
	 * return populatePoToVo(c); }
	 * 
	 * public int getUserByCriteriaCount(UserDto cvo){ User c = null; if (null
	 * != cvo) { c = populateVoToPo(cvo); } return
	 * userDao.getUserByCriteriaCount(c); }
	 * 
	 * public List getAllUserByPage(int startRow, int pageSize, UserDto cvo){
	 * List resultList = new ArrayList(); User c = null; if (null != cvo) { c =
	 * populateVoToPo(cvo); } List tmpList =
	 * userDao.getAllUserByPage(startRow,pageSize, c);
	 * populatePoToVoForList(tmpList, resultList); return resultList; }
	 * 
	 * public void insertUser(UserDto cvo){ User c = populateVoToPo(cvo);
	 * userDao.insertUser(c); }
	 * 
	 * public void deleteUser(String[] idsList){ userDao.deleteUser(idsList); }
	 * 
	 * public void updateUser(UserDto cvo){ User c = populateVoToPo(cvo);
	 * userDao.updateUser(c); }
	 * 
	 * public List getUserByDisplayName(String displayName){ List resultList =
	 * new ArrayList(); List tmpList =
	 * userDao.getUserByDisplayName(displayName); populatePoToVoForList(tmpList,
	 * resultList); return resultList; }
	 * 
	 * private List populatePoToVoForList(List tmpList, List resultList) { for
	 * (int i = 0; i < tmpList.size(); i++) { User user = (User) tmpList.get(i);
	 * resultList.add(populatePoToVo(user)); } return resultList; }
	 * 
	 * 
	 * private User populateVoToPo(UserDto uservo) { User user = new User();
	 * user
	 * .setUserId(null!=uservo.getUserId()?Integer.parseInt(uservo.getUserId(
	 * )):Constants.VALIDATE_INT); user.setUserName(uservo.getUserName());
	 * user.setUserPassword
	 * (null!=uservo.getUserPassword()&&uservo.getUserPassword
	 * ().length()>0?EncryptUtil.encrypt(uservo.getUserPassword()):"");
	 * user.setPhone(uservo.getPhone()); user.setIdCard(uservo.getIdCard());
	 * user
	 * .setDepartmentId(null!=uservo.getDepartmentId()&&uservo.getDepartmentId
	 * ().
	 * length()>0?Integer.parseInt(uservo.getDepartmentId()):Constants.VALIDATE_INT
	 * ); user.setDepartmentName(uservo.getDepartmentName());
	 * user.setGasStationId
	 * (null!=uservo.getGasStationId()&&uservo.getGasStationId
	 * ().length()>0?Integer
	 * .parseInt(uservo.getGasStationId()):Constants.VALIDATE_INT);
	 * user.setGasStationName(uservo.getGasStationName()); return user; }
	 * 
	 * private UserDto populatePoToVo(User user) { UserDto uservo = new
	 * UserDto(); uservo.setUserId(String.valueOf(user.getUserId()));
	 * uservo.setUserName(user.getUserName()); uservo.setPhone(user.getPhone());
	 * uservo.setIdCard(user.getIdCard());
	 * uservo.setDepartmentId(String.valueOf(user.getDepartmentId()));
	 * uservo.setDepartmentName(user.getDepartmentName());
	 * uservo.setGasStationId(String.valueOf(user.getGasStationId()));
	 * uservo.setGasStationName(user.getGasStationName()); return uservo; }
	 */
}
