package cqupt.ssm.service.impl;

import cqupt.smm.utils.BCryptPasswordEncoderUtils;
import cqupt.ssm.dao.IUserDao;
import cqupt.ssm.domain.Role;
import cqupt.ssm.domain.UserInfo;
import cqupt.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/*
 * @author Liyi
 * @create 2019-10-05 11:06
 * @Service("userService") 为了让spring-security配置文件知道这是个需要conreoller接管的service
 * 对应配置文件中 <security:authentication-provider user-service-ref="userService">
*/

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
            userInfo = userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 处理自己的userInfo对象封装成userDetails
        // param3需要传入一个权限的集合 不能为空
        // <security:intercept-url pattern="/**" access="ROLE_USER,ROLE_ADMIN"/>
        // 必须具有 ROLE_USER  ROLE_ADMIN权限才可访问
//        User user = new User(userInfo.getUsername(), "{noop}"+userInfo.getPassword(), getAuthority());
        User user = new User(userInfo.getUsername(),"noop" + userInfo.getPassword(), userInfo.getStatus() == 0 ? false : true,
                true, true, true, getAuthority(userInfo.getRoles()));
        return user;
    }

/*
     * 集合中装入角色描述
     * 相当于把用户赋予可以登录的权限
     * @return
*/
/*    public List<SimpleGrantedAuthority> getAuthority() {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_USER"));
        return list;
    }*/

    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {

        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        // System.out.println(list);
        return list;
    }

    @Override
    public List<UserInfo> findAll() {

        return userDao.findAll();

    }

    @Override
    public void save(UserInfo userInfo) {
        // 对密码进行加密处理
        // userInfo.setPassword(BCryptPasswordEncoderUtils.encodePassword(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    @Override
    public UserInfo findById(String id) throws Exception {
        return userDao.findById(id);
    }

    @Override
    public List<Role> findOtherRoles(String userid) {
        return userDao.findOtherRoles(userid);
    }

    @Override
    public void addRoleToUser(String userId, String[] roleIds) {
        for(String roleId:roleIds){// 遍历每个能添加的角色 给用户添加这些角色
            userDao.addRoleToUser(userId,roleId);
        }
    }
}
