package org.tku.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.tku.database.entity.Cart;
import org.tku.database.repository.CartRepository;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Controller
@Log4j2
public class CartController {

    private final CartRepository cartRepository;

    public CartController(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }

    @Autowired
    LocaleResolver localeResolver;

    @GetMapping("/web/cart")
    public String index(@RequestParam(value = "record_seq", required = false) Long record_seq,
                        @RequestParam(value = "product_name", required = false) String product_name,
                        @RequestParam(value = "product_price", required = false) Long product_price,
                        @RequestParam(value = "amount", required = false) Long amount,
                        @RequestParam(value = "total", required = false) Long total,
                        Model model){

        Cart cart = new Cart();
        cart.setRecord_seq(record_seq);
        cart.setProduct_name(product_name);
        cart.setProduct_price(product_price);
        cart.setAmount(amount);
        cart.setTotal(total);
        List<Cart> carts = cartRepository.findCartsBySelective(cart);
        model.addAttribute("carts",carts);
        model.addAttribute("cart",cart);
        return "user/cart/index";
    }

    @GetMapping(value = "/web/cart/{record_seq}")
    public String cartInfo(@PathVariable(value = "record_seq", required = false) Long record_seq, Model model) {
        if(record_seq == null) {
            Cart cart = new Cart();
            cart.setAction("create");
            model.addAttribute("cart", cart);
            return "user/cart/detail";
        }
        Cart cart = cartRepository.findById(record_seq).orElse(new Cart());
        if(cart.getRecord_seq() == null) {
            cart.setAction("create");
        }else {
            cart.setAction("update");
        }
        model.addAttribute("cart", cart);
        return "user/cart/detail";
    }

    @PostMapping(value = "/web/cart")
    public String modifyBook(Long record_seq,
                             String product_name,
                             Long product_price,
                             Long amount,
                             Long total,
                             String action,
                             Model model) {
        log.info(record_seq);
        if(!StringUtils.isBlank(action)){
            switch (action) {
                case "create":
                    Cart cart = new Cart();
                    cart.setRecord_seq(record_seq);
                    cart.setProduct_name(product_name);
                    cart.setProduct_price(product_price);
                    cart.setAmount(amount);
                    cart.setTotal(total);
                    cartRepository.save(cart);
                    break;
                case "update":
                    Optional<Cart> optionalCart = cartRepository.findById(record_seq);
                    if(optionalCart.isEmpty()) {
                        break;
                    }
                    cart = optionalCart.get();
                    cart.setRecord_seq(record_seq);
                    cart.setProduct_name(product_name);
                    cart.setProduct_price(product_price);
                    cart.setAmount(amount);
                    cart.setTotal(total);
                    cartRepository.save(cart);
                    break;
                case "delete":
                    if (record_seq != null) {
                        cartRepository.deleteById(record_seq);
                    }
            }
        }
        return "redirect:/web/cart";
    }



}
