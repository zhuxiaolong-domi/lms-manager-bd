package njust.lmsbackend.lms.DAO;

import njust.lmsbackend.lms.POJO.ComputerLabPOJO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComputerLabDAO extends JpaRepository<ComputerLabPOJO, String>{
}
