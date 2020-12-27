package njust.lmsbackend.lms.DAO;

import njust.lmsbackend.lms.POJO.ParticipationPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipationDAO extends JpaRepository<ParticipationPOJO, String>{
    Integer deleteParticipationPOJOByExpId(String expId);
}
