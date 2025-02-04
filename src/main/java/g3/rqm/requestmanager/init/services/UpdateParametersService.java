package g3.rqm.requestmanager.init.services;

import g3.rqm.requestmanager.init.dtos.RequestManagerParam;
import g3.rqm.requestmanager.init.repositories.cache.CacheParamRepository;
import g3.rqm.requestmanager.init.repositories.state.StateParamRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateParametersService {

    private final StateParamRepository stateParamRepository;
    private final CacheParamRepository cacheParamRepository;
    private final Logger LOGGER = LogManager.getLogger(UpdateParametersService.class);

    public UpdateParametersService(StateParamRepository stateParamRepository, CacheParamRepository cacheParamRepository) {
        this.stateParamRepository = stateParamRepository;
        this.cacheParamRepository = cacheParamRepository;
    }

    private List<RequestManagerParam> getRemoteParams() {
        return stateParamRepository.getParams();
    }

    private void setLocalParam(RequestManagerParam remoteParam) {
        cacheParamRepository.addParam(remoteParam.getParamName(), remoteParam.getParamValue());
    }

    public void updateSelfParams() {
        try {
            for (RequestManagerParam managerParam : getRemoteParams()) {
                setLocalParam(managerParam);
            }
        } catch (Exception ex) {
            LOGGER.error("Error while init queue manager params. Exception message: ", ex);
            System.exit(1);
        }
    }
}
