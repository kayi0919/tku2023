package org.tku.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tku.database.entity.Announcement;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {
    // 在需要時可以加入特定的查詢方法
}
