package com.example.demo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * アイテム一覧取得用RepositoryImpl
 */
@Component
public class ItemListRepositoryImpl implements ItemListRepository {

	// SQL実行用のクラス
	@Autowired
	EntityManager em;

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<Item> findItemList(String name, Integer minPrice, Integer maxPrice, String order) {
		StringBuilder eSql = new StringBuilder();

		// SQL文作成
		eSql.append("SELECT ")
			.append(  "* ")
			.append("FROM ")
			.append(  "item ")
			.append("WHERE ")
			.append(  "1 = 1 ");
		
		// 名前の条件設定
		// 値の部分を":" + 名前でSQLを組み立てて、後で値を設定する
		if (name != null && !name.equals("")) {
			eSql.append("AND name like :name ");
		}
		
		// MIN価格条件設定
		if (minPrice != null) {
			eSql.append("AND price >= :minPrice ");
		}

		// MAX価格条件設定
		if (maxPrice != null) {
			eSql.append("AND price <= :maxPrice ");
		}

		// ソート条件設定
		if (!order.equals("")) {
			eSql.append("ORDER BY price ");
			
			if (order.equals("price_asc")) {
				eSql.append(  "asc ");
			}
			
			if (order.equals("price_desc")) {
				eSql.append(  "desc ");
			}
		}

		// Query生成
		// (SQL文と結果を詰めるクラスを指定する)
		Query query = em.createNativeQuery(eSql.toString(), Item.class);

		// 各検索条件値を設定
		if (name != null && !name.equals("")) {
			query.setParameter("name", "%" + name + "%");
		}
		if (minPrice != null) {
			query.setParameter("minPrice", minPrice);
		}
		if (maxPrice != null) {
			query.setParameter("maxPrice", maxPrice);
		}
		
		// ページング用
//		query.setFirstResult(1);
//		query.setMaxResults(3);

		// SQL実行
		return query.getResultList();
	}
}
