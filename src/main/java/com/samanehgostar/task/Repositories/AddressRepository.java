package com.samanehgostar.task.Repositories;

import com.samanehgostar.task.Model.Address;
import com.samanehgostar.task.Model.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Transactional
    @Modifying
    @Query("update Address a set a.country=:#{#address?.country},a.city=:#{#address?.city}," +
            "a.street=:#{#address?.street},a.buildingNum=:#{#address?.buildingNum} " +
            "where a.id=:#{#address?.id}")
    void update(@RequestParam("address") Address address);

    List<Address> findByCustomer(Customer customer);

    List<Address> findByCity(String name);

    List<Address> findByCountry(String name);
}
