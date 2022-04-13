package org.polik.votingsystem.repository;

import org.polik.votingsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by Polik on 2/1/2022
 */
@Transactional(readOnly = true)
public interface UserRepository extends BaseRepository<User> {
    @Override
    @Transactional
    <S extends User> S save(S entity);

    User getByUsername(String name);

    Optional<User> getByEmail(String email);
}
