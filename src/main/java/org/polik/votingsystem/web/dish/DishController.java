package org.polik.votingsystem.web.dish;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.polik.votingsystem.error.ErrorInfo;
import org.polik.votingsystem.model.Dish;
import org.polik.votingsystem.to.DishTo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Polik on 3/29/2022
 */
@RestController
@RequestMapping(value = DishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Dish Controller")
@ApiResponse(responseCode = "401", description = "UNAUTHORIZED", content = @Content(schema = @Schema(implementation = ErrorInfo.class)))
public class DishController extends AbstractDishController {
    public static final String REST_URL = "/api/dishes";

    @GetMapping
    @Operation(description = "Returns All Dishes", responses = {
            @ApiResponse(responseCode = "200", description = "SUCCESS"),
    })
    public List<DishTo> getAll(@RequestParam(required = false) Integer restaurantId,
                               @RequestParam(required = false) LocalDate date) {
        return super.getAll(restaurantId, date);
    }

    @GetMapping("/{id}")
    @Operation(description = "Returns a Dish By Id", responses = {
            @ApiResponse(responseCode = "200", description = "SUCCESS"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content(schema = @Schema(implementation = ErrorInfo.class))),
    })
    public Dish get(@PathVariable int id) {
        return super.get(id);
    }
}
