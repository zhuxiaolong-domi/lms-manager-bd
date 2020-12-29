package njust.lmsbackend.lms.DAO;

import njust.lmsbackend.lms.POJO.ExperimentPOJO;
import njust.lmsbackend.lms.POJO.UserPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperimentDAO extends JpaRepository<ExperimentPOJO, String> {

    List<ExperimentPOJO> findAllByName(String keywords);

    ExperimentPOJO findByExpId(String expId);
}
