package org.polik.votingsystem.service;

import org.polik.votingsystem.model.Restaurant;
import org.polik.votingsystem.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Polik on 3/29/2022
 */
@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    public Restaurant getById(int id) {
        return restaurantRepository.getById(id);
    }
}
