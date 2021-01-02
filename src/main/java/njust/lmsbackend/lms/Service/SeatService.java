package njust.lmsbackend.lms.Service;
import njust.lmsbackend.lms.DAO.SeatDAO;
import njust.lmsbackend.lms.POJO.SeatPOJO;
import  njust.lmsbackend.lms.POJO.ComputerLabPOJO;
import njust.lmsbackend.lms.DAO.ComputerLabDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {
    @Autowired
    SeatDAO seatDAO;
    ComputerLabDAO computerLabDAO;
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
    public void addSeat(SeatPOJO seatPOJO, ComputerLabPOJO computerLabPOJO) {
        seatDAO.save(seatPOJO);
        computerLabPOJO.setCapacity(computerLabPOJO.getCapacity()+1);
    }
    /**
     * 根据实验室号和机号删除机位
     */
    public void deleteSeat(SeatPOJO seatPOJO,ComputerLabPOJO computerLabPOJO) {
        seatDAO.delete(seatPOJO);
        computerLabPOJO.setCapacity(computerLabPOJO.getCapacity());
    }

    /**
     * 更改当前机位状态
     */
    public void changeSeatState(SeatPOJO seatPOJO) {
        if(seatPOJO.getState()==1){
            seatPOJO.setState(0);
        }

        else{
            seatPOJO.setState(1);
        }

        seatDAO.save(seatPOJO);
    }
}
