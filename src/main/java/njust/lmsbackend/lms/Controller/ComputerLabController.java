package njust.lmsbackend.lms.Controller;

import njust.lmsbackend.lms.POJO.ComputerLabPOJO;
import njust.lmsbackend.lms.Result.Result;
import njust.lmsbackend.lms.Result.ResultFactory;
import njust.lmsbackend.lms.Service.ComputerLabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class ComputerLabController {
    @Autowired
    ComputerLabService computerlabService;

    /**
     * 列出所有实验室
     *
     * @return 实验室列表
     * @throws Exception 异常
     */
    @CrossOrigin
    @GetMapping("/api/lab")
    public Result listLabs() throws Exception {
        return ResultFactory.buildSuccessResult("查询实验室成功", computerlabService.listAllLabs());
    }

    /**
     * 添加或更改机房信息
     *
     * @param computerLabPOJO 机房对象
     * @return 修改成功信息
     */
    @CrossOrigin
    @Modifying
    @Transactional
    @PostMapping("/api/lab/add")
    public Result addOrUpdateLabs(@RequestBody ComputerLabPOJO computerLabPOJO) {
        computerlabService.addComputerLab(computerLabPOJO);
        return ResultFactory.buildSuccessResult_p("修改成功", null);
    }

    /**
     * 根据id删除现有实验室
     *
     * @param computerLabPOJO 实验室对象
     * @throws Exception 异常
     */
    @CrossOrigin
    @Modifying
    @Transactional
    @PostMapping("/api/lab/delete")
    public Result deleteLab(@RequestBody ComputerLabPOJO computerLabPOJO) throws Exception {
        computerlabService.deleteById(computerLabPOJO.getId());
        return ResultFactory.buildSuccessResult_p("删除成功", null);
    }

}
