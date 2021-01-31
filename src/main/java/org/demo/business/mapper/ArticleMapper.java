package org.demo.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.demo.business.entity.Article;

import java.util.List;

public interface ArticleMapper extends BaseMapper<Article> {
    int insert(Article record);

    int insertSelective(Article record);

    int recieveGift(@Param("articleId") String articleId);

    Article findById(@Param("articleId") String articleId);

    int delete(@Param("ids") String ids);

    List<Article> list(@Param("type") String type);
}