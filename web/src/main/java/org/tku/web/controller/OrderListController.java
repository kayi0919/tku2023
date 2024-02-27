package org.tku.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.tku.database.entity.Announcement;
import org.tku.web.service.AnnouncementService;

import java.util.List;

@Controller
@Log4j2
public class OrderListController {

    @Autowired
    LocaleResolver localeResolver;

    @Autowired
    private AnnouncementService announcementService;


    @GetMapping("/web/orderlist")
    public String orderList(Model model) {
        List<Announcement> announcements = announcementService.getAllAnnouncements(); // 從服務層獲取所有公告資料

        model.addAttribute("announcements", announcements); // 將公告資料傳遞到模板中

        return "manager/orderlistdata/orderlist"; // Assuming "about.html" is the name of the HTML file for the About Us page
    }

    @PostMapping("/web/orderlist/delete")
    public String deleteAnnouncement(HttpServletRequest request, Model model) {
        Integer id = Integer.parseInt(request.getParameter("announcement_id"));
        boolean success = announcementService.deleteAnnouncementById(id);
        if (success) {
            return "redirect:/web/orderlist"; // 成功刪除後重新導向到公告列表頁面
        } else {
            model.addAttribute("deleteFailed", true); // 添加屬性以表示刪除失敗
            return "manager/orderlistdata/orderlist"; // 返回原頁面
        }
    }


    @GetMapping("/web/announcement/createlist")
    public String createList(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // 取得登入的使用者名稱
        log.debug(username);
        model.addAttribute("username", username); // 將使用者名稱傳遞到創建公告的表單
        return "manager/orderlistdata/createlist";
    }


    @PostMapping("/web/announcement/createlist")
    public String createListData(@ModelAttribute Announcement announcement, Model model) {
        announcementService.saveAnnouncement(announcement);
        log.debug(announcement);
        return "redirect:/web/orderlist";
    }



}
