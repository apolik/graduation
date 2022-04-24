package org.polik.votingsystem.web.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.polik.votingsystem.error.IllegalRequestDataException;
import org.polik.votingsystem.model.Restaurant;
import org.polik.votingsystem.repository.RestaurantRepository;
import org.polik.votingsystem.to.RestaurantTo;
import org.polik.votingsystem.util.RestaurantUtil;
import org.polik.votingsystem.util.validation.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.polik.votingsystem.util.RestaurantUtil.getTos;

/**
 * Created by Polik on 4/5/2022
 */
@Slf4j
public abstract class AbstractRestaurantController {
    @Autowired
    private RestaurantRepository repository;

    public List<RestaurantTo> getAll() {
        log.info("getAll");
        return getTos(repository.findAll()); //fixme
    }

    public RestaurantTo get(int id) {
        log.info("getById {}", id);
        return RestaurantUtil.createTo(repository.findById(id).orElseThrow(
                () -> new IllegalRequestDataException("No such restaurant with id: " + id)
        ));
    }

    public Restaurant create(Restaurant restaurant) {
        log.info("create {}", restaurant);
        return repository.save(restaurant);
    }

    public void update(Restaurant restaurant, int id) {
        log.info("update {} {}", restaurant, id);
        ValidationUtil.assureIdConsistent(restaurant, id);

        repository.save(restaurant);
    }

    public void delete(int id) {
        log.info("deleteById {}", id);
        repository.deleteExisted(id);
    }
}
