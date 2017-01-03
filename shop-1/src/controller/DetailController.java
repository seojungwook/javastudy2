package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import logic.Item;
import logic.Shop;

@Controller
public class DetailController {
  private Shop shopService;

public void setShopService(Shop shopService) {
	this.shopService = shopService;
}
/*
 * @RequestMapping : 요청이 들어오면 호출되는 메서드라는 것을 지정해준다 !!!
 */
@RequestMapping
public ModelAndView detailItem(Integer itemId) {//파라미터와 이름이같아야 전송된다 .
	Item item= shopService.getItemByItemId(itemId);
	ModelAndView mav =new ModelAndView();
	mav.setViewName("detail");
	mav.addObject("item",item);
	return mav;
}
  
}
