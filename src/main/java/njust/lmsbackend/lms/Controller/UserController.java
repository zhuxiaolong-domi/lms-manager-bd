package njust.lmsbackend.lms.Controller;

import njust.lmsbackend.lms.POJO.ExperimentPOJO;
import njust.lmsbackend.lms.POJO.ParticipitionExpPOJO;
import njust.lmsbackend.lms.POJO.UserPOJO;
import njust.lmsbackend.lms.Result.Result;
import njust.lmsbackend.lms.Result.ResultFactory;
import njust.lmsbackend.lms.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Column;
import java.awt.print.Book;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 列出所有用户
     * @return 用户列表
     * @throws Exception 异常
     */
    @CrossOrigin
    @GetMapping("/api/admin/user")
    public Result listUsers() throws Exception
    {
        return ResultFactory.buildSuccessResult("查询用户成功", userService.listAllUsers());
    }

    /**
     * 添加或更新用户
     * @param userPOJO 用户对象
     * @return 成功
     */
    @CrossOrigin
    @PostMapping("/api/admin/user/add")
    public Result addOrUpdateUsers(@RequestBody UserPOJO userPOJO)
    {
        userService.addOrUpdateUsers(userPOJO);
        return ResultFactory.buildSuccessResult_p("修改成功", null);
    }

    /**
     * 根据id删除id操作
     *
     * @param userPOJO 用户对象
     * @throws Exception 异常
     */
    @CrossOrigin
    @PostMapping("/api/admin/user/delete")
    public Result delete(@RequestBody UserPOJO userPOJO) throws Exception {
        userService.deleteById(userPOJO.getId());
        return ResultFactory.buildSuccessResult_p("删除成功", null);
    }

    /**
     * 列出所有实验
     * @return 实验列表
     */
    @CrossOrigin
    @GetMapping("/api/user/exp")
    public Result listExps()
    {
        return ResultFactory.buildSuccessResult("查询实验成功", userService.listAllExps());
    }


    /**
     * 选择实验
     * @param participitionExpPOJO 参与表-实验表 间接类
     * @return 选择成功
     */
    @CrossOrigin
    @PostMapping("/api/user/selectExp")
    public Result selectExp(@RequestBody ParticipitionExpPOJO participitionExpPOJO)
    {
        userService.selectExpById(participitionExpPOJO.experimentPOJO.getId(), participitionExpPOJO.userPOJO.getId());
        return ResultFactory.buildSuccessResult_p("选择实验成功", null);
    }

    @CrossOrigin
    @PostMapping("/api/user/withdrawExp")
    public Result withDrawExp(@RequestBody ParticipitionExpPOJO participitionExpPOJO)
    {
        userService.withDrawById(participitionExpPOJO.experimentPOJO.getId(), participitionExpPOJO.userPOJO.getId());
        return ResultFactory.buildSuccessResult_p("退选实验成功", null);
    }



}
