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
 * @RequestMapping : ��û�� ������ ȣ��Ǵ� �޼����� ���� �������ش� !!!
 */
@RequestMapping
public ModelAndView detailItem(Integer itemId) {//�Ķ���Ϳ� �̸��̰��ƾ� ���۵ȴ� .
	Item item= shopService.getItemByItemId(itemId);
	ModelAndView mav =new ModelAndView();
	mav.setViewName("detail");
	mav.addObject("item",item);
	return mav;
}
  
}
