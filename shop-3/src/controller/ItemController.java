package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import logic.Item;
import logic.ItemService;

@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;

	// http://localhost:8080/shop-3/item/list.html 요청시
	// 호출되는 메서드로 지정
	@RequestMapping("item/list")
	public ModelAndView list() {
		List<Item> itemList = itemService.getItemList();
		// mav.setViewName("item/list") : 생성자로 설정
		ModelAndView mav = new ModelAndView("item/list");
		mav.addObject("itemList", itemList);
		return mav;
	}

	@RequestMapping("item/detail")
	public ModelAndView detail(Integer itemId) {
		Item item = itemService.getItemById(itemId);
		ModelAndView mav = new ModelAndView();
		mav.addObject("item", item);
		// 뷰를 선택하기 않으면 url(item/detail)의 정보와 같은 이름의 jsp를 찾는다
		return mav;

	}

	@RequestMapping("item/search")
	public ModelAndView search(String itemName) {
		List<Item> item = itemService.searchList(itemName);
		ModelAndView mav = new ModelAndView("item/list");
		mav.addObject("itemName", itemName);
		mav.addObject("itemList", item);

		return mav;
	}

	@RequestMapping("item/create")
	public ModelAndView create() {
		ModelAndView mav = new ModelAndView("item/add");
		mav.addObject(new Item());//
		return mav;
	} // 이름없는 Itme 객체를 넘겨 주어서 add.jsp에 있는 modelAttribute이름으로 객체에 입력받은 값들을 저장해준다
		// .
		// @ModelAttribute
		// public Item getItem(){return new Item();}
		// @RequestMapping("item/create")
		// public String create(){
		// return "item/add";
		// }
        //@Vaild : 유효성 검사 를 하도록 하는 어노테이션 
	@RequestMapping("item/register")
	public ModelAndView register(@Valid Item item,BindingResult bindingResult,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("item/add");
		if (bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			return mav;
		}
		itemService.entryItem(item,request);
		//redirect왜 브라우져의 url이 변경될까?
		//
		mav.setViewName("redirect:/item/list.html");
		return mav;
	}
	@RequestMapping("item/edit")
	public ModelAndView edit(Integer itemId){
//		ModelAndView mav = new ModelAndView("item/edit");
//		Item item = itemService.getItemById(itemId);
//		mav.addObject("item",item);
//		return mav;
		return detail(itemId);
	}
	@RequestMapping("item/update")
	public ModelAndView update(@Valid Item item,BindingResult bindingResult,HttpServletRequest request){
		ModelAndView mav = new ModelAndView("item/update");
		if (bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			return mav;
		}
		itemService.entryUpdate(item, request);
		mav.setViewName("redirect:/item/list.html");
		return mav;
	}
	@RequestMapping("item/confirm")
	public ModelAndView delete(Integer itemId){
		ModelAndView mav = new ModelAndView("item/delete");
		Item item = itemService.getItemById(itemId);
		mav.addObject("item",item);
		return mav;
	}
	@RequestMapping("item/delete")
	public ModelAndView del(Integer itemId){
		ModelAndView mav = new ModelAndView("item/list");
		itemService.delete(itemId);
		mav.setViewName("redirect:/item/list.html");
		return mav;
		
	}
	
}
