package com.somercelik.apiforgeneralpurpose.dao;

import com.somercelik.apiforgeneralpurpose.model.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class PersonDataAccessService implements PersonDao {

    private final JdbcTemplate jdbcTemplate;

    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(UUID id, Person breakingBadCharacter) {
        final String sql = "" +
                "INSERT INTO person (id, name, real_name, date_of_birth)" +
                "VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                id,
                breakingBadCharacter.getName().toUpperCase(),
                breakingBadCharacter.getRealName().toUpperCase(),
                breakingBadCharacter.getDateOfBirth());
    }

    @Override
    public List<Person> selectAllPeople() {
        final String sql = "SELECT id, name, real_name, date_of_birth FROM person";
        return jdbcTemplate.query(sql, mapPersonFromDb());
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        final String sql = "" +
                "SELECT id, name, real_name, date_of_birth FROM person " +
                "WHERE id = ?";
        Person breakingBadCharacter = jdbcTemplate.queryForObject(sql, new Object[]{id}, mapPersonFromDb());
        return Optional.ofNullable(breakingBadCharacter);
    }

    @Override
    public int deletePersonById(UUID id) {
        final String sql = "" +
                "DELETE FROM person " +
                "WHERE id=?";
        return jdbcTemplate.update(sql, id);

    }

    @Override
    public int updatePersonById(UUID id, Person breakingBadCharacter) {
        final String sql = "" +
                "UPDATE person " +
                "SET name = ?, " +
                "real_name = ?," +
                "date_of_birth = ?" +
                "WHERE id = ?";
        return jdbcTemplate.update(sql,
                breakingBadCharacter.getName().toUpperCase(),
                breakingBadCharacter.getRealName().toUpperCase(),
                breakingBadCharacter.getDateOfBirth(),
                id);
    }

    private RowMapper<Person> mapPersonFromDb() {
        return ((resultSet, i) -> {
            UUID characterId = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            String realName = resultSet.getString("real_name");
            Date dateOfBirth = resultSet.getDate("date_of_birth");
            return new Person(characterId, name, realName, dateOfBirth);
        });
    }
}
