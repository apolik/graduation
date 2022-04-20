package org.polik.votingsystem.web.vote;

import org.polik.votingsystem.model.Vote;
import org.polik.votingsystem.to.VoteTo;
import org.polik.votingsystem.web.AuthorizedUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Polik on 4/11/2022
 */
@RestController
@RequestMapping(value = AdminVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminVoteController extends AbstractVoteController {
    public static final String REST_URL = "api/admin/voting";

    @Override
    @GetMapping
    public List<VoteTo> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("/history")
    public List<VoteTo> getAllByDate(@RequestParam LocalDate date) {
        return super.getAllByDate(date);
    }

    @PostMapping("/{id}")
    public Vote vote(@PathVariable int id,
                     @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        return super.vote(id, authorizedUser.getUser());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void revote(@PathVariable int id,
                       @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        super.revote(id, authorizedUser.getId());
    }
}
