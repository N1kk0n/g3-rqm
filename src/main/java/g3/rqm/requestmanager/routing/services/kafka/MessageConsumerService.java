package g3.rqm.requestmanager.routing.services.kafka;

import g3.rqm.requestmanager.routing.dtos.kafka.Message;
import g3.rqm.requestmanager.routing.repositories.state.RouteStateRepository;
import g3.rqm.requestmanager.routing.repositories.state.TopicMessageRepository;
import g3.rqm.requestmanager.routing.services.RouterService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MessageConsumerService {

    private final TopicMessageRepository topicMessageRepository;
    private final RouteStateRepository routeStateRepository;
    private final RouterService routerService;
    private final String TOPIC_NAME = "rqm-topic";
    private final Logger LOGGER = LogManager.getLogger(MessageConsumerService.class);

    public MessageConsumerService(TopicMessageRepository topicMessageRepository,
                                  RouteStateRepository routeStateRepository,
                                  RouterService routerService) {
        this.topicMessageRepository = topicMessageRepository;
        this.routeStateRepository = routeStateRepository;
        this.routerService = routerService;
    }

    @KafkaListener(topics = TOPIC_NAME, groupId = "rqm")
    public void receiveMessage(UUID message_uuid, Acknowledgment acknowledgment) {
        Message message = topicMessageRepository.getMessage(message_uuid);
        if (message.getIs_received()) {
            LOGGER.info("Message received earlier. Message: " + message);
            acknowledgment.acknowledge();
            return;
        }
        if (routeStateRepository.isRouteNotActive(message.getRoute_id())) {
            LOGGER.info("Route for this message is not active. Message: " + message);
            acknowledgment.acknowledge();
            return;
        }
        LOGGER.info("Message received: " +  message);

        try {
            routerService.onRoute(message);
        } catch (RuntimeException ex) {
            LOGGER.error("Runtime exception while processing message: " + message, ex);
            routeStateRepository.setRouteStatus(message.getRoute_id(), -1);
            return;
        }

        topicMessageRepository.commitReceiveMessage(message_uuid);
        acknowledgment.acknowledge();
    }
}
