package g3.rqm.requestmanager.restapi.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class RequestCheckerService {
    private final Logger LOGGER = LogManager.getLogger(RequestCheckerService.class);

    public boolean checkProgramExists(String body) {
        LOGGER.info(body);
        return true;
    }
}
