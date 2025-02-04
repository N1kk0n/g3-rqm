package g3.rqm.requestmanager.routing.dtos.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Operation {
    private Long route_id;
    private String name;
    private String component;
    private Integer result;
}
