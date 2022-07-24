package rubenbros.subscriptionService.controllers;

import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rubenbros.subscriptionService.model.Subscription;

@Api(value = "/subscriptions", tags = "subscriptions")
@SwaggerDefinition(
        info = @Info(
                description = "API that manages newsletter subscriptions",
                version = "v1.0",
                title = "Subscriptions API",
                contact = @Contact(
                        name = "Ruben Jarne Cabañero",
                        email = "ruben.jarne.cabanero@gmail.com",
                        url = "https://github.com/rubenbros"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        consumes = {"application/json"},
        produces = {"application/json"},
        schemes = {SwaggerDefinition.Scheme.HTTP,SwaggerDefinition.Scheme.HTTPS},
        tags = {
                @Tag(name = "subscriptions",description = "manages subscriptions")
        }
)
public interface SubscriptionController {

    @Operation(summary = "Get all subscriptions",
            description = "Get details of all subscriptions",
            security = {
                @SecurityRequirement(name = "bearer")
            },
            tags={  })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(
                                            implementation = Subscription.class)))),
            @ApiResponse(responseCode = "401",
                    description = "Unauthorized"),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error") })
    @RequestMapping(value = "/subscriptions",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<String> getAllSubscriptions();

    @Operation(summary = "Create new subscription",
            description = "Create a new subscription.",
            security = {
                @SecurityRequirement(name = "bearer")
            },
            tags={  })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Subscription Created"
                    ),
            @ApiResponse(responseCode = "400",
                    description = "Missing Required Information os badly specified"),
            @ApiResponse(responseCode = "409",
                    description = "Email Already Taken"),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error") })
    @RequestMapping(value = "/subscription",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<String> createSubscription(@RequestBody  Subscription body);

    @Operation(summary = "Get a subscription",
            description = "Get details of the subscription specified",
            security = {
                    @SecurityRequirement(name = "bearer")
            },
            tags={  })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "OK",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(
                                            implementation = Subscription.class)))),
            @ApiResponse(responseCode = "401",
                    description = "Unauthorized"),
            @ApiResponse(responseCode = "404",
                    description = "Subscription not found"),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error") })
    @RequestMapping(value = "/subscription",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<String> getSubscription(@RequestParam String email);

    @Operation(summary = "Get a subscription",
            description = "Get details of the subscription specified",
            security = {
                    @SecurityRequirement(name = "bearer")
            },
            tags={  })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "OK"),
            @ApiResponse(responseCode = "401",
                    description = "Unauthorized"),
            @ApiResponse(responseCode = "404",
                    description = "Subscription not found"),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error") })
    @RequestMapping(value = "/subscription",
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<String> cancelSubscription(@RequestParam String email);
}
