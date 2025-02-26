package com.example.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository(value = "com.example.daos.DemoDao")
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class DemoDao {
    private static final String QUERY = "SELECT name FROM test WHERE id = 1";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public String getFirstName() {
        List<String> names = jdbcTemplate.queryForList(QUERY, new MapSqlParameterSource(), String.class);
        return names.get(0);
    }

    public int insertUser(int id, String name) {
        String q = "INSERT INTO test(id, name) VALUES (:id, :name)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("name", name);

        return jdbcTemplate.update(q, params);

    }

}
