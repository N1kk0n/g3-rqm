package g3.rqm.requestmanager.restapi.controllers;

import g3.rqm.requestmanager.restapi.exceptions.CacheAlreadyFullException;
import g3.rqm.requestmanager.restapi.services.CacheAccessService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MainController {
    private final CacheAccessService cacheAccessService;

    public MainController(CacheAccessService cacheAccessService) {
        this.cacheAccessService = cacheAccessService;
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody String body) {
        try {
            return new ResponseEntity<>(cacheAccessService.put(body), HttpStatus.OK);
        } catch (CacheAlreadyFullException e) {
            return new ResponseEntity<>(HttpStatus.LOCKED);
        }
    }
}
