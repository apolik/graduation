package org.polik.votingsystem.web.vote;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.polik.votingsystem.error.ExpiredTimeException;
import org.polik.votingsystem.error.IllegalRequestDataException;
import org.polik.votingsystem.model.Restaurant;
import org.polik.votingsystem.model.Vote;
import org.polik.votingsystem.repository.RestaurantRepository;
import org.polik.votingsystem.repository.VoteRepository;
import org.polik.votingsystem.to.VoteTo;
import org.polik.votingsystem.util.DateTimeUtil;
import org.polik.votingsystem.web.AuthorizedUser;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalTime;
import java.util.List;

import static org.polik.votingsystem.util.Util.safeGet;
import static org.polik.votingsystem.util.VoteUtil.getTos;
import static org.polik.votingsystem.util.validation.ValidationUtil.checkModification;
import static org.polik.votingsystem.web.GlobalExceptionHandler.EXCEPTION_DUPLICATE_VOTE;
import static org.polik.votingsystem.web.GlobalExceptionHandler.EXCEPTION_EXPIRED_TIME;

/**
 * Created by Polik on 3/29/2022
 */
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {
    public static final String REST_URL = "/api/profile/voting";

    private VoteRepository repository;

    private RestaurantRepository restaurantRepository;

    @GetMapping
    public List<VoteTo> getAll(@AuthenticationPrincipal AuthorizedUser authUser) {
        log.info("getAll");
        int userId = authUser.id();
        return getTos(repository.findAll(userId));
    }

    @Transactional
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createWithLocation(@RequestBody @Valid VoteTo voteTo,
                                                   @AuthenticationPrincipal AuthorizedUser authUser) {
        try {
            log.info("create {}", voteTo);
            Vote created = repository.save(new Vote(
                    authUser.getUser(),
                    getRestaurant(voteTo.getRestaurantId())
            ));

            URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path(REST_URL + "/{id}")
                    .buildAndExpand(created.getId()).toUri();

            return ResponseEntity.created(uriOfNewResource).body(created);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalRequestDataException(EXCEPTION_DUPLICATE_VOTE);
        }
    }

    @PatchMapping
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void revote(@RequestParam int restaurantId,
                       @AuthenticationPrincipal AuthorizedUser authUser) {
        log.info("revote {}", restaurantId);
        if (DateTimeUtil.isBeforeDeadline(LocalTime.now())) {
            // fixme: checkModification message
            checkModification(repository.revote(restaurantId, authUser.id()), restaurantId);
        } else {
            throw new ExpiredTimeException(EXCEPTION_EXPIRED_TIME);
        }
    }

    @GetMapping("/{id}")
    public Vote get(@PathVariable int id,
                    @AuthenticationPrincipal AuthorizedUser authUser) {
        log.info("get {}", id);
        return safeGet(repository.findById(id, authUser.id()), id);
    }

    private Restaurant getRestaurant(int restaurantId) {
        return safeGet(restaurantRepository.findById(restaurantId), restaurantId);
    }
}
