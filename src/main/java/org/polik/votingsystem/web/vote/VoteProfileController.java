package org.polik.votingsystem.web.vote;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.polik.votingsystem.error.DuplicateException;
import org.polik.votingsystem.error.ErrorInfo;
import org.polik.votingsystem.error.ExpiredTimeException;
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
import static org.polik.votingsystem.web.GlobalExceptionHandler.EXCEPTION_DUPLICATE_VOTE;
import static org.polik.votingsystem.web.GlobalExceptionHandler.EXCEPTION_EXPIRED_TIME;

/**
 * Created by Polik on 3/29/2022
 */
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping(value = VoteProfileController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Vote Profile Controller")
@ApiResponse(responseCode = "401", description = "UNAUTHORIZED", content = @Content(schema = @Schema(implementation = ErrorInfo.class)))
public class VoteProfileController {
    public static final String REST_URL = "/api/profile/voting";

    private VoteRepository repository;

    private RestaurantRepository restaurantRepository;

    @GetMapping
    @Operation(description = "Returns All Votes For Current User", responses = {
            @ApiResponse(responseCode = "200", description = "SUCCESS"),
    })
    public List<VoteTo> getAll(@AuthenticationPrincipal AuthorizedUser authUser) { 
        log.info("getAll");
        int userId = authUser.id();
        return getTos(repository.findAll(userId));
    }

    @Transactional
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Creates a New Vote", responses = {
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "409", description = "CONFLICT", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
            @ApiResponse(responseCode = "422", description = "UNPROCESSABLE ENTITY", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
    })
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
            throw new DuplicateException(EXCEPTION_DUPLICATE_VOTE);
        }
    }

    @GetMapping("/{id}")
    @Operation(description = "Returns User's Vote By Id", responses = {
            @ApiResponse(responseCode = "200", description = "SUCCESS"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
    })
    public Vote get(@PathVariable int id,
                    @AuthenticationPrincipal AuthorizedUser authUser) {
        log.info("get {}", id);
        return safeGet(repository.findById(id, authUser.id()), id);
    }

    @PatchMapping
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Changes Today's User Vote", responses = {
            @ApiResponse(responseCode = "204", description = "NO CONTENT"),
            @ApiResponse(responseCode = "409", description = "CONFLICT", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
    })
    public void revote(@RequestParam int restaurantId,
                       @AuthenticationPrincipal AuthorizedUser authUser) {
        log.info("revote {}", restaurantId);
        if (DateTimeUtil.isBeforeDeadline(LocalTime.now())) {
            repository.revote(restaurantId, authUser.id());
        } else {
            throw new ExpiredTimeException(EXCEPTION_EXPIRED_TIME);
        }
    }

    private Restaurant getRestaurant(int restaurantId) {
        return safeGet(restaurantRepository.findById(restaurantId), restaurantId);
    }
}
