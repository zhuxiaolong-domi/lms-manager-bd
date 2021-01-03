package njust.lmsbackend.lms.Controller;

import njust.lmsbackend.lms.POJO.ExperimentPOJO;
import njust.lmsbackend.lms.Result.Result;
import njust.lmsbackend.lms.Result.ResultFactory;
import njust.lmsbackend.lms.Service.ExperimentService;
import njust.lmsbackend.lms.Util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class ExpController {
    @Autowired
    private ExperimentService service;

    /**
     * @Description: 教师查询所有创建的实验
     * @Param: teacherId 教师的工号
     * @return:
     * @Author: Liu ZhiTian
     * @Date: 2020/12/30
     */
    @CrossOrigin
    @RequestMapping("/api/teacher/allExp")
    public Result allExp(String teacherId) {
        return ResultFactory.buildSuccessResult("查找成功", service.queryAllExpByTeacher(teacherId));
    }

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
        return (service.deleteExperiment(expId)) ? (ResultFactory.buildSuccessResult_p("删除实验成果", null)) : (ResultFactory.buildSuccessResult_p("删除失败", null));
    }

    /**
     * @Description: 批量删除已发布的实验
     * @Param: allExpId 所有要删除的实验编号，按照如下格式"1:2:3:4" , teacherId 教师工号
     * @return: 删除后教师所有的实验
     * @Author: Liu ZhiTian
     * @Date: 2020/12/30
     */
    @CrossOrigin
    @RequestMapping("/api/teacher/batchDeleteExp")
    public Result batchDeleteExp(String allExpId, String teacherId) {
        String[] ids = allExpId.split(":");
        List<ExperimentPOJO> experiments = null;
        String msg = "";
        if (service.batchDeleteExperiment(ids)) {
            msg = "批量删除成功！";
            experiments = service.queryAllExpByTeacher(teacherId);
        } else {
            msg = "批量删除失败";
        }
        return ResultFactory.buildSuccessResult(msg, experiments);
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
        if (service.enterScore(studentId, expId, score)) {
            res = "录入成绩成功";
        } else {
            res = "录入成绩失败";
        }
        return ResultFactory.buildSuccessResult_p(res, null);
    }

    /**
     * @Description: 处理下载实验报告的请求
     * @Param: studentId 学生编号, expId 实验编号
     * @return:
     * @Author: Liu ZhiTian
     * @Date: 2020/12/30
     */
    @CrossOrigin
    @RequestMapping("/api/teacher/downloadFile")
    public void download(String studentId, String expId, HttpServletResponse response, HttpServletRequest request) {
        String fileName = service.downloadReport(studentId, expId);
        try {
            String filePath = fileName;
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, fileName));
            FileUtils.writeBytes(filePath, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }

}
