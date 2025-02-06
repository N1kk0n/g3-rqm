package g3.rqm.requestmanager.restapi.controllers;

import g3.rqm.requestmanager.restapi.dtos.requests.QueueInfoBody;
import g3.rqm.requestmanager.restapi.dtos.responses.QueueInfoResponse;
import g3.rqm.requestmanager.restapi.dtos.responses.listitems.ProfileInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/info")
public class InfoController {
    //TODO: ADD Spring Cache

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("queue")
    public ResponseEntity<QueueInfoResponse> queue(@RequestBody QueueInfoBody body) {
        return new ResponseEntity<>(new QueueInfoResponse(), HttpStatus.OK);
    }

    @GetMapping("/profiles")
    public ResponseEntity<List<ProfileInfo>> profiles() {
        return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
    }

    @GetMapping("/profiles/{program_name}")
    public ResponseEntity<List<ProfileInfo>> profiles(@PathVariable(value = "program_name") String name) {
        return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
    }

    @GetMapping("/programs/{profile_name}")
    public ResponseEntity<List<ProfileInfo>> programs(@PathVariable(value = "profile_name") String name) {
        return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
    }
}
