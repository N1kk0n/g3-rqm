package g3.rqm.requestmanager.routing.actions;

import g3.rqm.requestmanager.routing.dtos.kafka.Content;

public class Test implements Action {
    @Override
    public int execute(Content content) {
        return 2;
    }
}
