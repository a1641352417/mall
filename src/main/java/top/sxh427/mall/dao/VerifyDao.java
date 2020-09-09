package top.sxh427.mall.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface VerifyDao {
    /**
     * 查询管理员账号是否存在
     * @param phone 手机号
     * @return
     */
    int selectManagerByPhone(@Param("phone") String phone);

    /**
     * 查询用户账号是否存在
     * @param phone 手机号
     * @return
     */
    int selectUserByPhone(@Param("phone") String phone);
}
