package njust.lmsbackend.lms.Service;

import njust.lmsbackend.lms.DAO.SeatDAO;
import njust.lmsbackend.lms.POJO.SeatPOJO;
import njust.lmsbackend.lms.POJO.ComputerLabPOJO;
import njust.lmsbackend.lms.DAO.ComputerLabDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {
    @Autowired
    SeatDAO seatDAO;
    @Autowired
    ComputerLabDAO computerLabDAO;

    /**
     * 返回存在的所有机位具体信息
     *
     * @return 机位列表
     */
    public List<SeatPOJO> listAllSeats() {
        Sort sort = Sort.by(Sort.Direction.ASC, "labId");
        return seatDAO.findAll(sort);
    }

    /**
     * 根据id返回指定实验室的所有机位信息
     */
    public List<SeatPOJO> listSeats(int labId) {
        return seatDAO.findSeatPOJOByLabId(labId);
    }

    /**
     * 添加新机位并更新实验室容量
     *
     * @param seatPOJO 机位对象
     */
    public void addSeat(SeatPOJO seatPOJO, ComputerLabPOJO computerLabPOJO) {
        seatDAO.save(seatPOJO);
        int newCapacity = computerLabDAO.newestCapacity(computerLabPOJO.getId());
        computerLabDAO.updateCapacity(newCapacity, computerLabPOJO.getId());
    }

    /**
     * 删除机位并更新实验室容量
     *
     * @param seatPOJO 机位对象
     */
    public void deleteSeat(SeatPOJO seatPOJO, ComputerLabPOJO computerLabPOJO) {
        seatDAO.delete(seatPOJO);
        int newCapacity = computerLabDAO.newestCapacity(computerLabPOJO.getId());
        computerLabDAO.updateCapacity(newCapacity, computerLabPOJO.getId());
    }

    /**
     * 更改当前机位状态
     */
    public void changeSeatState(SeatPOJO seatPOJO) {
        if (seatPOJO.getState() == 1) {
            seatPOJO.setState(0);
        } else {
            seatPOJO.setState(1);
        }
        seatDAO.save(seatPOJO);
    }
}
