package njust.lmsbackend.lms.DAO;

import njust.lmsbackend.lms.POJO.StartExpPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StartExpDAO extends JpaRepository<StartExpPOJO, String> {
    StartExpPOJO findStartExpPOJOByExpId(String expId);

    StartExpPOJO findStartExpPOJOByExpIdAndTeacherId(String expId, String teacherId);

    List<StartExpPOJO> findStartExpPOJOSByTeacherId(String teacherId);
}
