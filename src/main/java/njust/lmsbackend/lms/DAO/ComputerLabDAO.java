package njust.lmsbackend.lms.DAO;

import njust.lmsbackend.lms.POJO.ComputerLabPOJO;
import njust.lmsbackend.lms.POJO.UserPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NamedQuery;
import java.util.List;

@Repository
public interface ComputerLabDAO extends JpaRepository<ComputerLabPOJO, Integer> {
    ComputerLabPOJO findComputerLabPOJOById(int id);

    @Query(value = "select COUNT(*) from tb_seat where lab_id=?1", nativeQuery = true)
    int newestCapacity(int lab_id);

    @Query(value = "select COUNT(*) from tb_seat where lab_id=?1 and state=1", nativeQuery = true)
    int newestRest(int lab_id);

    @Modifying
    @Transactional
    @Query(value = "update tb_computerlab set capacity=?1 where id=?2", nativeQuery = true)
    void updateCapacity(int capacity,int lab_id);
}
