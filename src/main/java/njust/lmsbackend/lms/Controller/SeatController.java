package njust.lmsbackend.lms.Controller;
import njust.lmsbackend.lms.POJO.SeatPOJO;
import njust.lmsbackend.lms.Result.Result;
import njust.lmsbackend.lms.Result.ResultFactory;
import njust.lmsbackend.lms.Service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SeatController {
    @Autowired
    SeatService seatService;

    /**
     * 列出所有机位信息
     * @return 机位列表
     * @throws Exception 异常
     */
    @CrossOrigin
    @GetMapping("/api/admin/seat")
    public Result listSeats() throws Exception
    {
        return ResultFactory.buildSuccessResult("查询机位信息成功", seatService.listAllSeats());
    }

    @CrossOrigin
    @PostMapping("/api/admin/seat/add")
    public Result addOrUpdate(@RequestBody SeatPOJO seatPOJO)
    {
        seatService.addComputerLab(seatPOJO);
        return ResultFactory.buildSuccessResult_p("修改成功", null);
    }

    /**
     * 根据id删除现有机位
     * @param seatPOJO 机位对象
     * @throws Exception 异常
     */
    @CrossOrigin
    @PostMapping("/api/admin/seat/delete")
    public Result delete(@RequestBody SeatPOJO seatPOJO) throws Exception {
        seatService.deleteById(seatPOJO.getId());
        return ResultFactory.buildSuccessResult_p("删除成功", null);
    }

}
