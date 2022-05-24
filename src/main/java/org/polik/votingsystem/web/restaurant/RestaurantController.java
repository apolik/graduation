package org.polik.votingsystem.web.restaurant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.polik.votingsystem.error.ErrorInfo;
import org.polik.votingsystem.model.Restaurant;
import org.polik.votingsystem.to.RestaurantTo;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Polik on 3/12/2022
 */
@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Restaurant Controller")
@ApiResponse(responseCode = "401", description = "UNAUTHORIZED", content = @Content(schema = @Schema(implementation = ErrorInfo.class)))
public class RestaurantController extends AbstractRestaurantController {
    public static final String REST_URL = "/api/restaurants";

    @GetMapping
    @Operation(description = "Returns All Restaurants", responses = {
            @ApiResponse(responseCode = "200", description = "SUCCESS"),
    })
    public List<RestaurantTo> getAll() {
        return super.getAll();
    }

    @GetMapping("/{id}/with-dishes")
    @Operation(description = "Returns a Restaurant By Id With Dishes", responses = {
            @ApiResponse(responseCode = "200", description = "SUCCESS"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    public Restaurant getWithDishes(@PathVariable int id,
                                    @Parameter(description = "the date when the dishes are on the menu in ISO format (yyyy-mm-dd). today by default") @Nullable LocalDate date) {
        return super.getWithDishes(id, date);
    }

    @Override
    @GetMapping("/{id}")
    @Operation(description = "Returns a Restaurant By Id", responses = {
            @ApiResponse(responseCode = "200", description = "SUCCESS"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
    })
    public Restaurant get(@PathVariable int id) {
        return super.get(id);
    }
}
