package sk.ness.interview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import io.swagger.annotations.Api;
import sk.ness.interview.config.DatabaseConfig;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Main application runner that starts up the application with embedded tomcat.
 *
 * @author michal.kmetka
 *
 */
@SpringBootApplication
@EnableSwagger2
@EnableTransactionManagement
@ComponentScan(basePackages = "sk.ness.interview")
@Import(DatabaseConfig.class)
public class ApplicationRunner {

  public static void main(final String[] args) throws Exception {
    SpringApplication.run(ApplicationRunner.class, args);
  }

  @Bean
  public Docket swaggerSpringMvcPlugin() {
    return new Docket(DocumentationType.SWAGGER_2).groupName("blog-api").select().apis(RequestHandlerSelectors.withClassAnnotation(Api.class)).build();
  }

}
