package com.example.EcommerceApp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FooterController {

  @GetMapping("/support")
  public String support() {
    return "support.html";
  }

  @GetMapping("/order-status")
  public String orderStatus() {
    return "order-status";
  }

  @GetMapping("/shipping")
  public String shipping() {
    return "shipping";
  }

  @GetMapping("/returns")
  public String returnsPage() {
    return "returns";
  }
}
