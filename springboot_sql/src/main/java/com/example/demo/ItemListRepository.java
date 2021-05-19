package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * アイテム一覧取得用Repository
 */
@Repository
public interface ItemListRepository {
	List<Item> findItemList(String name, Integer minPrice, Integer maxPrice, String order);
}
