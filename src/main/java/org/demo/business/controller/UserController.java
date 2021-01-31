package org.demo.business.controller;

import org.demo.base.BaseController;
import org.demo.base.ResponseBean;
import org.demo.base.util.UuidUtil;
import org.demo.business.entity.SysUser;
import org.demo.business.entity.system.User;
import org.demo.business.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    SysUserMapper sysUserMapper;

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 查询用户下管理的用户
     * @param userId
     * @return
     */
    @GetMapping("/listUser")
    @ResponseBody
    public ResponseBean listUser(@RequestParam("userId") String userId){
        int code=200;
        String msg="";
        List<SysUser> userList = new ArrayList<>();
        try{
            userList = sysUserMapper.listUser(userId);
            if(userList!=null && userList.size()>0){
                if(userList.get(0).getRoleId().equals("2")){//业务员
                    //所有用户
                    List<SysUser> allList = sysUserMapper.listUser(null);
                    for(SysUser u : userList){
                        for(SysUser u1 : allList){
                            if(u1.getCreateUser().equals(u.getUserId())){//用户属于业务员
                                //数量加1
                                u.setSubUserCount(u.getSubUserCount()+1);
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            code = 500;
            msg = "出现异常，联系管理员";
            e.printStackTrace();
        }
        return new ResponseBean(code,msg,userList);
    }

    /**
     * 新增
     * @return
     */
    @GetMapping("/addUser")
    @ResponseBody
    public ResponseBean addUser(@RequestParam("name")String name,
                                @RequestParam("userName")String userName,
                                @RequestParam("password")String password,
                                @RequestParam("phone")String phone,
                                @RequestParam("roleId")String roleId,
                                @RequestParam("sex")String sex,
                                @RequestParam("age")int age,
                                @RequestParam("department")String department,
                                @RequestParam("currUser")String currUser){
        int code=200;
        String msg="";
        try{
            User user = sysUserMapper.getUserByUsername(userName);
            if(user!=null){
                return new ResponseBean(500,"当前用户名已经存在",null);
            }
            SysUser sysUser = new SysUser();
            sysUser.setUserId(UuidUtil.get32UUID());
            sysUser.setName(name);
            sysUser.setUserName(userName);
            sysUser.setPassword(password);
            sysUser.setPhone(phone);
            sysUser.setRoleId(roleId);
            sysUser.setSex(sex);
            sysUser.setAge(age);
            sysUser.setDepartment(department);
            sysUser.setCreateUser(currUser);
            sysUser.setCreateTime(new Date());
            sysUserMapper.insert(sysUser);
        }catch (Exception e){
            code = 500;
            msg = "出现异常，联系管理员";
            e.printStackTrace();
        }
        return new ResponseBean(code,msg,null);
    }

    /**
     * 修改
     * @return
     */
    @GetMapping("/updateUser")
    @ResponseBody
    public ResponseBean addUser(@RequestParam("name")String name,
                                @RequestParam("userId")String userId,
                                @RequestParam("password")String password,
                                @RequestParam("phone")String phone,
                                @RequestParam("department")String department,
                                @RequestParam("sex")String sex,
                                @RequestParam("age")int age){
        int code=200;
        String msg="";
        try{
            SysUser sysUser = new SysUser();
            sysUser.setName(name);
            sysUser.setPassword(password);
            sysUser.setPhone(phone);
            sysUser.setSex(sex);
            sysUser.setAge(age);
            sysUser.setUserId(userId);
            sysUser.setDepartment(department);
            sysUserMapper.updateUser(sysUser);
        }catch (Exception e){
            code = 500;
            msg = "出现异常，联系管理员";
            e.printStackTrace();
        }
        return new ResponseBean(code,msg,null);
    }

    /**
     * 删除
     * @return
     */
    @GetMapping("/deleteUser")
    @ResponseBody
    public ResponseBean deleteUser(@RequestParam("userIds")String userIds){
        int code=200;
        String msg="";
        try{
            if(!"".equals(userIds)){
                sysUserMapper.deleteUser(userIds);
            }
        }catch (Exception e){
            code = 500;
            msg = "出现异常，联系管理员";
            e.printStackTrace();
        }
        return new ResponseBean(code,msg,null);
    }




}
