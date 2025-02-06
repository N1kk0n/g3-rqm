package g3.rqm.requestmanager.restapi.controllers;

import g3.rqm.requestmanager.restapi.dtos.requests.ChangePriorityBody;
import g3.rqm.requestmanager.restapi.dtos.requests.DeleteTaskBody;
import g3.rqm.requestmanager.restapi.dtos.responses.ChangePriorityResponse;
import g3.rqm.requestmanager.restapi.dtos.responses.DeleteTaskResponse;
import g3.rqm.requestmanager.restapi.dtos.responses.TaskDataResponse;
import g3.rqm.requestmanager.restapi.exceptions.CacheAlreadyFullException;
import g3.rqm.requestmanager.restapi.services.CacheAccessService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("operation")
public class OperationController {
    private final CacheAccessService cacheAccessService;

    public OperationController(CacheAccessService cacheAccessService) {
        this.cacheAccessService = cacheAccessService;
    }

    @PostMapping("/add_task")
    public ResponseEntity<String> add(@RequestBody String body) {
        try {
            return new ResponseEntity<>(cacheAccessService.put(body), HttpStatus.OK);
        } catch (CacheAlreadyFullException e) {
            return new ResponseEntity<>(HttpStatus.LOCKED);
        }
    }

    @GetMapping("/get_task_id")
    public ResponseEntity<TaskDataResponse> get(@RequestParam UUID request_id) {
        return new ResponseEntity<>(new TaskDataResponse(), HttpStatus.OK);
    }

    @PostMapping("/change_priority")
    public ResponseEntity<ChangePriorityResponse> change(@RequestBody ChangePriorityBody body) {
        return new ResponseEntity<>(new ChangePriorityResponse(), HttpStatus.OK);
    }

    @PostMapping("delete_task")
    public ResponseEntity<DeleteTaskResponse> delete(@RequestBody DeleteTaskBody body) {
        return new ResponseEntity<>(new DeleteTaskResponse(), HttpStatus.OK);
    }
}
