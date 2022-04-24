package org.polik.votingsystem.web.vote;

import lombok.experimental.UtilityClass;
import org.polik.votingsystem.MatcherFactory;
import org.polik.votingsystem.model.Vote;
import org.polik.votingsystem.to.VoteTo;
import org.polik.votingsystem.util.VoteUtil;
import org.polik.votingsystem.web.restaurant.RestaurantTestData;
import org.polik.votingsystem.web.user.UserTestData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by Polik on 4/23/2022
 */
@UtilityClass
public class VoteTestData {
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

    public static final List<VoteTo> VOTES_FOR_CURRENT_DATE = new ArrayList<>();
    public static final List<VoteTo> ALL_VOTES = new ArrayList<>();

    static {
        ALL_VOTES.addAll(VoteUtil.getTos(Stream.of(adminVote1, userVote1, superVote1, guestVote1,
                                                adminVote2, userVote2, superVote2, guestVote2,
                                                adminVote3, userVote3, superVote3, guestVote3).toList()));
        VOTES_FOR_CURRENT_DATE.addAll(filteredByPredicate(vote -> vote.getDate().equals(LocalDate.now())));
    }

    public static List<VoteTo> filteredByPredicate(Predicate<? super VoteTo> filter) {
        return ALL_VOTES.stream()
                .filter(filter)
                .toList();
    }
}
