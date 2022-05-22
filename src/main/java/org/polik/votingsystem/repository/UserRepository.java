package org.polik.votingsystem.repository;

import org.polik.votingsystem.model.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by Polik on 2/1/2022
 */
@Transactional(readOnly = true)
@CacheConfig(cacheNames = "users")
public interface UserRepository extends BaseRepository<User> {
//    @Cacheable
    Optional<User> getByEmail(String email);

    @Override
    @Cacheable
    List<User> findAll();

    @Override
    @Transactional
    @CacheEvict(allEntries = true)
    <S extends User> S save(S entity);

    @CacheEvict(allEntries = true)
    default void deleteExisted(int id) {
        BaseRepository.super.deleteExisted(id);
    }
}
