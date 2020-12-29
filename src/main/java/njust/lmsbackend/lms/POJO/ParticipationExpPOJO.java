package njust.lmsbackend.lms.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class ParticipationExpPOJO {


    public ExperimentPOJO experimentPOJO;
    public UserPOJO userPOJO;

    public ParticipationExpPOJO() {
    }

    public ParticipationExpPOJO(ExperimentPOJO experimentPOJO, UserPOJO userPOJO) {
        this.experimentPOJO = experimentPOJO;
        this.userPOJO = userPOJO;
    }

    public ExperimentPOJO getExperimentPOJO() {
        return experimentPOJO;
    }

    public void setExperimentPOJO(ExperimentPOJO experimentPOJO) {
        this.experimentPOJO = experimentPOJO;
    }

    public UserPOJO getUserPOJO() {
        return userPOJO;
    }

    public void setUserPOJO(UserPOJO userPOJO) {
        this.userPOJO = userPOJO;
    }
}
