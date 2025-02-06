package g3.rqm.requestmanager.restapi.dtos.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ChangePriorityBody {
    private long task_id;
    private String profile_name;
    private int priority;
}
