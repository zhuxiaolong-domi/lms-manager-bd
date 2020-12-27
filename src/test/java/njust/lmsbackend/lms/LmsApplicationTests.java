package njust.lmsbackend.lms;

import njust.lmsbackend.lms.DAO.ExperimentDAO;
import njust.lmsbackend.lms.DAO.UserDAO;
import njust.lmsbackend.lms.POJO.ExperimentPOJO;
import njust.lmsbackend.lms.POJO.StartExpPOJO;
import njust.lmsbackend.lms.POJO.UserPOJO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Date;
import java.util.UUID;

@SpringBootTest
class LmsApplicationTests {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ExperimentDAO experimentDAO;

    @Test
    void contextLoads() {
    }

    @Test
    void testSave(){
        UserPOJO userPOJO = new UserPOJO();
        userPOJO.setId("918106840133");
        userPOJO.setName("柳智添");
        userPOJO.setPwd("12345");
        userPOJO.setIdentity(0);
        userDAO.save(userPOJO);
    }

    @Test
    void testExpSave(){
        /*ExperimentPOJO experimentPOJO = new ExperimentPOJO();
        experimentPOJO.setId(UUID.randomUUID().toString().replaceAll("-",""));
        experimentPOJO.setName("test1");
        experimentPOJO.setDescription("test");

        experimentDAO.save(experimentPOJO);*/
        System.out.println(new Date());
    }


}
