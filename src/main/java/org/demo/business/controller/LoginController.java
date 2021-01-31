package org.demo.business.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.demo.base.BaseController;
import org.demo.base.ResponseBean;
import org.demo.base.util.Const;
import org.demo.base.util.Jurisdiction;
import org.demo.business.entity.system.User;
import org.demo.business.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    @Autowired
    SysUserMapper sysUserMapper;

    //登录
    @ResponseBody
    @PostMapping("/myLogin")
    public ResponseBean myLogin(@RequestParam("username") String username,
                                @RequestParam("password") String password) {
        Subject currUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try{
            currUser.login(token);
        }catch (UnknownAccountException e){
            return new ResponseBean(401, "error", "用户不存在");
        }catch (IncorrectCredentialsException e){
            return new ResponseBean(401, "error", "密码错误");
        }
        if(currUser.isAuthenticated()){
            //验证通过，保存用户信息到session
            User user = sysUserMapper.getUserByUsername(username);
            Session session = Jurisdiction.getSession();
            session.setTimeout(-10001);
            session.setAttribute(Const.SESSION_USER,user);
            currUser.login(token);
        }
        return new ResponseBean(200, "success", this.getCurrentUser());
    }

    //退出登录
    @ResponseBody
    @PostMapping("/logout")
    public ResponseBean logout() {
        Subject currUser = SecurityUtils.getSubject();
        if(currUser.isAuthenticated()){
            currUser.logout();
        }
        return new ResponseBean(200, "success", "已退出登录");
    }
}
