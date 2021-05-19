package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * アイテム一覧取得用Repository
 */
@Repository
public interface ItemListRepository {
	
	/**
	 * 商品一覧検索
	 * 
	 * @param name
	 * @param minPrice
	 * @param maxPrice
	 * @param order
	 * @return
	 */
	List<Item> findItemList(String name, Integer minPrice, Integer maxPrice, String order);
}
