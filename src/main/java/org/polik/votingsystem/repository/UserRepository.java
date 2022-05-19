package org.polik.votingsystem.repository;

import org.polik.votingsystem.model.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by Polik on 2/1/2022
 */
@Transactional(readOnly = true)
public interface UserRepository extends BaseRepository<User> {
    // todo:
    @Cacheable(value = "users")
    Optional<User> getByEmail(String email);
}
