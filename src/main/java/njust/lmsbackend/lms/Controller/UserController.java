package njust.lmsbackend.lms.Controller;

import njust.lmsbackend.lms.POJO.*;
import njust.lmsbackend.lms.Result.Result;
import njust.lmsbackend.lms.Result.ResultFactory;
import njust.lmsbackend.lms.Service.ComputerLabService;
import njust.lmsbackend.lms.Service.ExperimentService;
import njust.lmsbackend.lms.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ExperimentService experimentService;
    @Autowired
    ComputerLabService computerLabService;

    /**
     * 列出所有用户
     *
     * @return 用户列表
     * @throws Exception 异常
     */
    @CrossOrigin
    @GetMapping("/api/admin/user")
    public Result listUsers() throws Exception {
        return ResultFactory.buildSuccessResult("查询用户成功", userService.listAllUsers());
    }

    /**
     * 列出所有学生用户
     *
     * @return 学生用户列表
     * @throws Exception 异常
     */
    @CrossOrigin
    @GetMapping("/api/admin/student")
    public Result listStudents() throws Exception {
        return ResultFactory.buildSuccessResult("查询用户成功", userService.listAllStudents());
    }

    /**
     * 添加或更新用户
     *
     * @param userPOJO 用户对象
     * @return 成功
     */
    @CrossOrigin
    @PostMapping("/api/admin/user/add")
    public Result addOrUpdateUsers(@RequestBody UserPOJO userPOJO) {
        userService.addOrUpdateUsers(userPOJO);
        return ResultFactory.buildSuccessResult_p("修改成功", null);
    }

    /**
     * 根据 id 删除用户操作
     *
     * @param userPOJO 用户对象
     * @throws Exception 异常
     */
    @CrossOrigin
    @PostMapping("/api/admin/user/deleteUser")
    public Result deleteUser(@RequestBody UserPOJO userPOJO) throws Exception {
        userService.deleteById(userPOJO.getId());
        return ResultFactory.buildSuccessResult_p("删除成功", null);
    }

    /**
     * 列出所有实验
     *
     * @return 实验列表
     */
    @CrossOrigin
    @GetMapping("/api/user/exp")
    public Result listExps() {
        List<ExperimentPOJO> Exps = userService.listAllExps();
        for (ExperimentPOJO exp : Exps) {
            if (exp.getTeacherName() == null) {
                exp.setTeacherName((experimentService.findTeacherByExp(exp.getId())).getName());
            }
        }
        return ResultFactory.buildSuccessResult("查询实验成功", Exps);
    }


    /**
     * 选择实验
     *
     * @param participationExpPOJO 参与表-实验表 间接类
     * @return 选择成功
     */
    @CrossOrigin
    @PostMapping("/api/user/selectExp")
    public Result selectExp(@RequestBody ParticipationExpPOJO participationExpPOJO) {
        userService.selectExpById(participationExpPOJO.experimentPOJO.getId(), participationExpPOJO.userPOJO.getId());
        return ResultFactory.buildSuccessResult_p("选择实验成功", null);
    }

    /**
     * 退选实验
     *
     * @param participationExpPOJO 参与和实验的中间类
     * @return 退选实验成功
     */
    @CrossOrigin
    @PostMapping("/api/user/withdrawExp")
    public Result withDrawExp(@RequestBody ParticipationExpPOJO participationExpPOJO) {
        userService.withDrawById(participationExpPOJO.experimentPOJO.getId(), participationExpPOJO.userPOJO.getId());
        return ResultFactory.buildSuccessResult_p("退选实验成功", null);
    }

    /**
     * 根据学号查询成绩
     *
     * @param userPOJO 学生对象
     * @return 学生的所有成绩
     */
    @CrossOrigin
    @PostMapping("/api/user/queryScore")
    public Result queryAllScore(@RequestBody UserPOJO userPOJO) {
        return ResultFactory.buildSuccessResult_p("查询所有成绩成功", userService.queryAllScoreById(userPOJO.getId()));
    }

    /**
     * 根据关键词查找实验
     *
     * @param keywords 关键词
     * @return 查找出的实验列表
     */
    @CrossOrigin
    @GetMapping("/api/searchExp")
    public Result searchExpResult(@RequestParam("keywords") String keywords) {
        if ("".equals(keywords)) {
            return ResultFactory.buildSuccessResult("查询所有实验成功", userService.listAllExps());
        } else {
            return ResultFactory.buildSuccessResult("关键词查询成功", userService.searchExpByKeywords(keywords));
        }
    }

    /**
     * 查询所有已经预约实验的信息
     *
     * @param userPOJO 学生对象
     * @return 成功:查询出的所有符合的信息列表 失败:"没有已预约实验"
     */
    @CrossOrigin
    @PostMapping("/api/user/queryAppointment")
    public Result queryAppointment(@RequestBody UserPOJO userPOJO) {
        AppointmentPOJO appointmentPOJO = userService.queryAppointmentById(userPOJO.getId());
        if (appointmentPOJO != null) {
            System.out.println(appointmentPOJO.getLabId());

            ComputerLabPOJO computerLabPOJO = computerLabService.findAddressByLabId(appointmentPOJO.getLabId());
            System.out.println(computerLabPOJO.getId());
            appointmentPOJO.setAddress(computerLabPOJO.getAddress());

            ParticipationPOJO participationPOJO = userService.findExpByStudentId(appointmentPOJO.getStudentId());
            appointmentPOJO.setTeacherName(experimentService.findTeacherByExp(participationPOJO.getExp_id()).getName());

            ExperimentPOJO experimentPOJO = experimentService.findExpNameById(participationPOJO.getExp_id());
            appointmentPOJO.setExpName(experimentPOJO.getName());

            return ResultFactory.buildSuccessResult_p("查询所有已预约实验信息成功", appointmentPOJO);
        } else {
            return ResultFactory.buildFailResult("没有已预约实验");
        }
    }

    /**
     * 查询学生个人实验信息
     *
     * @param userPOJO 用户类
     * @return 成功:参与的实验信息 失败:"没有参与实验"
     */
    @CrossOrigin
    @PostMapping("/api/user/queryExp")
    public Result queryExp(@RequestBody UserPOJO userPOJO) {
        ParticipationPOJO participationPOJO = userService.findExpByStudentId(userPOJO.getId());
        if (participationPOJO != null) {
            return ResultFactory.buildSuccessResult_p("查询实验信息成功", participationPOJO);
        } else {
            return ResultFactory.buildFailResult("没有参与实验");
        }
    }

}
