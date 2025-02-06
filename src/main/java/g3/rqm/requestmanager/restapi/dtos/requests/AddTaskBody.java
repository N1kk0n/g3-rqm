package g3.rqm.requestmanager.restapi.dtos.requests;

import g3.rqm.requestmanager.restapi.dtos.requests.listitems.AddTaskProfile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class AddTaskBody {
    private String program_name;
    private String department;
    private String gp;
    private String info;
    private String data_link;
    private List<AddTaskProfile> profiles;
}
