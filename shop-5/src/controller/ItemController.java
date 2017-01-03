package controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dao.ItemDao;
import logic.Item;

@Controller
public class ItemController {
	@Autowired
	private ItemDao dao;
	@RequestMapping("item/list")
	private ModelAndView list() {
		ModelAndView mav = new ModelAndView("list");
		List<Item> itemList = dao.getItemList();
		mav.addObject("itemList", itemList);
		return mav;
	}
	@RequestMapping("item/create")
	public ModelAndView create(){
		ModelAndView mav = new ModelAndView("add");
		mav.addObject(new Item());
		return mav;
	}
	@RequestMapping("item/register")
	public ModelAndView register(@Valid Item item, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			ModelAndView mav = new ModelAndView("add");
		mav.getModel().putAll(bindingResult.getModel());
		return mav;
		}
		dao.addItem(item);
		return list();
	}
}
