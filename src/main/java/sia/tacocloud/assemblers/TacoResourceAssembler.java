package sia.tacocloud.assemblers;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import sia.tacocloud.controllers.DesignTacoController;
import sia.tacocloud.dao.Taco;
import sia.tacocloud.resources.TacoResource;

public class TacoResourceAssembler extends ResourceAssemblerSupport<Taco, TacoResource> {

  public TacoResourceAssembler() {
    super(DesignTacoController.class, TacoResource.class);
  }

  @Override
  protected TacoResource instantiateResource(Taco taco) {
    return new TacoResource(taco);
  }

  @Override
  public TacoResource toResource(Taco taco) {
    return createResourceWithId(taco.getId(), taco);
  }
}
