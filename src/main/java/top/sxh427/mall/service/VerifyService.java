package top.sxh427.mall.service;


public interface VerifyService {
    /**
     * 查询管理员账号是否存在
     * @param phone 手机号
     * @return
     */
    int selectManagerByPhone(String phone);

    /**
     * 查询用户账号是否存在
     * @param phone 手机号
     * @return
     */
    int selectUserByPhone(String phone);
}
