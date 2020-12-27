package njust.lmsbackend.lms.DAO;

import njust.lmsbackend.lms.POJO.UserPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<UserPOJO, String> {
    UserPOJO findUserPOJOById(String id);
    @Query(value = "select * from tb_user where identity=0", nativeQuery = true)
    List<UserPOJO> listAllStudents();

