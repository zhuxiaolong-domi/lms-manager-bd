package njust.lmsbackend.lms.Service;
import njust.lmsbackend.lms.DAO.SeatDAO;
import njust.lmsbackend.lms.POJO.SeatPOJO;
import  njust.lmsbackend.lms.POJO.ComputerLabPOJO;
import njust.lmsbackend.lms.Service.ComputerLabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {
    @Autowired
    SeatDAO seatDAO;
    ComputerLabService computerLabService;
    /**
     * 根据机位和实验室id返回机位具体信息
     * @return 机位列表
     */
    public List<SeatPOJO> listAllSeats()
    {
        Sort sort = Sort.by(Sort.Direction.ASC, "labId");
        return seatDAO.findAll(sort);
    }
    /**
     * @param seatPOJO 添加机位对象
     */
    public void addSeat(SeatPOJO seatPOJO, ComputerLabPOJO computerLabPOJO,int labId) {

        seatDAO.save(seatPOJO);
        computerLabPOJO.setCapacity(computerLabPOJO.getCapacity()+1);
        seatDAO.save(seatPOJO);
    }
    /**
     * 根据实验室号和机号删除机位
     */
    public void deleteSeat(ComputerLabPOJO computerLabPOJO,SeatPOJO seatPOJO,int labId,int seatId,int deleteNum) {
        seatDAO.deleteSeatPOJOByLabIdAndSeatId(labId,seatId);
        computerLabPOJO.setCapacity(computerLabPOJO.getCapacity()-deleteNum);
        seatDAO.save(seatPOJO);
    }

    /**
     * 更改当前机位状态
     */
    public void changeSeatState(SeatPOJO seatPOJO,int labId) {
        if(seatPOJO.getState()==1){
            seatPOJO.setState(0);
        }

        else{
            seatPOJO.setState(1);
        }

        seatDAO.save(seatPOJO);
    }
}
