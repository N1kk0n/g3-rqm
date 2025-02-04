package g3.rqm.requestmanager.restapi.repositories.cache;

import g3.rqm.requestmanager.restapi.dtos.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Repository
public class RequestBodyCacheRepository {
    private final NamedParameterJdbcTemplate template;

    @Autowired
    public RequestBodyCacheRepository(@Qualifier("cacheJdbcTemplate") NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public void addRequestBody(UUID uniqueId, String body) {
        String sql = """
            insert into request_body_cache(unique_id, body)
            values (:uniqueId, :body)
        """;
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("uniqueId", uniqueId)
                .addValue("body", body);
        template.update(sql, sqlParameterSource);
    }

    public RequestBody getRequestBody() {
        String sql = """
            select * from request_body_cache
            order by id limit 1
        """;
        SqlParameterSource namedParameters = new MapSqlParameterSource();
        return template.queryForObject(sql, namedParameters, (rs, rowNum) ->
                new RequestBody(rs.getLong("ID"),
                                UUID.fromString(rs.getString("UNIQUE_ID")),
                                rs.getString("BODY")));
    }

    public void deleteBody(Long id) {
        String sql = """
            delete from request_body_cache
            where ID = :id
        """;
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", id);
        template.update(sql, sqlParameterSource);
    }

    public Integer getCacheSize() {
        String sql = """
            select count(*) from request_body_cache
        """;
        SqlParameterSource namedParameters = new MapSqlParameterSource();
        return template.queryForObject(sql, namedParameters, Integer.class);
    }

    public Integer getCacheCapacity() {
        return 100;
    }
}
