package njust.lmsbackend.lms.Service;

import njust.lmsbackend.lms.DAO.AppointmentDAO;
import njust.lmsbackend.lms.DAO.ExperimentDAO;
import njust.lmsbackend.lms.DAO.ParticipationDAO;
import njust.lmsbackend.lms.DAO.UserDAO;
import njust.lmsbackend.lms.POJO.AppointmentPOJO;
import njust.lmsbackend.lms.POJO.ExperimentPOJO;
import njust.lmsbackend.lms.POJO.ParticipationPOJO;
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
    ParticipationDAO participationDAO;
    @Autowired
    AppointmentDAO appointmentDAO;


    /**
     * 返回所有的用户 根据id
     *
     * @return 用户列表
     */
    public List<UserPOJO> listAllUsers() {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        return userDAO.findAll(sort);
    }

    /**
     * 返回所有的学生 根据identity
     *
     * @return 学生列表
     */
    public List<UserPOJO> listAllStudents() {
        return userDAO.listAllStudents();
    }

    /**
     * 返回所有的实验 根据id
     *
     * @return 实验列表
     */
    public List<ExperimentPOJO> listAllExps() {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        return experimentDAO.findAll(sort);
    }

    /**
     * 无主键则增添，有主键则更新。
     *
     * @param userPOJO 用户对象
     */
    public void addOrUpdateUsers(UserPOJO userPOJO) {
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
     *
     * @param exp_id    实验 id
     * @param studentId 学生 id
     */
    public void selectExpById(String exp_id, String studentId) {
        ParticipationPOJO selectParticipationPOJO = new ParticipationPOJO(exp_id, studentId);
        participationDAO.save(selectParticipationPOJO);
    }

    /**
     * 根据实验号和学生学号退选实验
     *
     * @param exp_id    实验号
     * @param studentId 学生学号
     */
    public void withDrawById(String exp_id, String studentId) {
        //ParticipationPOJO withDrawParticipationPOJO = new ParticipationPOJO(exp_id, studentId);
        participationDAO.deleteByExpIdAndStudentId(exp_id, studentId);
    }

    /**
     * 根据学号查询所有成绩
     *
     * @param studentId 学生学号
     * @return 查询出的成绩列表
     */
    public List<ParticipationPOJO> queryAllScoreById(String studentId) {
        return participationDAO.findAllByStudentId(studentId);
    }

    /**
     * 根据关键词查找
     *
     * @param keywords 关键词
     * @return 查询出的所有符合的实验
     */
    public List<ExperimentPOJO> searchExpByKeywords(String keywords) {
        return experimentDAO.findAllByName('%' + keywords + "%");
    }

    /**
     * 查询所有已经预约实验的信息
     *
     * @param studentId 学生学号
     * @return 查询出的所有符合的信息列表
     */
    public AppointmentPOJO queryAppointmentById(String studentId) {
        return appointmentDAO.findByStudentId(studentId);
    }

    /**
     * 根据学生 ID 查询 实验 ID
     *
     * @param studentId 学生 ID
     * @return ParticipationPOJO 对象
     */
    public ParticipationPOJO findExpIdByStudentId(String studentId) {
        return participationDAO.findByStudentId(studentId);
    }
}
