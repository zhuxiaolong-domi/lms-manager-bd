package njust.lmsbackend.lms.Controller;
import njust.lmsbackend.lms.POJO.ComputerLabPOJO;
import njust.lmsbackend.lms.Result.Result;
import njust.lmsbackend.lms.Result.ResultFactory;
import njust.lmsbackend.lms.Service.ComputerLabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ComputerLabController {
    @Autowired
    ComputerLabService computerlabService;

    /**
     * 列出所有实验室
     * @return 实验室列表
     * @throws Exception 异常
     */
    @CrossOrigin
    @GetMapping("/api/admin/lab")
    public Result listLabs() throws Exception
    {
        return ResultFactory.buildSuccessResult("查询实验室成功", computerlabService.listAllLabs());
    }

    @CrossOrigin
    @PostMapping("/api/admin/lab/add")
    public Result addOrUpdate(@RequestBody ComputerLabPOJO computerlabPOJO)
    {
        computerlabService.addComputerLab(computerlabPOJO);
        return ResultFactory.buildSuccessResult_p("修改成功", null);
    }

    /**
     * 根据id删除现有实验室
     * @param computerlabPOJO 实验室对象
     * @throws Exception 异常
     */
    @CrossOrigin
    @PostMapping("/api/admin/lab/delete")
    public Result delete(@RequestBody ComputerLabPOJO computerlabPOJO) throws Exception {
        computerlabService.deleteById(computerlabPOJO.getId());
        return ResultFactory.buildSuccessResult_p("删除成功", null);
    }

}
