package njust.lmsbackend.lms.DAO;

import njust.lmsbackend.lms.POJO.SeatPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatDAO extends JpaRepository<SeatPOJO, Integer> {
    SeatPOJO findSeatPOJOByLabId(int labId);

    void deleteSeatPOJOByLabIdAndSeatId(int labId, int seatId);
}
