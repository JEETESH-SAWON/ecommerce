package com.example.ecommerce.controller;

import com.example.ecommerce.dto.CartResponseDto;
import com.example.ecommerce.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @PostMapping("/cart/add")
    public String addToCart(@RequestParam Long productId,
                            HttpSession session,
                            RedirectAttributes redirectAttributes) {

        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            redirectAttributes.addFlashAttribute("loginError", "Please login to add to cart.");
            return "redirect:/login";
        }

        cartService.addToCart(userId, productId);
        redirectAttributes.addFlashAttribute("success", "Product added to cart!");
        return "redirect:/product";
    }


    @GetMapping("/cart")
    public String showCart(Model model,
                           HttpSession session,
                           RedirectAttributes redirectAttributes) {

        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            redirectAttributes.addFlashAttribute("loginError", "Please login");
            return "redirect:/login";
        }

        List<CartResponseDto> cartItems = cartService.getCartItems(userId);
        model.addAttribute("cartItems", cartItems);
        return "cart"; // JSP: cart.jsp
    }


    @PostMapping("/cart/remove")
    public String removeFromCart(@RequestParam Long productId,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes) {

        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            redirectAttributes.addFlashAttribute("loginError", "Please login first.");
            return "redirect:/login";
        }

        cartService.removeFromCart(userId, productId);
        redirectAttributes.addFlashAttribute("success", "Item removed from cart.");
        return "redirect:/cart";
    }


    @PostMapping("/cart/clear")
    public String clearCart(HttpSession session,
                            RedirectAttributes redirectAttributes) {

        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            redirectAttributes.addFlashAttribute("loginError", "Please login first.");
            return "redirect:/login";
        }

        cartService.clearCart(userId);
        redirectAttributes.addFlashAttribute("success", "Cart cleared.");
        return "redirect:/cart";
    }
}

