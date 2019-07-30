package sia.tacocloud.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sia.tacocloud.dto.Order;

@Controller
@RequestMapping("/orders")
@Slf4j
public class OrderController {

  @GetMapping("/current")
  public String orderFrom(Model model) {
    model.addAttribute("order", new Order());
    return "orderForm";
  }

  @PostMapping
  public String processOrder(Order order) {
    log.info("Order submitted: {}", order);
    return "redirect:/";
  }
}
