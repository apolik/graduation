package org.polik.votingsystem.web.restaurant;

import lombok.experimental.UtilityClass;
import org.polik.votingsystem.model.Restaurant;

/**
 * Created by Polik on 4/14/2022
 */
@UtilityClass
public class RestaurantTestData {
    public static final Restaurant macdonalds = new Restaurant(1, "Macdonalds");
    public static final Restaurant fridays = new Restaurant(2, "Fridays");
    public static final Restaurant kroger = new Restaurant(3, "Kroger");
    public static final Restaurant wingstop = new Restaurant(4, "Wingstop");

    public static final int MACDONALDS_ID = 1;
    public static final int FRIDAYS_ID = 2;
    public static final int KROGER_ID = 2;
    public static final int WINGSTOP_ID = 4;
}
