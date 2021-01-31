package org.demo.business.controller;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.demo.base.BaseController;
import org.demo.base.ResponseBean;
import org.demo.base.util.UuidUtil;
import org.demo.business.entity.Article;
import org.demo.business.entity.ArticleView;
import org.demo.business.entity.GiftUser;
import org.demo.business.mapper.ArticleMapper;
import org.demo.business.mapper.ArticleViewMapper;
import org.demo.business.mapper.GiftUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    ArticleViewMapper articleViewMapper;

    @Autowired
    GiftUserMapper giftUserMapper;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 查询所有
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public ResponseBean list(String type){
        int code=200;
        String msg="";
        List<Article> list = new ArrayList<>();
        try{
            list = articleMapper.list(type);
        }catch (Exception e){
            code = 500;
            msg = "出现异常，联系管理员";
            e.printStackTrace();
        }
        return new ResponseBean(code,msg,list);
    }

    /**
     * 新增
     * @return
     */
    @RequiresAuthentication
    @RequestMapping("/add")
    @ResponseBody
    public ResponseBean add(@RequestParam("title")String title,
                                @RequestParam(value = "linkUrl",required = false)String linkUrl,
                                @RequestParam(value = "imgUrl",required = false)String imgUrl,
                                @RequestParam(value = "startTime",required = false)String startTime,
                                @RequestParam(value = "entTime",required = false)String entTime,
                                @RequestParam("type")String type,
                                @RequestParam("content")String content,
                                @RequestParam(value = "giftCount",required = false)int giftCount){
        int code=200;
        String msg="";
        try{
            Article article = new Article();
            article.setArticleId(UuidUtil.get32UUID());
            article.setTitle(title);
            article.setLinkUrl(linkUrl);
            article.setImgUrl(imgUrl);
            article.setStartTime(sdf.parse(startTime));
            article.setEntTime(sdf.parse(entTime));
            article.setTitle(type);
            article.setContent(content);
            article.setGiftCount(giftCount);
            article.setState("0");
//            article.setCreateUser(this.getCurrentUserId());
            article.setCreateUser("");
            article.setCreateTime(new Date());

            articleMapper.insertSelective(article);
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
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseBean delete(@RequestParam("ids")String ids){
        int code=200;
        String msg="";
        try{
            if(!"".equals(ids)){
                articleMapper.delete(ids);
            }
        }catch (Exception e){
            code = 500;
            msg = "出现异常，联系管理员";
            e.printStackTrace();
        }
        return new ResponseBean(code,msg,null);
    }

    /**
     * 查看文章
     * @param articleId
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/viewArticle")
    public ResponseBean viewArticle(@RequestParam("articleId")String articleId,
                             @RequestParam("userId")String userId){
        int code=200;
        String msg="";
        Article article = new Article();
        try{
            ArticleView articleView = new ArticleView();
            articleView.setUserId(userId);
            articleView.setArticleId(articleId);
            articleView.setCreateTime(new Date());

            articleViewMapper.insert(articleView);

            article = articleMapper.findById(articleId);
        }catch (Exception e){
            code = 500;
            msg = "出现异常，联系管理员";
            e.printStackTrace();
        }
        return new ResponseBean(code,msg,article);
    }

    /**
     * 领取礼物
     */
    @ResponseBody
    @RequestMapping("/getGift")
    public ResponseBean getGift(@RequestParam("articleId")String articleId,
                                    @RequestParam("userId")String userId){
        int code=200;
        String msg="领取成功";
        try{
            //检查是否还有剩余小礼物
            Article article = articleMapper.findById(articleId);
            if(article.getGiftCount() <= article.getReceiveCount()){
                return new ResponseBean(200,"礼物领取完了",null);
            }

            //检查是否已经领取过
            List<GiftUser> list = giftUserMapper.findByUserAndArticle(userId,articleId);
            if(list!=null && list.size()>0){
                return new ResponseBean(200,"已经领取过该礼物",null);
            }

            //新增领取记录
            GiftUser giftUser = new GiftUser();
            giftUser.setUserId(userId);
            giftUser.setGiftArticleId(articleId);
            giftUser.setCreateTime(new Date());

            //礼物领取数量+1
            articleMapper.recieveGift(articleId);

            giftUserMapper.insert(giftUser);
        }catch (Exception e){
            code = 500;
            msg = "出现异常，联系管理员";
            e.printStackTrace();
        }
        return new ResponseBean(code,msg,null);
    }


}
