package g3.rqm.requestmanager.restapi.dtos.requests.listitems;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class QueueInfoSorting {
    private String field;
    private boolean desc;
}
