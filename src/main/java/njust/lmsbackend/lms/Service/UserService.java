package njust.lmsbackend.lms.Service;

import njust.lmsbackend.lms.DAO.ExperimentDAO;
import njust.lmsbackend.lms.DAO.ParticipitionDAO;
import njust.lmsbackend.lms.DAO.UserDAO;
import njust.lmsbackend.lms.POJO.ExperimentPOJO;
import njust.lmsbackend.lms.POJO.ParticipitionPOJO;
import njust.lmsbackend.lms.POJO.UserPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;
    @Autowired
    ExperimentDAO experimentDAO;
    @Autowired
    ParticipitionDAO participitionDAO;


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
     * 返回所有的实验 根据id
     * @return 实验列表
     */
    public List<ExperimentPOJO> listAllExps()
    {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        return experimentDAO.findAll(sort);
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

    /**
     * 根据实验号选择实验
     * TODO:现在先不做验证,暂时使用前端验证
     * @param exp_id 实验 id
     * @param student_id 学生 id
     */
    public void selectExpById(String exp_id, String student_id)
    {
        ParticipitionPOJO selectParticipitionPOJO = new ParticipitionPOJO(exp_id, student_id);
        participitionDAO.save(selectParticipitionPOJO);
    }
}
