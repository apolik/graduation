package org.polik.votingsystem.web.restaurant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.polik.votingsystem.error.ErrorInfo;
import org.polik.votingsystem.model.Restaurant;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Polik on 3/12/2022
 */
@RestController
@CacheConfig(cacheNames = "restaurants")
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Restaurant Controller")
@ApiResponse(responseCode = "401", description = "UNAUTHORIZED", content = @Content(schema = @Schema(implementation = ErrorInfo.class)))
public class RestaurantController extends AbstractRestaurantController {
    public static final String REST_URL = "/api/restaurants";

    @GetMapping
    @Cacheable
    @Operation(description = "Returns All Restaurants", responses = {
            @ApiResponse(responseCode = "200", description = "SUCCESS"),
    })
    public List<Restaurant> getAll(@RequestParam(required = false) LocalDate date) {
        return super.getAll(date);
    }

    @Override
    @GetMapping("/{id}")
    @Operation(description = "Returns Restaurant By Id", responses = {
            @ApiResponse(responseCode = "200", description = "SUCCESS"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
    })
    public Restaurant get(@PathVariable int id) {
        return super.get(id);
    }
}
