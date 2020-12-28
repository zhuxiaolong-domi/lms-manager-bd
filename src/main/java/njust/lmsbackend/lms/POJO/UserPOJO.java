package njust.lmsbackend.lms.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Entity
@Table(name = "tb_user")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class UserPOJO {
    @Id
    @Column(name = "id")
    String id;

    private String name;
    private String pwd;
    //0->学生 1->老师 2->管理员
    @Column(name = "identity")
    private int identity;

    public UserPOJO() {
    }

    public UserPOJO(String id, String name, String pwd, int identity) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.identity = identity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }
}
