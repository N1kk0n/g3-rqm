package g3.rqm.requestmanager.restapi.services;

import g3.rqm.requestmanager.restapi.dtos.RequestBody;
import g3.rqm.requestmanager.restapi.exceptions.CacheAlreadyFullException;
import g3.rqm.requestmanager.restapi.repositories.cache.RequestBodyCacheRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CacheAccessService {

    private final RequestBodyCacheRepository requestBodyCacheRepository;

    public CacheAccessService(RequestBodyCacheRepository requestBodyCacheRepository) {
        this.requestBodyCacheRepository = requestBodyCacheRepository;
    }

    public String put(String body) throws CacheAlreadyFullException {
        if (cacheAvailable()) {
            UUID uniqueId = UUID.nameUUIDFromBytes(body.getBytes());
            requestBodyCacheRepository.addRequestBody(uniqueId, body);
            return uniqueId.toString();
        }
        throw new CacheAlreadyFullException();
    }

    public String get() {
        RequestBody requestBody = requestBodyCacheRepository.getRequestBody();
        requestBodyCacheRepository.deleteBody(requestBody.getId());
        return requestBody.getBody();
    }

    public boolean cacheAvailable() {
        return requestBodyCacheRepository.getCacheCapacity() - requestBodyCacheRepository.getCacheSize() > 0;
    }

    public boolean cacheIsEmpty() {
        return requestBodyCacheRepository.getCacheSize() == 0;
    }
}
