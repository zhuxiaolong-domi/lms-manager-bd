package njust.lmsbackend.lms.DAO;

import njust.lmsbackend.lms.POJO.ParticipationPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ParticipationDAO extends JpaRepository<ParticipationPOJO, String> {
    @Modifying
    @Transactional
    void deleteByExpIdAndStudentId(String ExpId, String studentId);

    List<ParticipationPOJO> findAllByStudentId(String studentId);

    Integer deleteParticipationPOJOByExpId(String expId);

    List<ParticipationPOJO> findAllByExpId(String expId);

    ParticipationPOJO findParticipationPOJOByStudentIdAndExpId(String studentId, String expId);

    @Modifying
    @Query(value = "update tb_participation set score=?1 where student_id=?2 AND exp_id=?3", nativeQuery = true)
    Integer updateScore(Integer score, String studentId, String expId);

    ParticipationPOJO findByStudentId(String studentId);

    @Modifying
    @Transactional
    @Query(value = "update tb_participation set report=?1 where student_id=?2 AND exp_id=?3", nativeQuery = true)
    void updateReport(String report, String studentId, String expId);
}
