package com.example.ecommerce.controller;

import com.example.ecommerce.model.OrderEntity;
import com.example.ecommerce.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/order")
public class PlaceOrder {

    private final OrderService orderService;

    public PlaceOrder(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place")
    public String placeOrder(RedirectAttributes redirectAttributes) {
        orderService.placeOrder();
        redirectAttributes.addFlashAttribute("success", "Order placed successfully!");
        return "redirect:/order/history"; // Redirect back to cart (now empty)
    }

    @GetMapping("/history")
    public String viewOrderHistory(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        List<OrderEntity> orders = orderService.getOrdersByUser(userId);
        model.addAttribute("orders", orders);
        return "order-history";
    }

}

