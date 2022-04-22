package org.polik.votingsystem.web.vote;

import org.polik.votingsystem.model.Vote;
import org.polik.votingsystem.to.VoteTo;
import org.polik.votingsystem.web.AuthorizedUser;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Polik on 3/29/2022
 */
@RestController
@CacheConfig(cacheNames = "votes")
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController extends AbstractVoteController {
    public static final String REST_URL = "/api/voting";

    @Override
    @GetMapping
    @Cacheable
    public List<VoteTo> getAll() {
        return super.getAll();
    }

    @PostMapping("/{id}")
    @CacheEvict(allEntries = true)
    public Vote vote(@PathVariable int id,
                     @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        return super.vote(id, authorizedUser.getUser());
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CacheEvict(allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void revote(@PathVariable int id,
                       @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        super.revote(id, authorizedUser.getId());
    }
}
