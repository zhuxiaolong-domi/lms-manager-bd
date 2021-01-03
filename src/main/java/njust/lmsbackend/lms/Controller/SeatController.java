package njust.lmsbackend.lms.Controller;

import njust.lmsbackend.lms.Result.ResultFactory;
import njust.lmsbackend.lms.POJO.SeatPOJO;
import njust.lmsbackend.lms.POJO.ComputerLabPOJO;
import njust.lmsbackend.lms.Result.Result;
import njust.lmsbackend.lms.Service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class SeatController {
    @Autowired
    SeatService seatService;

    /**
     * 列出所有机位信息
     *
     * @return 机位列表
     * @throws Exception 异常
     */
    @CrossOrigin
    @GetMapping("/api/seat")
    public Result listSeats() throws Exception {
        return ResultFactory.buildSuccessResult("查询机位信息成功", seatService.listAllSeats());
    }

    /**
     * 列出指定实验室的机位信息
     */
    @CrossOrigin
    @GetMapping("/api/seat/labId")
    public Result listSeatsByLabId(int labId) throws Exception {
        return ResultFactory.buildSuccessResult("查询指定实验室机位成功", seatService.listSeats(labId));
    }

    /**
     * 添加新机位
     *
     * @param seatPOJO        机位对象
     * @param computerLabPOJO 实验室对象
     */
    @CrossOrigin
    @PostMapping("/api/seat/add")
    public Result add(@RequestBody SeatPOJO seatPOJO, ComputerLabPOJO computerLabPOJO) {
        seatService.addSeat(seatPOJO, computerLabPOJO);
        return ResultFactory.buildSuccessResult_p("添加成功", null);
    }

    /**
     * 更新指定机位的可用状态
     *
     * @param seatPOJO 机位对象
     */
    @CrossOrigin
    @PostMapping("/api/seat/update")
    public Result update(@RequestBody SeatPOJO seatPOJO) {
        seatService.changeSeatState(seatPOJO);
        return ResultFactory.buildSuccessResult_p("修改成功", null);
    }

    /**
     * 根据id删除现有机位
     *
     * @param seatPOJO        机位对象
     * @param computerLabPOJO 实验室对象
     */
    @CrossOrigin
    @PostMapping("/api/seat/delete")
    public Result deleteSeat(@RequestBody SeatPOJO seatPOJO, ComputerLabPOJO computerLabPOJO) throws Exception {
        seatService.deleteSeat(seatPOJO, computerLabPOJO);
        return ResultFactory.buildSuccessResult_p("删除成功", null);
    }

    /**
     * 获取当时的时间
     */
    @CrossOrigin
    @GetMapping("/api/seat/time")
    public Date getRecentTime() {
        Date date = new Date();
        return date;
    }
}
