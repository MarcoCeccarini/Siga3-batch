package it.finanze.siga.rest;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("api")
@OpenAPIDefinition(
        info = @Info(
                title = "Jakarta REST API",
                version = "1.0.0",
                description = "OpenAPI definition for REST endpoints in org.eclipse.jakarta.rest.",
                contact = @Contact(name = "API Support"),
                license = @License(name = "EPL-2.0")),
            servers = {
                @Server(url = "/Siga3-batch/api", description = "Local development server")
            }
           )
public class App extends Application {
    // Needed to enable Jakarta REST and specify path.
}
