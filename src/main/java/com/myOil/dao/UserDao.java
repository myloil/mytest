/**
 * 
 */
package com.myOil.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.myOil.entity.User;

/**
 * @author Shuai.yang
 *
 */
@Repository
@Transactional
public interface UserDao extends PagingAndSortingRepository<User, String>,JpaSpecificationExecutor<User>{

	@Query("from User u where u.userName=:userName and u.userPassword=:userPassword")
	public User getUser(@Param("userName") String userName, @Param("userPassword") String userPassword);
}
