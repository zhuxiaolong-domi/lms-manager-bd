package njust.lmsbackend.lms.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "tb_start_exp")
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StartExpPOJO {
    @Id
    @Column(name = "exp_id", length = 32)
    private String expId;

    @Column(name = "teacher_id", length = 12)
    private String teacherId;
}
