package org.tku.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.tku.database.entity.Announcement;
import org.tku.database.entity.Cart;
import org.tku.web.service.AnnouncementService;
import org.tku.web.service.CartService;

import java.util.*;

@Controller
@Log4j2
public class HomeController {

    @Autowired
    LocaleResolver localeResolver;

    @Autowired
    private CartService cartService;

    @Autowired
    private AnnouncementService announcementService;


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

        return "statics"; // 回傳視圖名稱（HTML檔案名稱）
    }

    @GetMapping("/web/about")
    public String aboutUs() {
        return "about"; // Assuming "about.html" is the name of the HTML file for the About Us page
    }

    @GetMapping("/web/orderlist")
    public String orderList(Model model) {
        List<Announcement> announcements = announcementService.getAllAnnouncements(); // 從服務層獲取所有公告資料

        model.addAttribute("announcements", announcements); // 將公告資料傳遞到模板中

        return "orderlistdata/orderlist"; // Assuming "about.html" is the name of the HTML file for the About Us page
    }

    @PostMapping("/web/orderlist/delete")
    public String deleteAnnouncement(HttpServletRequest request, Model model) {
        Integer id = Integer.parseInt(request.getParameter("announcement_id"));
        boolean success = announcementService.deleteAnnouncementById(id);
        if (success) {
            return "redirect:/web/orderlist"; // 成功刪除後重新導向到公告列表頁面
        } else {
            model.addAttribute("deleteFailed", true); // 添加屬性以表示刪除失敗
            return "orderlistdata/orderlist"; // 返回原頁面
        }
    }




    @GetMapping("/web/announcement/createlist")
    public String createList(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // 取得登入的使用者名稱
        log.debug(username);
        model.addAttribute("username", username); // 將使用者名稱傳遞到創建公告的表單
        return "orderlistdata/createlist";
    }


    @PostMapping("/web/announcement/createlist")
    public String createListData(@ModelAttribute Announcement announcement, Model model) {
        announcementService.saveAnnouncement(announcement);
        log.debug(announcement);
        return "redirect:/web/orderlist";
    }


}
