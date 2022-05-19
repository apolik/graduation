package org.polik.votingsystem.web.vote;

import lombok.experimental.UtilityClass;
import org.polik.votingsystem.MatcherFactory;
import org.polik.votingsystem.model.Vote;
import org.polik.votingsystem.to.VoteTo;
import org.polik.votingsystem.web.restaurant.RestaurantTestData;
import org.polik.votingsystem.web.user.UserTestData;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

import static org.polik.votingsystem.util.VoteUtil.getTos;
import static org.polik.votingsystem.web.restaurant.RestaurantTestData.FRIDAYS_ID;
import static org.polik.votingsystem.web.restaurant.RestaurantTestData.KROGER_ID;

/**
 * Created by Polik on 4/23/2022
 */
@UtilityClass
public class VoteTestData {
    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vote.class, "user", "restaurant");
    public static final MatcherFactory.Matcher<VoteTo> TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(VoteTo.class);

    public static final Vote adminVote1 = new Vote(1, LocalDate.parse("2022-03-10"), UserTestData.admin, RestaurantTestData.macdonalds);
    public static final Vote adminVote2 = new Vote(5, LocalDate.parse("2022-03-11"), UserTestData.admin, RestaurantTestData.kroger);
    public static final Vote adminVote3 = new Vote(9, LocalDate.now(), UserTestData.admin, RestaurantTestData.fridays);

    public static final Vote userVote1 = new Vote(2, LocalDate.parse("2022-03-10"), UserTestData.user, RestaurantTestData.wingstop);
    public static final Vote userVote2 = new Vote(6, LocalDate.parse("2022-03-11"), UserTestData.user, RestaurantTestData.fridays);
    public static final Vote userVote3 = new Vote(10, LocalDate.now(), UserTestData.user, RestaurantTestData.kroger);

    public static final Vote superVote1 = new Vote(3, LocalDate.parse("2022-03-10"), UserTestData.superUser, RestaurantTestData.kroger);
    public static final Vote superVote2 = new Vote(7, LocalDate.parse("2022-03-11"), UserTestData.superUser, RestaurantTestData.fridays);
    public static final Vote superVote3 = new Vote(11, LocalDate.now(), UserTestData.superUser, RestaurantTestData.wingstop);

    public static final Vote guestVote1 = new Vote(4, LocalDate.parse("2022-03-10"), UserTestData.guest, RestaurantTestData.fridays);
    public static final Vote guestVote2 = new Vote(8, LocalDate.parse("2022-03-11"), UserTestData.guest, RestaurantTestData.wingstop);
    public static final Vote guestVote3 = new Vote(12, LocalDate.now(), UserTestData.guest, RestaurantTestData.kroger);

    public static final List<VoteTo> VOTES_FOR_CURRENT_DATE;
    public static final List<VoteTo> ALL_VOTES;

    public static final List<VoteTo> USER_VOTES;

    static {
        ALL_VOTES = getTos(List.of(adminVote1, userVote1, superVote1, guestVote1,
                                                adminVote2, userVote2, superVote2, guestVote2,
                                                adminVote3, userVote3, superVote3, guestVote3));
        USER_VOTES = getTos(List.of(userVote1, userVote2, userVote3));

        VOTES_FOR_CURRENT_DATE = filteredByPredicate(vote -> vote.getVoteDate().equals(LocalDate.now()));
    }

    public static VoteTo getNew() {
        return VoteTo.builder()
                .restaurantId(FRIDAYS_ID)
                .build();
    }

    public static VoteTo getUpdated() {
        return VoteTo.builder()
                .restaurantId(KROGER_ID)
                .build();
    }


    public static List<VoteTo> filteredByPredicate(Predicate<? super VoteTo> filter) {
        return ALL_VOTES.stream()
                .filter(filter)
                .toList();
    }
}
