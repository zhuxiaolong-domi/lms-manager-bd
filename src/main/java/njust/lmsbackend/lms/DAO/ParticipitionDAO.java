package njust.lmsbackend.lms.DAO;

import njust.lmsbackend.lms.POJO.ParticipitionPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ParticipitionDAO extends JpaRepository<ParticipitionPOJO, String> {
    //void deleteByexp_idAndstudent_id(String exp_id, String studentId);
    @Modifying
    @Transactional
    void deleteByExpIdAndStudentId(String ExpId, String studentId);

    List<ParticipitionPOJO> findAllByStudentId(String studentId);
}
