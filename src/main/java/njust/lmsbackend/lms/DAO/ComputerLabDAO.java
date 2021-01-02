package njust.lmsbackend.lms.DAO;

import njust.lmsbackend.lms.POJO.ComputerLabPOJO;
import njust.lmsbackend.lms.POJO.UserPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedQuery;
import java.util.List;

@Repository
public interface ComputerLabDAO extends JpaRepository<ComputerLabPOJO, Integer> {
    ComputerLabPOJO findComputerLabPOJOById(int id);

    @Query(value = "select COUNT(*) from tb_seat where lab_id=?1", nativeQuery = true)
    public int recentCapacity(int lab_id);
}
