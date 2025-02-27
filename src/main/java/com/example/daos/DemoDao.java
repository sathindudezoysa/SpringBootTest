package com.example.daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.daos.dto.Profile;

@Repository(value = "com.example.daos.DemoDao")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class DemoDao {
    private static final String QUERY = "SELECT name FROM test";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private JdbcTemplate jdbc;

    public List<String> getFirstName() {
        List<String> names = jdbcTemplate.queryForList(QUERY, new MapSqlParameterSource(), String.class);
        return names;
    }

    public List<Profile> getNames() {
        String q = "SELECT id, name FROM test";
        /**
         * Here the row mapper object is automatically created by spring boot
         */
        // return jdbc.query(q, (rs, rownum) -> new Profile(rs.getInt("id"),
        // rs.getString("name")));

        return jdbc.query(q, new RowMapper<Profile>() {
            @Override
            public Profile mapRow(ResultSet rs, int rownum) throws SQLException {
                return new Profile(rs.getInt("id"), rs.getString("name"));
            }
        });
    }

    public int insertUser(int id, String name) {
        String q = "INSERT INTO test(id, name) VALUES (:id, :name)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("name", name);

        return jdbcTemplate.update(q, params);

    }

}
