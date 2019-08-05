package sia.tacocloud.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import sia.tacocloud.config.OrderProps;
import sia.tacocloud.dao.Order;
import sia.tacocloud.dao.User;
import sia.tacocloud.repositories.OrderRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
@ConfigurationProperties(prefix = "taco.orders")
@Slf4j
public class OrderController {

  private OrderRepository orderRepository;
  private OrderProps orderProps;

  @Autowired
  public OrderController(OrderRepository orderRepository, OrderProps orderProps) {
    this.orderRepository = orderRepository;
    this.orderProps = orderProps;
  }

  @GetMapping
  public String ordersForUser(@AuthenticationPrincipal User user, Model model) {
    Pageable pageable = PageRequest.of(0, orderProps.getPageSize());
    model.addAttribute("orders", orderRepository.findByUserOrderByCreatedAtDesc(user, pageable));

    return "orderList";
  }

  @GetMapping("/current")
  public String orderForm(Model model) {
    model.addAttribute("order", new Order());
    return "orderForm";
  }

  @PostMapping
  public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus,
                             @AuthenticationPrincipal User user) {
    if (errors.hasErrors()) {
      return "orderForm";
    }

    order.setUser(user);

    orderRepository.save(order);
    sessionStatus.setComplete();

    return "redirect:/";
  }
}