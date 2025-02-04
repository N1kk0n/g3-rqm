package g3.rqm.requestmanager.routing.actions;

import g3.rqm.requestmanager.routing.dtos.kafka.Content;

public interface Action {
    int execute(Content content);
}
