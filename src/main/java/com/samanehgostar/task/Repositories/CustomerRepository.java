package com.samanehgostar.task.Repositories;

import com.samanehgostar.task.Model.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Transactional
    @Modifying
    @Query("update Customer c set c.birthdayDate=:#{#customer?.birthdayDate},c.fatherName=:#{#customer?.fatherName}," +
            "c.firstName=:#{#customer?.firstName},c.gender=:#{#customer?.gender},c.lastName=:#{#customer?.lastName}," +
            "c.nationalCode=:#{#customer?.nationalCode},c.registerDate=:#{#customer?.registerDate} where c.customerId=:#{#customer?.customerId}")
    void update(@RequestParam("customer") Customer customer);
    List<Customer> findByLastName(String name);

    List<Customer> findByFirstName(String name);
}
