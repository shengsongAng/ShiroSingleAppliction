package org.demo.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.demo.business.entity.ArticleView;

import java.util.List;
import java.util.Map;

public interface ArticleViewMapper extends BaseMapper<ArticleView> {
    int insert(ArticleView record);

    int insertSelective(ArticleView record);

    List<Map> statisArticleType(@Param("month") String month);
}