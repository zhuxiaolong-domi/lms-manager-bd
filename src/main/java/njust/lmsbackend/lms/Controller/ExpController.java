package njust.lmsbackend.lms.Controller;

import njust.lmsbackend.lms.POJO.ExperimentPOJO;
import njust.lmsbackend.lms.Service.ExperimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exp")
public class ExpController {
    @Autowired
    private ExperimentService service;

    /*接收创建实验的请求*/
    @RequestMapping("/create")
    public String createExp(String teacherId, ExperimentPOJO experiment){
        service.startExp(teacherId,experiment);
        return "OK, create success";
    }

    /*测试根据实验编号查找老师*/
    @RequestMapping("/findTeacher")
    public String findTeacher(String expId){
        System.out.println("yes");
        return service.findTeacherByExp(expId).toString();
    }
}
