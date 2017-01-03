package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import logic.Cart;
import logic.Item;
import logic.ItemSet;
import logic.Shop;

@Controller
public class CartController {
    @Autowired
	private Shop shopService;
    //장바구니 정보를 Session에 저장함.
    @RequestMapping("cart/cartAdd")
    public ModelAndView add(Integer itemId, Integer quantity, HttpSession session){
		//selectedItem : itemId에 해당하는 Item 정보 저장
    	Item selectedItem = shopService.getItemById(itemId);
		Cart cart =(Cart)session.getAttribute("CART_KEY");
		if(cart == null){//카트 정보가 없다.
			cart = shopService.getCart();
			session.setAttribute("CART_KEY", cart);
		}
		//cart : session에 등록된 Cart 객체
		cart.push(new ItemSet(selectedItem,quantity));
		ModelAndView mav = new ModelAndView("cart/cart");
		mav.addObject("message" , selectedItem.getItemName()+"을(를)"+quantity+"개 추가했습니다 ");
		mav.addObject("cart",cart);
		
		return mav;
    }
    @RequestMapping("cart/cartDelete")
    public ModelAndView cartDelete(int index,HttpSession session){
    	ModelAndView mav = new ModelAndView("cart/cart");
    	Cart cart = (Cart)session.getAttribute("CART_KEY");
    	String name = cart.remove(index);
    	mav.addObject("message",name + "을(를)"+"삭제 했습니다 .");
    	mav.addObject("cart",cart);
    	return mav;
    	
    }
   

}
