package g3.rqm.requestmanager.restapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class RequestBody {
    Long id;
    UUID unique_id;
    String body;
}
