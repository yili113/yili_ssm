package cqupt.ssm.service;


import cqupt.ssm.domain.Role;
import cqupt.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author Liyi
 * @create 2019-10-05 11:04
 * 要用spring-security框架必须实现UserDetailsService接口
 */
public interface IUserService extends UserDetailsService {

    List<UserInfo> findAll();

    void save(UserInfo userInfo);

    UserInfo findById(String id) throws Exception;

    List<Role> findOtherRoles(String userid);

    void addRoleToUser(String userId, String[] roleIds);
}
