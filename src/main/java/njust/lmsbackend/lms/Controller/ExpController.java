package njust.lmsbackend.lms.Controller;

import njust.lmsbackend.lms.POJO.ExperimentPOJO;
import njust.lmsbackend.lms.Service.ExperimentService;
import njust.lmsbackend.lms.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exp")
public class ExpController {
    @Autowired
    private ExperimentService service;

    /*接收创建实验的请求*/
    @RequestMapping("/create")
    public String createExp(String teacherId, ExperimentPOJO experiment){
        service.startExperiment(teacherId,experiment);
        return "OK, create success";
    }

    /*测试根据实验编号查找老师*/
    @PostMapping("/findTeacher")
    public String findTeacher(@RequestParam String expId){
        return service.findTeacherByExp(expId).toString();
    }

    /*测试修改已发布实验*/
    @RequestMapping("/updateExp")
    public String updateExp(String teacherId, ExperimentPOJO experiment){
        if(service.updateExperiment(experiment,teacherId)){
            return "ok, update success";
        }
        return "no, update failed";
    }

    /*测试删除已发布实验*/
    @RequestMapping("/deleteExp")
    public String deleteExp(String expId){
        return (service.deleteExperiment(expId) == true)? "ok, delete success":"no, delete failed";
    }
}
