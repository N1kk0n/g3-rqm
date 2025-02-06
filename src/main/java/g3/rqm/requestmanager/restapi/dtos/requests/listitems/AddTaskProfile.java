package g3.rqm.requestmanager.restapi.dtos.requests.listitems;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AddTaskProfile {
    private int profile_id;
    private int priority;
}
