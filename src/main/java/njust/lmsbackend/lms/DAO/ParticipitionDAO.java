package njust.lmsbackend.lms.DAO;

import njust.lmsbackend.lms.POJO.ParticipitionPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipitionDAO extends JpaRepository<ParticipitionPOJO, String> {

}
