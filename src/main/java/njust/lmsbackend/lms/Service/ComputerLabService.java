package njust.lmsbackend.lms.Service;
import njust.lmsbackend.lms.DAO.ComputerLabDAO;
import njust.lmsbackend.lms.POJO.ComputerLabPOJO;
import njust.lmsbackend.lms.DAO.SeatDAO;
import njust.lmsbackend.lms.POJO.SeatPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputerLabService {
    @Autowired
    ComputerLabDAO computerlabDAO;
    @Autowired
    SeatDAO seatDAO;
    /**
     * 返回所有的实验室 根据id
     * @return 实验室列表
     */
    public List<ComputerLabPOJO> listAllLabs()
    {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        return computerlabDAO.findAll(sort);
    }

    /**
     * @param computerlabPOJO 实验室对象
     */
    public void addComputerLab(ComputerLabPOJO computerlabPOJO) {
        computerlabDAO.save(computerlabPOJO);
    }

    /**
     * 根据id删除实验室
     *
     * @param id 实验室的id
     */
    public void deleteById(int id) {
        computerlabDAO.deleteById(id);
    }

    /**
     * 更新实验室总机位数目
     */
    public  void updateCapacity(ComputerLabPOJO computerLabPOJO){
        computerLabPOJO.setCapacity(computerlabDAO.newestCapacity(computerLabPOJO.getId()));
        computerlabDAO.save(computerLabPOJO);
    }

    /**
     * 更新实验室可用机位数目
     */
    public  void updateRest(ComputerLabPOJO computerLabPOJO){
        computerLabPOJO.setRest(computerlabDAO.newestRest(computerLabPOJO.getId()));
        computerlabDAO.save(computerLabPOJO);
    }
    /**
     * 查询指定实验室地址
     * @param id
     * @return
     */
    public ComputerLabPOJO findAddressByLabId(int id) {
        return computerlabDAO.findComputerLabPOJOById(id);
    }

}
