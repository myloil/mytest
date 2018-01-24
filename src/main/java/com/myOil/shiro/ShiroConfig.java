/**
 * 
 */
package com.myOil.shiro;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;
import com.myOil.entity.Authority;
import com.myOil.service.UserService;

/**
 * @author Shuai.yang
 *
 */
@Configuration
public class ShiroConfig {
	
	@Autowired
	private UserService userService;
	
	@Bean(name = "MyRealm")
	public MyRealm getShiroRealm() {
		return new MyRealm();
	}

	/*@Bean(name = "shiroEhcacheManager")
	public EhCacheManager getEhCacheManager() {
		EhCacheManager em = new EhCacheManager();
		em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
		return em;
	}*/

	/**
    *在此重点说明这个方法，如果不设置为静态方法会导致bean对象无法注入进来，
    *我被这个问题坑的想死的心都有了
    *我是在这篇博客里找到答案的：
    *http://blog.csdn.net/wuxuyang_7788/article/details/70141812
    */
	@Bean(name = "lifecycleBeanPostProcessor")
	public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
		daap.setProxyTargetClass(true);
		return daap;
	}

	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager() {
		DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
		dwsm.setRealm(getShiroRealm());
//		dwsm.setCacheManager(getEhCacheManager());
		return dwsm;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor() {
		AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
		aasa.setSecurityManager(getDefaultWebSecurityManager());
		return new AuthorizationAttributeSourceAdvisor();
	}

	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);  
		shiroFilterFactoryBean.setLoginUrl("/");
		shiroFilterFactoryBean.setSuccessUrl("/sa/index");

		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		// <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        // <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/user/login", "anon");//anon 可以理解为不拦截

//		filterChainDefinitionMap.put("/user/list/getAllUsers", "roles[getAllUsers]");
       /* List<Resources> resourcesList = resourcesService.queryAll();
        for(Resources resources:resourcesList){

           if (StringUtil.isNotEmpty(resources.getResurl())) {
               String permission = "perms[" + resources.getResurl()+ "]";
               filterChainDefinitionMap.put(resources.getResurl(),permission);
           }
       }*/

//        filterChainDefinitionMap.put("/**", "authc");
		Map<String, Set<Authority>> mp = userService.getUserAllAuthorityMap();
		for (Map.Entry<String, Set<Authority>> entry : mp.entrySet()) {
			String key = entry.getKey();
			Set<Authority> authoritySet = entry.getValue();
			for (Authority au : authoritySet) {
				if(!filterChainDefinitionMap.containsKey("/"+au.getAuthorityUrl())){
					filterChainDefinitionMap.put("/"+au.getAuthorityUrl(),"roles[" + key + "]");
				}else{
					StringBuffer tmpRole = new StringBuffer(filterChainDefinitionMap.get("/"+au.getAuthorityUrl()));
					tmpRole.deleteCharAt(tmpRole.length()-1);
					tmpRole.append(",").append(key).append("]");
					System.out.println(tmpRole);
					filterChainDefinitionMap.put("/"+au.getAuthorityUrl(),tmpRole.toString());
				}
				
			}
		}
		filterChainDefinitionMap.put("/**", "authc");
		
		
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}
}
