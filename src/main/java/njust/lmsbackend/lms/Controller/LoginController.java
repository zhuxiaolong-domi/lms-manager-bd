package njust.lmsbackend.lms.Controller;

import njust.lmsbackend.lms.POJO.UserPOJO;
import njust.lmsbackend.lms.Result.Result;
import njust.lmsbackend.lms.Result.ResultFactory;
import njust.lmsbackend.lms.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping("/api/user/login")
    public Result Login(@RequestBody UserPOJO userPOJO) {
        String pwd = userService.findPwdById(userPOJO.getId()).getPwd();
        if (pwd.equals(userPOJO.getPwd())) {
            return ResultFactory.buildSuccessResult_p("学生登陆成功", userPOJO.getIdentity());
        } else {
            return ResultFactory.buildFailResult("登陆失败");
        }
    }
}
