package njust.lmsbackend.lms.Service;

import njust.lmsbackend.lms.DAO.ExperimentDAO;
import njust.lmsbackend.lms.DAO.ParticipationDAO;
import njust.lmsbackend.lms.DAO.UserDAO;
import njust.lmsbackend.lms.POJO.StartExpPOJO;
import njust.lmsbackend.lms.POJO.UserPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private ExperimentDAO experimentDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ParticipationDAO participationDAO;

    /*老师根据实验编号查看所有选择该实验的学生的信息*/
    public List<UserPOJO> queryAllStudentsSelectedExp(String expId){
        return null;
    }
}
