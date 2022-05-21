package org.polik.votingsystem.web.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.polik.votingsystem.error.ErrorInfo;
import org.polik.votingsystem.model.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.polik.votingsystem.util.validation.ValidationUtil.assureIdConsistent;
import static org.polik.votingsystem.util.validation.ValidationUtil.checkNew;

/**
 * Created by Polik on 4/17/2022
 */
@Slf4j
@RestController
@CacheConfig(cacheNames = "users")
@RequestMapping(value = AdminUserController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value = {
        @ApiResponse(responseCode = "403", description = "FORBIDDEN", content = @Content(schema = @Schema(implementation = ErrorInfo.class))) ,
        @ApiResponse(responseCode = "401", description = "UNAUTHORIZED", content = @Content(schema = @Schema(implementation = ErrorInfo.class)))
})
public class AdminUserController extends AbstractUserController {
    public static final String REST_URL = "/api/admin/users";

    @Override
    @GetMapping("/{id}")
    @Operation(description = "Returns a User By Id", responses = {
            @ApiResponse(responseCode = "200", description = "SUCCESS"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
    })
    public ResponseEntity<User> get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Deletes a User By Id", responses = {
            @ApiResponse(responseCode = "204", description = "NO CONTENT"),
            @ApiResponse(responseCode = "422", description = "UNPROCESSABLE ENTITY", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
    })
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @GetMapping
    @Operation(description = "Returns All Users", responses = {
            @ApiResponse(responseCode = "200", description = "SUCCESS"),
    })
    public List<User> getAll() {
        log.info("getAll");
        return repository.findAll(Sort.by(Sort.Direction.ASC, "name", "email"));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @CacheEvict(allEntries = true)
    @Operation(description = "Creates a New User", responses = {
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "422", description = "UNPROCESSABLE ENTITY", content = @Content(schema = @Schema(implementation = ErrorInfo.class)))
    })
    public ResponseEntity<User> createWithLocation(@Valid @RequestBody User user) {
        log.info("create {}", user);
        checkNew(user);
        User created = prepareAndSave(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(allEntries = true)
    @Operation(description = "Updates a User", responses = {
            @ApiResponse(responseCode = "204", description = "NO CONTENT"),
            @ApiResponse(responseCode = "422", description = "UNPROCESSABLE ENTITY", content = @Content(schema = @Schema(implementation = ErrorInfo.class)))
    })
    public void update(@Valid @RequestBody User user, @PathVariable int id) {
        log.info("update {} with id={}", user, id);
        assureIdConsistent(user, id);
        prepareAndSave(user);
    }

    @GetMapping("/by-email")
    @Operation(description = "Returns a User By Email", responses = {
            @ApiResponse(responseCode = "200", description = "SUCCESS"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
    })
    public ResponseEntity<User> getByEmail(@RequestParam String email) {
        log.info("getByEmail {}", email);
        return ResponseEntity.of(repository.getByEmail(email));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    @CacheEvict(allEntries = true)
    @Operation(description = "Enables/Disables a User", responses = {
            @ApiResponse(responseCode = "204", description = "NO CONTENT"),
            @ApiResponse(responseCode = "422", description = "UNPROCESSABLE ENTITY", content = @Content(schema = @Schema(implementation = ErrorInfo.class)))
    })
    public void enable(@PathVariable int id, @RequestParam boolean enabled) {
        log.info(enabled ? "enable {}" : "disable {}", id);
        User user = repository.getById(id);
        user.setEnabled(enabled);
    }
}
