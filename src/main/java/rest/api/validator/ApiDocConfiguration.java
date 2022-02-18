package rest.api.validator;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiDocConfiguration {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .servers(servers());
  }

  private List<Server> servers() {
    List<Server> servers = new ArrayList<>();

    Server local = new Server()
        .url("http://localhost:8180/")
        .description("Local environment");
    servers.add(local);
    return servers;
  }

  private List<Tag> tags() {
    List<Tag> tags = new ArrayList<>();
    tags.add(new Tag().name("Validation Service API"));
    return tags;
  }
}
