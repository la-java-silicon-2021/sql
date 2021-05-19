package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ItemController {
	
	@Autowired
	ItemListRepository itemListRepository;

	/**
	 * 全商品一覧画面表示
	 */
	@RequestMapping("/items")
	public ModelAndView items(
			@RequestParam(name = "sort", defaultValue = "") String sort,
			@RequestParam(name = "max_price", defaultValue = "") String maxPrice,
			@RequestParam(name = "min_price", defaultValue = "") String minPrice,
			@RequestParam(name = "name", defaultValue = "") String name,
			ModelAndView mv) {

		// MIN価格を数値型の変換
		Integer minPriceInt = null;
		if (!"".equals(minPrice)) {
			minPriceInt = Integer.parseInt(minPrice);
		}

		// MAX価格を数値型の変換
		Integer maxPriceIng = null;
		if (!"".equals(maxPrice)) {
			maxPriceIng = Integer.parseInt(maxPrice);
		}

		// 商品一覧を検索
		List<Item> itemList = itemListRepository.findItemList(name, minPriceInt, maxPriceIng, sort);
		
		// 商品一覧をモデルにセット
		mv.addObject("items", itemList);

		// 検索条件をモデルにセット
		// (検索条件の保持用)
		mv.addObject("maxPrice", maxPrice);
		mv.addObject("minPrice", minPrice);
		mv.addObject("name", name);

		// items.htmlを表示
		mv.setViewName("items");
		
		return mv;
	}
}
