package org.demo.business.controller;

import org.demo.base.ResponseBean;
import org.demo.business.mapper.ArticleViewMapper;
import org.demo.business.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @description: ${description}
 * @author: ssang
 * @create: 2020/12/25 0025 12:10
 **/
@RequestMapping("/busniss")
@Controller
public class BusnissController {
    @Autowired
    ArticleViewMapper viewMapper;
    @Autowired
    SysUserMapper sysUserMapper;

    /**
     * 统计文章分类的阅读量
     */
    @ResponseBody
    @RequestMapping("/statisArticleType")
    public ResponseBean statisArticleType(@RequestParam(required = false) String month){
        int code=200;
        String msg="";
        List<Map> list = new ArrayList<>();
        try{
            list = viewMapper.statisArticleType(month);
        }catch (Exception e){
            code = 500;
            msg = "出现异常，联系管理员";
            e.printStackTrace();
        }
        return new ResponseBean(code,msg,list);
    }

    /**
     * 统计业务员的顾客数
     */
    @ResponseBody
    @RequestMapping("/statisSubUser")
    public ResponseBean statisSubUser(@RequestParam(required = false) String month){
        int code=200;
        String msg="";
        List<Map> list = new ArrayList<>();
        try{
            list = sysUserMapper.statisSubUser(month);
        }catch (Exception e){
            code = 500;
            msg = "出现异常，联系管理员";
            e.printStackTrace();
        }
        return new ResponseBean(code,msg,list);
    }

    /**
     * 获取用户领取的小礼物记录
     */
    @ResponseBody
    @RequestMapping("/listGiftByUser")
    public ResponseBean listGiftByUser(@RequestParam("userId") String userId){
        int code=200;
        String msg="";
        List<Map> list = new ArrayList<>();
        try{
            list = sysUserMapper.listGiftByUser(userId);
        }catch (Exception e){
            code = 500;
            msg = "出现异常，联系管理员";
            e.printStackTrace();
        }
        return new ResponseBean(code,msg,list);
    }
}
