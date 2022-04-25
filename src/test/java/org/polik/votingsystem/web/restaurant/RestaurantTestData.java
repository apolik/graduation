package org.polik.votingsystem.web.restaurant;

import lombok.experimental.UtilityClass;
import org.polik.votingsystem.MatcherFactory;
import org.polik.votingsystem.model.Restaurant;
import org.polik.votingsystem.to.RestaurantTo;
import org.polik.votingsystem.util.RestaurantUtil;

import java.util.List;

/**
 * Created by Polik on 4/14/2022
 */
@UtilityClass
public class RestaurantTestData {
    public static final MatcherFactory.Matcher<RestaurantTo> TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(RestaurantTo.class, "votes");
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "user");

    public static final Restaurant macdonalds = new Restaurant(1, "Macdonalds");
    public static final Restaurant fridays = new Restaurant(2, "Fridays");
    public static final Restaurant kroger = new Restaurant(3, "Kroger");
    public static final Restaurant wingstop = new Restaurant(4, "Wingstop");

    public static final List<RestaurantTo> RESTAURANTS_FOR_CURRENT_DATE = RestaurantUtil.getTos(List.of(kroger, fridays, wingstop, macdonalds));

    public static final int MACDONALDS_ID = 1;
    public static final int FRIDAYS_ID = 2;
    public static final int KROGER_ID = 2;
    public static final int WINGSTOP_ID = 4;
    public static final int NOT_FOUND_ID = 1017;

    public static Restaurant getUpdated() {
        return new Restaurant(1, "KFC");
    }

    public static Restaurant getNew() {
        return new Restaurant(null, "new restaurant");
    }
}
