package sia.tacocloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import sia.tacocloud.dao.Taco;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("home");
    registry.addViewController("/login");
  }

  @Bean
  public ResourceProcessor<PagedResources<Resource<Taco>>> tacoProcessor(EntityLinks links) {
    return resource -> {
      resource.add(links.linkFor(Taco.class)
        .slash("recent")
        .withRel("recents")
      );

      return resource;
    };
  }
}
