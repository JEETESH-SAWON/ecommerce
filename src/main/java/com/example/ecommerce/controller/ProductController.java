package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ProductRequestDto;
import com.example.ecommerce.dto.ProductResponseDto;
import com.example.ecommerce.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProductController {
    private final ProductService productSer;

    ProductController(ProductService productSer) {
        this.productSer = productSer;
    }

    @GetMapping("/product")
    public String showProduct(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("userId") == null) {
            redirectAttributes.addFlashAttribute("loginError", "Not valid Session");
            return "redirect:/login";
        }
        productSer.validate((Long) session.getAttribute("userId"));
        List<ProductResponseDto> dto = productSer.showProduct();
        model.addAttribute("cartItemNo", productSer.checkCartItemNo((Long) session.getAttribute("userId")));
        model.addAttribute("data", dto);
        return "product";
    }

    @PostMapping("/delete/product")
    public String deleteProduct(Model model, HttpSession session, Long productId, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("userId") == null) {
            model.addAttribute("loginError", "Not Valid User");
            return "redirect:/login";
        }
        productSer.validate((Long) session.getAttribute("userId"));
        redirectAttributes.addFlashAttribute("success", "Item Deleted Successfully");
        return productSer.deleteProduct(productId);
    }

    @GetMapping("/product/createProduct")
    public String showProduct(Model model, HttpSession session) {
        if (session.getAttribute("userId") == null) {
            model.addAttribute("loginError", "Not Valid User");
            return "redirect:/login";
        }
        productSer.validate((Long) session.getAttribute("userId"));
        return "/createProduct";
    }

    @PostMapping("/products/save")
    public String createProduct(Model model, HttpSession session, ProductRequestDto dto, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("userId") == null) {
            model.addAttribute("loginError", "Not Valid User");
            return "redirect:/login";
        }
        productSer.validate((Long) session.getAttribute("userId"));
        productSer.createProduct(dto);
        redirectAttributes.addFlashAttribute("success", "New Product Added");
        return "redirect:/product";
    }

}
