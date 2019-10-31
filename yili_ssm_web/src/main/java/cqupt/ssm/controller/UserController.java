package cqupt.ssm.controller;

import cqupt.ssm.domain.Role;
import cqupt.ssm.domain.UserInfo;
import cqupt.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Liyi
 * @create 2019-10-07 14:57
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    //给用户添加角色
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId", required = true) String userId, @RequestParam(name = "ids", required = true) String[] roleIds) {
        userService.addRoleToUser(userId, roleIds);
        return "redirect:findAll.do";
    }
    // 查询用户以及该用户可以添加的角色
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name="id", required = true)String userid) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        // 1.根据用户id查询用户
        UserInfo userInfo = userService.findById(userid);
        // 2.根据用户id查询可以添加的角色
        List<Role> otherRoles = userService.findOtherRoles(userid);
        modelAndView.addObject("user",userInfo);
        modelAndView.addObject("roleList",otherRoles);
        modelAndView.setViewName("user-role-add");
        return modelAndView;
    }
    // 查询用户详情
    // 根据用户查询角色  再根据角色查询权限
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        modelAndView.addObject("user",userInfo);
        modelAndView.setViewName("user-show1");
        return modelAndView;
    }
    // 添加用户
    @RequestMapping("/save.do")
    public String save(UserInfo userInfo) throws Exception{
        userService.save(userInfo);
        return "redirect:findAll.do";// 先跳转到findAll再查询出来
    }


    // 查询所有用户
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws  Exception{
        ModelAndView modelAndView = new ModelAndView();
        List<UserInfo> userList = userService.findAll();
        modelAndView.addObject("userList", userList);
        modelAndView.setViewName("/user-list");
        return modelAndView;
    }
}
