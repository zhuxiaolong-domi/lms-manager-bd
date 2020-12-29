package njust.lmsbackend.lms.DAO;

import njust.lmsbackend.lms.POJO.ComputerLabPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputerLabDAO extends JpaRepository<ComputerLabPOJO, Integer> {
    ComputerLabPOJO findById(int id);
}
