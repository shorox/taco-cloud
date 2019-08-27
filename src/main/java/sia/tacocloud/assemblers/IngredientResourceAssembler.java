package sia.tacocloud.assemblers;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import sia.tacocloud.controllers.IngredientController;
import sia.tacocloud.dao.Ingredient;
import sia.tacocloud.resources.IngredientResource;

public class IngredientResourceAssembler extends ResourceAssemblerSupport<Ingredient, IngredientResource> {

  public IngredientResourceAssembler() {
    super(IngredientController.class, IngredientResource.class);
  }

  @Override
  public IngredientResource toResource(Ingredient ingredient) {
    return createResourceWithId(ingredient.getId(), ingredient);
  }

  @Override
  protected IngredientResource instantiateResource(Ingredient ingredient) {
    return new IngredientResource(ingredient);
  }
}
