package g3.rqm.requestmanager.restapi.dtos.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class DeleteTaskBody {
    private List<Long> task_ids;
}
