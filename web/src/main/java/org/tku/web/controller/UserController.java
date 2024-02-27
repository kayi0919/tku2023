package org.tku.web.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.tku.database.entity.Cart;
import org.tku.web.service.CartService;

import java.util.*;

@Controller
@Log4j2
public class UserController {

    @Autowired
    LocaleResolver localeResolver;

    @Autowired
    private CartService cartService;

    @GetMapping("/web/home")
    public String home(@RequestParam(required = false) String locale,
                       HttpServletRequest request, HttpServletResponse response){
        if(StringUtils.isNotBlank(locale)){
            localeResolver.setLocale(request, response, new Locale(locale));
        }
        return "user/home";
    }

    @GetMapping("/web/about")
    public String aboutUs() {
        return "user/about"; // Assuming "about.html" is the name of the HTML file for the About Us page
    }

    @GetMapping("/web/statics")
    public String getStatics(Model model) {
        List<Cart> purchaseRecords = cartService.getAllPurchaseRecords();

        Map<String, Long> productPricesMap = new HashMap<>();
        Map<String, Long> productAmountMap = new HashMap<>();
        Map<String, Long> productTotalMap = new HashMap<>();

        // 整理相同名稱的商品價格、數量和總計
        for (Cart record : purchaseRecords) {
            String productName = record.getProduct_name();

            // 將相同商品名稱的價格、數量和總計進行累加
            productPricesMap.merge(productName, record.getProduct_price(), Long::sum);
            productAmountMap.merge(productName, record.getAmount(), Long::sum);
            productTotalMap.merge(productName, record.getTotal(), Long::sum);
        }

        // 提取整理後的資料作為圖表所需資料
        List<String> productNames = new ArrayList<>(productPricesMap.keySet());
        List<Long> productPrices = new ArrayList<>(productPricesMap.values());
        List<Long> productAmount = new ArrayList<>(productAmountMap.values());
        List<Long> productTotal = new ArrayList<>(productTotalMap.values());

        // 將資料加入Model中以供HTML頁面使用
        model.addAttribute("productNames", productNames);
        model.addAttribute("productPrices", productPrices);
        model.addAttribute("productAmount", productAmount);
        model.addAttribute("productTotal", productTotal);
        model.addAttribute("purchaseRecords", purchaseRecords);

        return "user/statics"; // 回傳視圖名稱（HTML檔案名稱）
    }

    @GetMapping("/web/shop")
    public String shop() {


        return "user/shop"; // Assuming "about.html" is the name of the HTML file for the About Us page
    }
}
