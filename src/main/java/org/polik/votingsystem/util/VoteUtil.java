package org.polik.votingsystem.util;

import lombok.experimental.UtilityClass;
import org.polik.votingsystem.model.Vote;
import org.polik.votingsystem.to.VoteTo;

import java.util.List;

/**
 * Created by Polik on 4/11/2022
 */
@UtilityClass
public class VoteUtil {
    public static List<VoteTo> getTos(List<Vote> votes) {
        return votes.stream()
                .map(VoteUtil::createTo)
                .toList();
    }

    public static VoteTo createTo(Vote vote) {
        return new VoteTo(vote.id(), vote.getRestaurant().getName(), vote.getRestaurant().id(), vote.getVoteDate());
    }
}
