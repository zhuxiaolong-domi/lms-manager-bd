package njust.lmsbackend.lms.Controller;
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

    @CrossOrigin
    @PostMapping("/api/admin/user/add")
    public Result addOrUpdate(@RequestBody UserPOJO userPOJO)
    {
        userService.addOrUpdate(userPOJO);
        return ResultFactory.buildSuccessResult_p("修改成功", null);
    }

    /**
     * 根据id删除id操作
     *
     * @param userPOJO 书籍对象
     * @throws Exception 异常
     */
    @CrossOrigin
    @PostMapping("/api/admin/user/delete")
    public Result delete(@RequestBody UserPOJO userPOJO) throws Exception {
        userService.deleteById(userPOJO.getId());
        return ResultFactory.buildSuccessResult_p("删除成功", null);
    }

}
