package njust.lmsbackend.lms.Service;

import njust.lmsbackend.lms.DAO.UserDAO;
import njust.lmsbackend.lms.POJO.ComputerLabPOJO;
import njust.lmsbackend.lms.POJO.UserPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    @Autowired
    UserDAO userDAO;
    /**
     * 返回所有的用户 根据id
     * @return 用户列表
     */
    public List<UserPOJO> listAllUsers()
    {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        return userDAO.findAll(sort);
    }
    /**
     * 无主键则增添，有主键则更新。
     *
     * @param userPOJO 用户对象
     */
    public void addOrUpdate(UserPOJO userPOJO) {
        userDAO.save(userPOJO);
    }
    /**
     * 根据id删除用户
     *
     * @param id 用户的id
     */
    public void deleteById(String id) {
        userDAO.deleteById(id);
    }
}
