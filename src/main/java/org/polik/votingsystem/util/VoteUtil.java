package org.polik.votingsystem.util;

import lombok.experimental.UtilityClass;
import org.polik.votingsystem.model.Restaurant;
import org.polik.votingsystem.model.Vote;
import org.polik.votingsystem.to.VoteTo;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Polik on 4/11/2022
 */
@UtilityClass
public class VoteUtil {
    public static List<VoteTo> getTos(List<Vote> votes) {
        Map<Restaurant, Long> map = votes.stream()
                .collect(
                        Collectors.groupingBy(Vote::getRestaurant, Collectors.counting())
                );

        return map.keySet()
                .stream()
                .map(el -> createTo(el, Math.toIntExact(map.get(el))))
                .sorted(Comparator.comparingInt(VoteTo::getVotes).reversed())
                .toList();
    }

    public static VoteTo createTo(Restaurant restaurant, Integer votes) {
        return new VoteTo(restaurant.getId(), votes);
    }
}
