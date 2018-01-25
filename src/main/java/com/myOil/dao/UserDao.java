/**
 * 
 */
package com.myOil.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.myOil.entity.Department;
import com.myOil.entity.User;

/**
 * @author Shuai.yang
 *
 */
@Repository
@Transactional
public interface UserDao extends PagingAndSortingRepository<User, String>,JpaSpecificationExecutor<User>{

	@Query("from User u where u.userName=?1 and u.userPassword=?2")
	public User getUser(String userName, String userPassword);
	
	@Query("from User")
	public List<User> getAllUserList();
	
	@Query("select d, a from Department d join d.authorityList a")
	public Set<Department> getUserAllAuthority();
	
	@Query("select d, a from Department d join d.authorityList a where d.departmentId=?1")
	public Set<Department> getUserAuthorityMapByDepartmentId(int departmentId);
}
