package njust.lmsbackend.lms.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "tb_experiment")
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExperimentPOJO {
    @Id
    @Column(name = "id", length = 32)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "start")
    private Date start;

    @Column(name = "end")
    private Date end;

    @Column(name = "description")
    private String description;

    @Column(name = "max")
    private Integer max;

}
