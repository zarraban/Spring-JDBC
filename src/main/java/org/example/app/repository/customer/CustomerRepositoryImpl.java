package org.example.app.repository.customer;

import lombok.extern.slf4j.Slf4j;
import org.example.app.dto.CustomerDTOrequest;
import org.example.app.entity.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Slf4j
@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    JdbcTemplate jdbcTemplate;

    private final RowMapper<Customer> mapper = (resultSet, _)->{
        Customer customer = new Customer();
        customer.setId(resultSet.getLong("id"));
        customer.setName(resultSet.getString("name"));
        customer.setAddress(resultSet.getString("address"));
        customer.setPhone(resultSet.getString("phone"));
        return customer;
    };

    public CustomerRepositoryImpl(JdbcTemplate template){
        this.jdbcTemplate = template;
    }


    @Override
    public boolean create(CustomerDTOrequest entity) {
        String sql = "INSERT INTO customers(name, phone, address) VALUES(?,?,?)";
        return jdbcTemplate.update(sql,entity.name(), entity.phone(), entity.address()) > 0;

    }

    @Override
    public Optional<List<Customer>> read() {
        String sql = "SELECT * FROM customers";
        return Optional.of(jdbcTemplate.query(sql,mapper));

    }

    @Override
    public Optional<Customer> readById(Long id) {
        String sql = "SELECT * FROM customers WHERE id = ? LIMIT 1";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, mapper, id));
    }

    @Override
    public boolean updateById(Long id, CustomerDTOrequest entity) {
        String sql = " UPDATE customers SET name = ?, phone = ?, address = ? WHERE id = ?";
        return jdbcTemplate.update(sql,entity.name(), entity.phone(), entity.address(), id) > 0;
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM customers WHERE id = ?";
        return jdbcTemplate.update(sql,id)>0;
    }

    @Override
    public Optional<Customer> getLastEntity() {
        String sql = "SELECT * FROM customers LIMIT 1 ORDER BY DESC";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, mapper));
    }
}
