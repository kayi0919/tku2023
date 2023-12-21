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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@Log4j2
public class HomeController {

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
        return "home";
    }

    @GetMapping("/web/statics")
    public String getStatics(Model model) {
        // 假設這個方法能夠從資料庫中取得所有購買紀錄
        List<Cart> purchaseRecords = cartService.getAllPurchaseRecords();

        // 創建兩個新的 ArrayList 以存放圖表的 x 軸和 y 軸資料
        List<String> productNames = new ArrayList<>();
        List<Long> productPrices = new ArrayList<>();

        // 將資料轉換成圖表所需的格式
        for (Cart record : purchaseRecords) {
            productNames.add(record.getProduct_name());
            productPrices.add(record.getProduct_price());
        }

        // 將資料加入Model中以供HTML頁面使用
        model.addAttribute("productNames", productNames);
        model.addAttribute("productPrices", productPrices);

        return "statics"; // 回傳視圖名稱（HTML檔案名稱）
    }
}
