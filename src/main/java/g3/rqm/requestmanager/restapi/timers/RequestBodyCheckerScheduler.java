package g3.rqm.requestmanager.restapi.timers;

import g3.rqm.requestmanager.restapi.services.CacheAccessService;
import g3.rqm.requestmanager.restapi.services.RequestCheckerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class RequestBodyCheckerScheduler {
    private final RequestCheckerService requestCheckerService;
    private final CacheAccessService cacheAccessService;
    private final Logger LOGGER = LogManager.getLogger(RequestBodyCheckerScheduler.class);

    public RequestBodyCheckerScheduler(RequestCheckerService requestCheckerService, CacheAccessService cacheAccessService) {
        this.requestCheckerService = requestCheckerService;
        this.cacheAccessService = cacheAccessService;
    }

    @Scheduled(fixedDelay = 2000)
    public void checkBody() {
        try {
            while (!cacheAccessService.cacheIsEmpty()) {
                String body = cacheAccessService.get();
                requestCheckerService.checkProgramExists(body);
            }
        } catch (Exception ex) {
            LOGGER.error("Error while update decision: ", ex);
        }
    }
}
