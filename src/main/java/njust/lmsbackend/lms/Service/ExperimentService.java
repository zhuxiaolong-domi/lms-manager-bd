package njust.lmsbackend.lms.Service;

import njust.lmsbackend.lms.DAO.ExperimentDAO;
import njust.lmsbackend.lms.DAO.ParticipationDAO;
import njust.lmsbackend.lms.DAO.StartExpDAO;
import njust.lmsbackend.lms.DAO.UserDAO;
import njust.lmsbackend.lms.POJO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    public boolean startExperiment(String teacherId, ExperimentPOJO experiment) {
        /*如果老师不存在直接返回*/
        if (userDAO.findById(teacherId) == null) {
            return false;
        }
        experiment.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        experimentDAO.save(experiment);
        StartExpPOJO startExp = new StartExpPOJO();
        startExp.setExpId(experiment.getId());
        startExp.setTeacherId(teacherId);
        startExpDAO.save(startExp);
        return true;
    }

    /*2.老师修改已发布的实验*/
    public boolean updateExperiment(ExperimentPOJO experiment, String teacherId) {
        StartExpPOJO startExp = startExpDAO.findStartExpPOJOByExpIdAndTeacherId(experiment.getId(), teacherId);
        if (startExp == null) {
            return false;
        }
        experimentDAO.save(experiment);
        return true;
    }

    /*3.删除已发布实验*/
    public boolean deleteExperiment(String expId) {
        if (!experimentDAO.existsById(expId)) {
            return false;
        }
        startExpDAO.deleteById(expId);
        participationDAO.deleteParticipationPOJOByExpId(expId);
        experimentDAO.deleteById(expId);
        return true;
    }

    /*4.批量删除已发布的实验*/
    public boolean batchDeleteExperiment(String[] ids) {
        boolean flag = true;
        for (var expId : ids) {
            flag = this.deleteExperiment(expId);
        }

        return flag;
    }

    /*5.根据老师工号查找所有的实验*/
    public List<ExperimentPOJO> queryAllExpByTeacher(String teacherId) {
        List<ExperimentPOJO> list = new LinkedList<>();
        if (teacherId == null || !userDAO.findById(teacherId).isPresent()) {
            return null;
        }
        UserPOJO teacher = userDAO.findUserPOJOById(teacherId);
        for (var tmp : startExpDAO.findStartExpPOJOSByTeacherId(teacherId)) {
            var exp = experimentDAO.findByExpId(tmp.getExpId());
            exp.setTeacherName(teacher.getName());
            list.add(exp);
        }
        return list;
    }


    /*根据实验查找创建的老师*/
    public UserPOJO findTeacherByExp(String expId) {
        Optional<ExperimentPOJO> optional = experimentDAO.findById(expId);
        if (optional.isPresent()) {
            ExperimentPOJO experiment = optional.get();
            StartExpPOJO startExp = startExpDAO.findStartExpPOJOByExpId(experiment.getId());
            if (startExp == null) {
                return null;
            }
            Optional<UserPOJO> teacher = userDAO.findById(startExp.getTeacherId());
            if (!teacher.isPresent()) {
                return null;
            }
            return teacher.get();
        }
        return null;
    }

    /*查询学生信息*/
    public List<UserPOJO> findAllStudentSelected(String expId) {
        List<UserPOJO> students = new LinkedList<>();
        for (var participation : participationDAO.findAllByExpId(expId)) {
            students.add(userDAO.findUserPOJOById(participation.getStudentId()));
        }
        return students;
    }

    /*下载实验报告*/
    public String downloadReport(String studentId, String expId) {
        return participationDAO.findParticipationPOJOByStudentIdAndExpId(studentId, expId).getReport();
    }

    /*录入成绩以及修改成绩*/
    public boolean enterScore(String studentId, String expId, Integer score) {
        if (!experimentDAO.existsById(expId)) {
            return false;
        }
        if (!userDAO.existsById(studentId)) {
            return false;
        }
        int res = participationDAO.updateScore(score, studentId, expId);
        return res > 0;
    }

    /**
     * 根据实验 ID 查询实验名称
     *
     * @param id 实验 ID
     * @return ExperimentPOJO 对象
     */
    public ExperimentPOJO findExpNameById(String id) {
        return experimentDAO.findByExpId(id);
    }
}
