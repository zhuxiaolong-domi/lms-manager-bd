package njust.lmsbackend.lms.DAO;

import njust.lmsbackend.lms.POJO.SeatPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatDAO extends JpaRepository<SeatPOJO, Integer> {
    SeatPOJO findByLabIdAndSeatId(int labId,int seatId);
    @Query(value = "select count(*) from tb_seat where state=1 and labid=?1", nativeQuery = true)
    int updateCanUsedSeat(int labid);
    void deleteSeatPOJOByLabIdAndSeatId(int labId, int seatId);
}
