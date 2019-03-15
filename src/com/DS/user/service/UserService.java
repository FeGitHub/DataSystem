package com.DS.user.service;
import java.util.List;
import com.DS.common.model.User;
import com.DS.user.vo.UserVo;
/***
 * 
 * @author jeff
 * 用户服务层
 */
public interface UserService {
  /***
   * 注册新的用户
   * @param user
   * @return
   */
   int register(UserVo user);
   
   /***
    * 得到所有用户信息
    * @return
    */
   List<User> getAllUser();
   
   
}
