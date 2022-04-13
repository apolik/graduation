package org.polik.votingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Created by Polik on 2/1/2022
 */
@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, Integer> {
}
