package njust.lmsbackend.lms.Controller;

import njust.lmsbackend.lms.POJO.ExperimentPOJO;
import njust.lmsbackend.lms.Result.Result;
import njust.lmsbackend.lms.Result.ResultFactory;
import njust.lmsbackend.lms.Service.ExperimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpController {
    @Autowired
    private ExperimentService service;

    /**
     * @Description: 创建实验
     * @Param: teacherId 老师的ID，experiment 实验的封装实体类
     * @return:
     * @Author: Liu ZhiTian
     * @Date: 2020/12/27
     */
    @CrossOrigin
    @RequestMapping("/api/teacher/create")
    public Result createExp(String teacherId, ExperimentPOJO experiment) {
        service.startExperiment(teacherId, experiment);
        return ResultFactory.buildSuccessResult_p("创建实验成功", null);
    }

    /**
     * @Description: 根据实验编号查找老师
     * @Param: expId 实验的ID
     * @return:
     * @Author: Liu ZhiTian
     * @Date: 2020/12/27
     */
    @CrossOrigin
    @RequestMapping("/api/teacher/findTeacher")
    public Result findTeacher(String expId) {
        return ResultFactory.buildSuccessResult("根据实验查找老师成功", service.findTeacherByExp(expId));
    }

    /**
     * @Description: 修改已发布的实验
     * @Param: teacherId 老师的ID，experiment 实验的封装类
     * @return:
     * @Author: Liu ZhiTian
     * @Date: 2020/12/27
     */
    @CrossOrigin
    @RequestMapping("/api/teacher/updateExp")
    public Result updateExp(String teacherId, ExperimentPOJO experiment) {
        service.updateExperiment(experiment, teacherId);
        return ResultFactory.buildSuccessResult_p("修改已发布的实验成果", null);
    }

    /**
     * @Description: 删除已发布的实验
     * @Param: expId 实验编号
     * @return:
     * @Author: Liu ZhiTian
     * @Date: 2020/12/27
     */
    @CrossOrigin
    @RequestMapping("/api/teacher/deleteExp")
    public Result deleteExp(String expId) {
        return (service.deleteExperiment(expId) == true) ? (ResultFactory.buildSuccessResult_p("删除实验成果", null)) : (ResultFactory.buildSuccessResult_p("删除失败", null));
    }

    /**
     * @Description: 查询所选实验的所有学生信息
     * @Param: expId 实验编号
     * @return: 所有学生信息的列表
     * @Author: Liu ZhiTian
     * @Date: 2020/12/27
     */
    @CrossOrigin
    @RequestMapping("/api/teacher/queryAllStudentSelect")
    public Result queryAllStudentSelect(String expId) {
        return ResultFactory.buildSuccessResult("查找所有选择改实验的学生成功", service.findAllStudentSelected(expId));
    }

    /**
     * @Description: 处理下载实验报告请求
     * @Param: studentId 选择该实验的学生的学号, expId 选择的实验编号
     * @return:
     * @Author: Liu ZhiTian
     * @Date: 2020/12/27
     */
    @CrossOrigin
    @RequestMapping("/api/teacher/downloadReport")
    public Result downloadReport(String studentId, String expId) {
        return ResultFactory.buildSuccessResult("查找实验报告成功", service.downloadReport(studentId, expId));
    }

    /**
     * @Description: 录入成绩
     * @Param: studentId 学生学号, expId 实验编号, score 成绩
     * @return:
     * @Author: Liu ZhiTian
     * @Date: 2020/12/27
     */
    @CrossOrigin
    @RequestMapping("/api/teacher/enterScore")
    public Result enterScore(String studentId, String expId, Integer score) {
        String res = "";
        if (service.enterScore(studentId, expId, score) == true) {
            res = "录入成绩成功";
        } else {
            res = "录入成绩失败";
        }
        return ResultFactory.buildSuccessResult_p(res, null);
    }
}
