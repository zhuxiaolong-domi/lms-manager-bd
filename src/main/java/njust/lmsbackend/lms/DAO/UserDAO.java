package njust.lmsbackend.lms.DAO;

import njust.lmsbackend.lms.POJO.ExperimentPOJO;
import njust.lmsbackend.lms.POJO.UserPOJO;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserDAO extends JpaRepository<UserPOJO, String> {
    @Query(value = "select * from tb_user where identity=0", nativeQuery = true)
    List<UserPOJO> listAllStudents();
}
