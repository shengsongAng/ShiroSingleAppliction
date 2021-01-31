package org.demo.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.demo.business.entity.GiftUser;

import java.util.List;

public interface GiftUserMapper extends BaseMapper<GiftUser> {
    int insert(GiftUser record);

    int insertSelective(GiftUser record);

    List<GiftUser> findByUserAndArticle(@Param("userId") String userId,@Param("articleId") String articleId);
}