package org.tku.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.tku.database.entity.Products;
import org.tku.web.service.ProductsService;

import java.util.List;

@Controller
@Log4j2
public class OwnerController {

    @Autowired
    LocaleResolver localeResolver;

    @Autowired
    private ProductsService productsService;

    @GetMapping("/web/owner")
    public String ownermanager(Model model) {
        List<Products> products = productsService.getAllProducts();
        model.addAttribute("products",products);
        return "manager/owner/ownermanager";
    }

    @GetMapping("/web/owner/insert")
    public String insertproduct() {
        return "manager/owner/insertproduct";
    }

    @PostMapping("/web/owner/insert")
    public String insertProductData(@ModelAttribute Products products, Model model){
        productsService.saveProducts(products);
        log.debug(products);
        return "redirect:/web/owner";
    }

    @PostMapping("/web/owner/delete")
    public String deleteProducts(HttpServletRequest request, Model model) {
        Long productsNo = Long.parseLong(request.getParameter("products_no"));
        boolean success = productsService.deleteProductsById(productsNo);
        if (success) {
            return "redirect:/web/owner"; // 成功刪除後重新導向到公告列表頁面
        } else {
            model.addAttribute("deleteFailed", true); // 添加屬性以表示刪除失敗
            return "manager/owner/ownermanager"; // 返回原頁面
        }
    }
}
