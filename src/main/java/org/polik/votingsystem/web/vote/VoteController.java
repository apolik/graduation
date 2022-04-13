package org.polik.votingsystem.web.vote;

import org.polik.votingsystem.model.Vote;
import org.polik.votingsystem.to.VoteTo;
import org.polik.votingsystem.web.AuthorizedUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Polik on 3/29/2022
 */
@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController extends AbstractVoteController {
    public static final String REST_URL = "/rest/voting";

    @Override
    @GetMapping
    public List<VoteTo> getAll() {
        return super.getAll();
    }

    @Override
    @PostMapping("/{id}")
    public Vote vote(@PathVariable int id,
                     @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        return super.vote(id, authorizedUser);
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void revote(@PathVariable int id,
                       @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        super.revote(id, authorizedUser);
    }
}
