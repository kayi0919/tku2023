package org.tku.web.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tku.database.entity.Announcement;
import org.tku.database.repository.AnnouncementRepository;

import java.util.List;

@Service
@Log4j2
public class AnnouncementService {

    @Autowired
    private AnnouncementRepository announcementRepository;

    public void saveAnnouncement(Announcement announcement) {
        announcementRepository.save(announcement);
    }


    // 取得所有公告
    public List<Announcement> getAllAnnouncements() {
        return announcementRepository.findAll();
    }

    // 根據 ID 刪除公告
    // 根據 ID 刪除公告
    public boolean deleteAnnouncementById(Integer id) {
        try {
            announcementRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("刪除失敗: " + e.getMessage());
            return false;
        }
    }
}
