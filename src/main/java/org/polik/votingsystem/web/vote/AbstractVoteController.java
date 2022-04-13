package org.polik.votingsystem.web.vote;

import org.polik.votingsystem.model.Vote;
import org.polik.votingsystem.service.VoteService;
import org.polik.votingsystem.to.VoteTo;
import org.polik.votingsystem.web.AuthorizedUser;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Polik on 4/11/2022
 */
public abstract class AbstractVoteController {

    @Autowired
    private VoteService service;

    public List<VoteTo> getAll() {
        return service.getAll();
    }

    public List<VoteTo> getAll(LocalDate date) {
        return service.getAll(date);
    }

    public Vote vote(int id, AuthorizedUser authorizedUser) {
        return service.vote(id, authorizedUser.getUser());
    }

    public void revote(int id, AuthorizedUser authorizedUser) {
        service.revote(id, authorizedUser.getId());
    }
}
