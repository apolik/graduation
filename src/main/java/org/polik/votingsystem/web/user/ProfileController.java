package org.polik.votingsystem.web.user;

import lombok.extern.slf4j.Slf4j;
import org.polik.votingsystem.model.User;
import org.polik.votingsystem.to.UserTo;
import org.polik.votingsystem.util.UserUtil;
import org.polik.votingsystem.web.AuthorizedUser;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static org.polik.votingsystem.util.validation.ValidationUtil.assureIdConsistent;
import static org.polik.votingsystem.util.validation.ValidationUtil.checkNew;

/**
 * Created by Polik on 4/15/2022
 */
@RestController
@RequestMapping(value = ProfileController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@CacheConfig(cacheNames = "users")
public class ProfileController extends AbstractUserController {
    public static final String REST_URL = "/api/profile";

    @GetMapping
    public User get(@AuthenticationPrincipal AuthorizedUser authUser) {
        return authUser.getUser();
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthorizedUser authUser) {
        super.delete(authUser.getId());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @CacheEvict(allEntries = true)
    public ResponseEntity<User> register(@Valid @RequestBody UserTo userTo) {
        log.info("register {}", userTo);
        checkNew(userTo);
        User created = prepareAndSave(UserUtil.createNewFromTo(userTo));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL).build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    @CacheEvict(allEntries = true)
    public void update(@RequestBody @Valid UserTo userTo, @AuthenticationPrincipal AuthorizedUser authUser) {
        assureIdConsistent(userTo, authUser.getId());
        User user = authUser.getUser();
        prepareAndSave(UserUtil.updateFromTo(user, userTo));
    }
}
