package org.demo.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.demo.business.entity.SysUser;
import org.demo.business.entity.system.User;

import java.util.List;
import java.util.Map;

public interface SysUserMapper extends BaseMapper<SysUser> {
    int insert(SysUser record);

    int insertSelective(SysUser record);

    int updateUser(SysUser record);

    int deleteUser(@Param("userIds") String userIds);

    User getUserByUsername(String username);

    List<SysUser> listUser(@Param("userId") String userId);

    List<Map> statisSubUser(@Param("month") String month);

    List<Map> listGiftByUser(@Param("userId") String userId);
}