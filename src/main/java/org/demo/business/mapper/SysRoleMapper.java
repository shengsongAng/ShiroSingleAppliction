package org.demo.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.demo.business.entity.SysRole;

public interface SysRoleMapper extends BaseMapper<SysRole> {
    int insert(SysRole record);

    int insertSelective(SysRole record);
}