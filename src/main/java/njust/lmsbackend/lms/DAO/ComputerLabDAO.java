package njust.lmsbackend.lms.DAO;

import njust.lmsbackend.lms.POJO.ComputerLabPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedQuery;
import java.util.List;

@Repository
public interface ComputerLabDAO extends JpaRepository<ComputerLabPOJO, Integer> {
    ComputerLabPOJO findComputerLabPOJOById(int id);
}
