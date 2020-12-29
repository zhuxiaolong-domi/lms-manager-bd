package njust.lmsbackend.lms.DAO;

import njust.lmsbackend.lms.POJO.SeatPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatDAO extends JpaRepository<ComputerLabPOJO, Integer>{
}
