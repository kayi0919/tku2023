package org.tku.web.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tku.database.entity.Announcement;
import org.tku.database.entity.Products;
import org.tku.database.repository.AnnouncementRepository;
import org.tku.database.repository.ProductsRepository;

import java.util.List;

@Service
@Log4j2
public class ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    public void saveProducts(Products products) {
        productsRepository.save(products);
    }


    // 取得所有公告
    public List<Products> getAllProducts() {
        return productsRepository.findAll();
    }

    // 根據 ID 刪除公告
    // 根據 ID 刪除公告
    public boolean deleteProductsById(Long products_no) {
        try {
            productsRepository.deleteById(products_no);
            return true;
        } catch (Exception e) {
            log.error("刪除失敗: " + e.getMessage());
            return false;
        }
    }
}
