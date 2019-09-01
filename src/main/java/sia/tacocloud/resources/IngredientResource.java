package sia.tacocloud.resources;

import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;
import sia.tacocloud.dao.Ingredient;
import sia.tacocloud.dao.Ingredient.Type;

@Getter
public class IngredientResource extends ResourceSupport {

  private String name;

  private Type type;

  public IngredientResource(Ingredient ingredient) {
    this.name = ingredient.getName();
    this.type = ingredient.getType();
  }
}
