/**
 * 
 */
package com.myOil.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.myOil.dao.UserDao;
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
	/*public boolean checkUserNameIsExist(String userName){
		boolean result = false;
		int count = userDao.checkUserIsExist(userName);
		if(count>0){
			result = true;
		}
		return result;
	}*/

	public User checkLoginUser(String userName, String userPassword) {
		User user = userDao.getUser(userName, userPassword);
		return user;
	}
/*
	public List getAllUsers() {
		List resultList = new ArrayList();
		List tmpList = userDao.getAllUserList();
		populatePoToVoForList(tmpList, resultList);
		return resultList;
	}

	public int getAllUserCount(int loginUserId){
		return userDao.getAllUserCount(loginUserId);
	}

	public UserDto getUserById(int id){
		User c = userDao.getUserById(id);

		return populatePoToVo(c);
	}
	
	public int getUserByCriteriaCount(UserDto cvo){
		User c = null;
		if (null != cvo) {
			c = populateVoToPo(cvo);
		}
		return userDao.getUserByCriteriaCount(c);
	}
	
	public List getAllUserByPage(int startRow, int pageSize, UserDto cvo){
		List resultList = new ArrayList();
		User c = null;
		if (null != cvo) {
			c = populateVoToPo(cvo);
		}
		List tmpList = userDao.getAllUserByPage(startRow,pageSize, c);
		populatePoToVoForList(tmpList, resultList);
		return resultList;
	}
	
	public void insertUser(UserDto cvo){
		User c = populateVoToPo(cvo);
		userDao.insertUser(c);
	}
	
	public void deleteUser(String[] idsList){
		userDao.deleteUser(idsList);
	}
	
	public void updateUser(UserDto cvo){
		User c = populateVoToPo(cvo);
		userDao.updateUser(c);
	}
	
	public List getUserByDisplayName(String displayName){
		List resultList = new ArrayList();
		List tmpList = userDao.getUserByDisplayName(displayName);
		populatePoToVoForList(tmpList, resultList);
		return resultList;
	}
	
	private List populatePoToVoForList(List tmpList, List resultList) {
		for (int i = 0; i < tmpList.size(); i++) {
			User user = (User) tmpList.get(i);
			resultList.add(populatePoToVo(user));
		}
		return resultList;
	}


	private User populateVoToPo(UserDto uservo) {
		User user = new User();
		user.setUserId(null!=uservo.getUserId()?Integer.parseInt(uservo.getUserId()):Constants.VALIDATE_INT);
		user.setUserName(uservo.getUserName());
		user.setUserPassword(null!=uservo.getUserPassword()&&uservo.getUserPassword().length()>0?EncryptUtil.encrypt(uservo.getUserPassword()):"");
		user.setPhone(uservo.getPhone());
		user.setIdCard(uservo.getIdCard());
		user.setDepartmentId(null!=uservo.getDepartmentId()&&uservo.getDepartmentId().length()>0?Integer.parseInt(uservo.getDepartmentId()):Constants.VALIDATE_INT);
		user.setDepartmentName(uservo.getDepartmentName());
		user.setGasStationId(null!=uservo.getGasStationId()&&uservo.getGasStationId().length()>0?Integer.parseInt(uservo.getGasStationId()):Constants.VALIDATE_INT);
		user.setGasStationName(uservo.getGasStationName());
		return user;
	}

	private UserDto populatePoToVo(User user) {
		UserDto uservo = new UserDto();
		uservo.setUserId(String.valueOf(user.getUserId()));
		uservo.setUserName(user.getUserName());
		uservo.setPhone(user.getPhone());
		uservo.setIdCard(user.getIdCard());
		uservo.setDepartmentId(String.valueOf(user.getDepartmentId()));
		uservo.setDepartmentName(user.getDepartmentName());
		uservo.setGasStationId(String.valueOf(user.getGasStationId()));
		uservo.setGasStationName(user.getGasStationName());
		return uservo;
	}*/
}
