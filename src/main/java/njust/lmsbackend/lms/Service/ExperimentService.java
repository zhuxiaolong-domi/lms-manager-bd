package njust.lmsbackend.lms.Service;

import njust.lmsbackend.lms.DAO.ExperimentDAO;
import njust.lmsbackend.lms.DAO.ParticipationDAO;
import njust.lmsbackend.lms.DAO.StartExpDAO;
import njust.lmsbackend.lms.DAO.UserDAO;
import njust.lmsbackend.lms.POJO.ExperimentPOJO;
import njust.lmsbackend.lms.POJO.ParticipationPOJO;
import njust.lmsbackend.lms.POJO.StartExpPOJO;
import njust.lmsbackend.lms.POJO.UserPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ExperimentService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ExperimentDAO experimentDAO;

    @Autowired
    private StartExpDAO startExpDAO;

    @Autowired
    private ParticipationDAO participationDAO;


    /*实验发布管理*/
    /*1.老师添加一个实验*/
    public boolean startExperiment(String teacherId, ExperimentPOJO experiment){
        /*如果老师不存在直接返回*/
        if(userDAO.findById(teacherId) == null){
            return false;
        }
        experiment.setId(UUID.randomUUID().toString().replaceAll("-",""));
        experimentDAO.save(experiment);
        StartExpPOJO startExp = new StartExpPOJO();
        startExp.setExpId(experiment.getId());
        startExp.setTeacherId(teacherId);
        startExpDAO.save(startExp);
        return true;
    }

    /*2.老师修改已发布的实验*/
    public boolean updateExperiment(ExperimentPOJO experiment, String teacherId){
        StartExpPOJO startExp = startExpDAO.findStartExpPOJOByExpIdAndTeacherId(experiment.getId(), teacherId);
        if(startExp == null){
            return false;
        }
        experimentDAO.save(experiment);
        return true;
    }

    /*3.删除已发布实验*/
    public boolean deleteExperiment(String expId){
        if(!experimentDAO.existsById(expId)){
            return false;
        }
        startExpDAO.deleteById(expId);
        participationDAO.deleteParticipationPOJOByExpId(expId);
        experimentDAO.deleteById(expId);
        return true;
    }


    /*根据实验查找创建的老师*/
    public UserPOJO findTeacherByExp(String expId){
        Optional<ExperimentPOJO> optional = experimentDAO.findById(expId);
        if(optional.isPresent()){
            ExperimentPOJO experiment = optional.get();
            StartExpPOJO startExp = startExpDAO.findStartExpPOJOByExpId(experiment.getId());
            if(startExp == null){
                return null;
            }
            Optional<UserPOJO> teacher = userDAO.findById(startExp.getTeacherId());
            if(!teacher.isPresent()){
                return null;
            }
            return teacher.get();
        }
        return null;
    }


}
