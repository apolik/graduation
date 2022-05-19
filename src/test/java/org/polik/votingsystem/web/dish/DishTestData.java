package org.polik.votingsystem.web.dish;

import lombok.experimental.UtilityClass;
import org.polik.votingsystem.MatcherFactory;
import org.polik.votingsystem.model.Dish;
import org.polik.votingsystem.to.DishTo;
import org.polik.votingsystem.util.DishUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static org.polik.votingsystem.web.restaurant.RestaurantTestData.*;

/**
 * Created by Polik on 4/24/2022
 */
@UtilityClass
public class DishTestData {
    public static final MatcherFactory.Matcher<DishTo> TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(DishTo.class);
    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "restaurant");

    public static final Dish macdonaldsDish1 = new Dish(1, "Блинчики", new BigDecimal("7443.00"), LocalDate.parse("2022-03-10"), macdonalds);
    public static final Dish macdonaldsDish2 = new Dish(12, "Блинчики", new BigDecimal("750.00"), LocalDate.parse("2022-03-11"), macdonalds);
    public static final Dish macdonaldsDish3 = new Dish(16, "Блинчики с лепешкой", new BigDecimal("750.00"), LocalDate.now(), macdonalds);


    public static final Dish fridaysDish1 = new Dish(4, "Ту капс оф пёрпл", new BigDecimal("1337.00"), LocalDate.parse("2022-03-10"), fridays);
    public static final Dish fridaysDish2 = new Dish(5, "Куриные крылышки", new BigDecimal("1000.00"), LocalDate.parse("2022-03-10"), fridays);
    public static final Dish fridaysDish3 = new Dish(9, "test123", new BigDecimal("2789.00"), LocalDate.parse("2022-03-11"), fridays);
    public static final Dish fridaysDish4 = new Dish(10, "Best dish", new BigDecimal("3785.00"), LocalDate.parse("2022-03-11"), fridays);
    public static final Dish fridaysDish5 = new Dish(14, "Шашлык", new BigDecimal("3785.00"), LocalDate.now(), fridays);


    public static final Dish krogerDish1 = new Dish(2, "Оладушки", new BigDecimal("4312.00"), LocalDate.parse("2022-03-10"), kroger);
    public static final Dish krogerDish2 = new Dish(7, "Оладушки с блинчиками", new BigDecimal("500.00"), LocalDate.parse("2022-03-11"), kroger);
    public static final Dish krogerDish3 = new Dish(8, "Блинчики с оладушками", new BigDecimal("300.00"), LocalDate.parse("2022-03-11"), kroger);
    public static final Dish krogerDish4 = new Dish(13, "Чашечка чая", new BigDecimal("1984.00"), LocalDate.parse("2022-03-11"), kroger);
    public static final Dish krogerDish5 = new Dish(17, "Чашечка чая", new BigDecimal("1984.00"), LocalDate.now(), kroger);


    public static final Dish wingstopDish1 = new Dish(3, "Чашечка чая", new BigDecimal("1432.00"), LocalDate.parse("2022-03-10"), wingstop);
    public static final Dish wingstopDish2 = new Dish(6, "Чашечка кофе", new BigDecimal("2500.00"), LocalDate.parse("2022-03-10"), wingstop);
    public static final Dish wingstopDish3 = new Dish(11, "Best dish1", new BigDecimal("250.00"), LocalDate.parse("2022-03-11"), wingstop);
    public static final Dish wingstopDish4 = new Dish(15, "Хлебушек с блинчиками", new BigDecimal("250.00"), LocalDate.now(), wingstop);
    public static final Dish wingstopDish5 = new Dish(18, "null", new BigDecimal("1324.00"), LocalDate.now(), wingstop);
    public static final Dish wingstopDish6 = new Dish(19, "undefined", new BigDecimal("101.00"), LocalDate.now(), wingstop);

    public static final List<Dish> ALL_DISHES = new ArrayList<>();
    public static final int NOT_FOUND_ID = 1017;

    static {
        ALL_DISHES.addAll(List.of(macdonaldsDish1, krogerDish1, wingstopDish1, fridaysDish1,
                                fridaysDish2, wingstopDish2, krogerDish2, krogerDish3, fridaysDish3,
                                fridaysDish4, wingstopDish3, macdonaldsDish2, krogerDish4, fridaysDish5, wingstopDish4,
                                macdonaldsDish3, krogerDish5, wingstopDish5, wingstopDish6));
    }

    public static List<DishTo> filteredByPredicate(Predicate<? super Dish> filter) {
        return DishUtil.getTos(
                ALL_DISHES.stream()
                        .filter(filter)
                        .toList()
        );
    }

    public static Dish getById(int id) {
        for (Dish dish : ALL_DISHES) {
            if (dish.id() == id) {
                return dish;
            }
        }

        return null;
    }

    public static Dish getNew() {
        return new Dish(null, "Новое блюдо", new BigDecimal("1934.00"), LocalDate.now(), wingstop);
    }

    public static Dish getUpdated() {
        return new Dish(2, "Обновленное блюдо", new BigDecimal("1337.00"), LocalDate.now(), wingstop);
    }
}
