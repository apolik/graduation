package org.polik.votingsystem.rest.vote;

import org.polik.votingsystem.model.Vote;
import org.polik.votingsystem.security.AuthorizedUser;
import org.polik.votingsystem.security.AuthorizedUserFactory;
import org.polik.votingsystem.service.VoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Polik on 3/29/2022
 */
@RestController
@RequestMapping(value = {"/rest/restaurants/voting", "/rest/admin/restaurants/voting"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {
    private final VoteService service;

    public VoteController(VoteService service) {
        this.service = service;
    }

    @PostMapping({"{id}", "{id}/"})
    public Vote vote(@PathVariable int id, @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        return service.vote(id, AuthorizedUserFactory.getUser(authorizedUser));
    }

    @PutMapping({"{id}", "{id}/"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void revote(@PathVariable int id, @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        service.revote(id, authorizedUser.getId());
    }
}
