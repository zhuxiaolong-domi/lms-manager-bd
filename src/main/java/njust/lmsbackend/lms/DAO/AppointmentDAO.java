package njust.lmsbackend.lms.DAO;

import njust.lmsbackend.lms.POJO.AppointmentPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentDAO extends JpaRepository<AppointmentPOJO, String> {

    //List<AppointmentPOJO> findAllByStudentId(String studentId);

    AppointmentPOJO findByStudentId(String studentId);
}
