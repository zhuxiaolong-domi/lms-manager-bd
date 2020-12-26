package njust.lmsbackend.lms.DAO;

import njust.lmsbackend.lms.POJO.StartExpPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StartExpDAO extends JpaRepository<StartExpPOJO, String> {
    StartExpPOJO findStartExpPOJOByExpId(String expId);
}
