package g3.rqm.requestmanager.routing.repositories.state;

import g3.rqm.requestmanager.routing.dtos.kafka.Message;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.UUID;

@Repository
public class TopicMessageRepository {
    private final JdbcTemplate jdbcTemplate;

    public TopicMessageRepository(@Qualifier("stateDataSource") DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int saveMessage(Message message) {
        return jdbcTemplate.update("""
                INSERT INTO state_schema.topic_message (unique_id, route_id, producer_component, consumer_component, is_received, content)
                VALUES (?, ?, ?, ?, ?, ?::JSON)
                """
                , message.getUnique_id(), message.getRoute_id(), message.getProducer(), message.getConsumer(), message.getIs_received(), message.getContent());
    }

    public Message getMessage(UUID message_uuid) {
        return jdbcTemplate.queryForObject("""
                SELECT * FROM state_schema.topic_message
                WHERE unique_id = ?
                """
                , (rs, rowNum) -> {
                    Message message = new Message();
                    message.setUnique_id(UUID.fromString(rs.getString("unique_id")));
                    message.setRoute_id(rs.getLong("route_id"));
                    message.setProducer(rs.getString("producer_component"));
                    message.setConsumer(rs.getString("consumer_component"));
                    message.setIs_received(rs.getBoolean("is_received"));
                    message.setContent(rs.getString("content"));
                    return message;
                }, message_uuid);
    }

    public void commitReceiveMessage(UUID message_uuid) {
        jdbcTemplate.update("""
            UPDATE state_schema.topic_message set is_received = true
            WHERE unique_id = ?
            """, message_uuid);
    }

    public void deleteRouteMessages(Long routeId) {
        jdbcTemplate.update("""
            DELETE FROM state_schema.topic_message
            WHERE route_id = ?
            """, routeId);
    }
}
