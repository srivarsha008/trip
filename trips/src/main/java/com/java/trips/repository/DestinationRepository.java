// OrderItemRepository.java
package com.java.trips.repository;

import com.java.trips.Model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Integer> {
    List<Destination> findByLoginId(int loginId);
}