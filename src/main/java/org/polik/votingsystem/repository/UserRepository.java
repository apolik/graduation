package org.polik.votingsystem.repository;

import org.polik.votingsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Polik on 2/1/2022
 */
@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer> {
    @Override
    @Transactional
    <S extends User> S save(S entity);

    User getByUsername(String name);
}
