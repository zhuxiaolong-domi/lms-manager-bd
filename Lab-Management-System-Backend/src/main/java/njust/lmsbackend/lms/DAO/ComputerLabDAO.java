package njust.lmsbackend.lms.DAO;
import  njust.lmsbackend.lms.POJO.ComputerLabPOJO;
import njust.lmsbackend.lms.POJO.UserPOJO;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ComputerLabDAO extends JpaRepository<UserPOJO, String>{
}
