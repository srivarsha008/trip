package com.java.trips.service;

import com.java.trips.Model.Destination;
import com.java.trips.repository.DestinationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationService {
    private final DestinationRepository orderItemRepository;

    public DestinationService(DestinationRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public Destination createOrderItem(Destination destination) {
        return orderItemRepository.save(destination);
    }

    public List<Destination> getOrderItemsByLoginId(int loginId) {
        return orderItemRepository.findByLoginId(loginId);
    }
}