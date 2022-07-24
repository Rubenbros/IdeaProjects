package rubenbros.emailService.controllers;

import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rubenbros.emailService.model.Email;

@Api(value = "/emails", tags = "emails")
@SwaggerDefinition(
        info = @Info(
                description = "API that manages email sending",
                version = "v1.0",
                title = "Emails API",
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
                @Tag(name = "emails",description = "manages emails")
        }
)
public abstract class EmailController {


    @Operation(summary = "Add an email to sending qeue",
            description = "Add an email to sending queue.",
            security = {
                    @SecurityRequirement(name = "bearer")
            },
            tags={  })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Email added"
            ),
            @ApiResponse(responseCode = "400",
                    description = "Missing Required Information or badly specified"),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error") })
    @PostMapping("/emails")
    public abstract ResponseEntity<String> addEmailToQueue(@RequestBody(required = false) Email emailBody);

    @Operation(summary = "Add an email to sending qeue",
            description = "Add an email to sending queue.",
            security = {
                    @SecurityRequirement(name = "bearer")
            },
            tags={  })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Emails sended"
            ),
            @ApiResponse(responseCode = "500",
                    description = "Internal Server Error") })
    @PostMapping("/emails/send")
    public abstract ResponseEntity<String> sendEmails();

}
