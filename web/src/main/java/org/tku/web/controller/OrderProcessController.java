package org.tku.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tku.database.entity.Cart;
import org.tku.database.entity.Orders;
import org.tku.database.repository.CartRepository;
import org.tku.database.repository.PendingRepository;

import java.util.List;

@Controller
public class OrderProcessController {

    private final PendingRepository pendingRepository;

    public OrderProcessController(PendingRepository pendingRepository){
        this.pendingRepository = pendingRepository;
    }

    @GetMapping("/web/orderprocess/pending")
    public String pending(@RequestParam(value = "orderId", required = false) Integer orderId,
                            @RequestParam(value = "username", required = false) String username,
                            @RequestParam(value = "product_name", required = false) String orders_product_name,
                            @RequestParam(value = "product_price", required = false) Integer orders_product_price,
                            @RequestParam(value = "amount", required = false) Integer orders_amount,
                            @RequestParam(value = "total", required = false) Integer orders_total,
                            @RequestParam(value = "operation", required = false) String operation,
                            Model model) throws JsonProcessingException {

        Orders order = new Orders();
        order.setOrderId(orderId);
        order.setUsername(username);
        order.setOrders_product_name(orders_product_name);
        order.setOrders_product_price(orders_product_price);
        order.setOrders_amount(orders_amount);
        order.setOrders_total(orders_total);
        order.setOperation(operation);
        List<Orders> orders = pendingRepository.findCartsBySelective(order);
        model.addAttribute("orders",orders);
        model.addAttribute("order",order);
        return "manager/orderprocess/pending";
    }

    @GetMapping("/web/orderprocess/processed")
    public String processed() throws JsonProcessingException {
        return "manager/orderprocess/processed";
    }

    @GetMapping("/web/orderprocess/returning")
    public String returning() throws JsonProcessingException {
        return "manager/orderprocess/returning";
    }

    @GetMapping("/web/orderprocess/soldout")
    public String soldout() throws JsonProcessingException {
        return "manager/orderprocess/soldout";
    }
}
