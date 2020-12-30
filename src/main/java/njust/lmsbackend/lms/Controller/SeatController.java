package njust.lmsbackend.lms.Controller;
import com.sun.xml.txw2.output.ResultFactory;
import njust.lmsbackend.lms.POJO.ComputerLabPOJO;
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

    /**
     * 添加新机位
     * @param seatPOJO 机位对象
     */
    @CrossOrigin
    @PostMapping("/api/admin/seat/add")
    public Result add(@RequestBody SeatPOJO seatPOJO,ComputerLabPOJO computerLabPOJO,int labId)
    {
        seatService.addSeat(seatPOJO,computerLabPOJO,labId);
        return ResultFactory.buildSuccessResult_p("添加成功", null);
    }

    /**
     * 更新指定机位的可用状态
     * @param seatPOJO 机位对象
     */
    @CrossOrigin
    @PostMapping("/api/admin/seat/update")
    public Result update(@RequestBody ComputerLabPOJO computerLabPOJO,SeatPOJO seatPOJO,int labId,int seatId){
        seatService.changeSeatState(computerLabPOJO,seatPOJO,labId);
        return ResultFactory.buildSuccessResult_p("更新成功",null);
    }

    /**
     * 根据id删除现有机位
     * @param seatPOJO 机位对象
     */
    @CrossOrigin
    @PostMapping("/api/admin/seat/delete")
    public Result delete(@RequestBody ComputerLabPOJO computerLabPOJO,SeatPOJO seatPOJO,int labId,int seatId,int deleteNum) throws Exception {
        seatService.deleteSeat(computerLabPOJO,seatPOJO,labId,seatId,deleteNum);
        return ResultFactory.buildSuccessResult_p("删除成功", null);
    }

}
