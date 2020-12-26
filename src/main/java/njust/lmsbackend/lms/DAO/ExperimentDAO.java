package njust.lmsbackend.lms.DAO;

import njust.lmsbackend.lms.POJO.ExperimentPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ExperimentDAO extends JpaRepository<ExperimentPOJO, String> {
    ExperimentPOJO findExperimentPOJOById(String id);

    @Transactional
    @Modifying
    @Query(value = "insert into tb_experiment(id, name, start, 'end', description, 'max') values(id=:#{#experimentPOJO.id}, name=:#{#experimentPOJO.name}, start=:#{#experimentPOJO.start}, end=:#{#experimentPOJO.end}, description=:#{#experimentPOJO.description}, max=:#{#experimentPOJO.max}})", nativeQuery = true)
    void addExperiment(ExperimentPOJO experiment);
}
