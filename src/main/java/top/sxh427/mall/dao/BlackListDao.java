package top.sxh427.mall.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.sxh427.mall.entities.BlackList;

@Mapper
public interface BlackListDao {
    /**
     * 查询所有信息
     * @return
     */
    BlackList queryById(@Param("id") Integer id);
}
