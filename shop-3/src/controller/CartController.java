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
    //��ٱ��� ������ Session�� ������.
    @RequestMapping("cart/cartAdd")
    public ModelAndView add(Integer itemId, Integer quantity, HttpSession session){
		//selectedItem : itemId�� �ش��ϴ� Item ���� ����
    	Item selectedItem = shopService.getItemById(itemId);
		Cart cart =(Cart)session.getAttribute("CART_KEY");
		if(cart == null){//īƮ ������ ����.
			cart = shopService.getCart();
			session.setAttribute("CART_KEY", cart);
		}
		//cart : session�� ��ϵ� Cart ��ü
		cart.push(new ItemSet(selectedItem,quantity));
		ModelAndView mav = new ModelAndView("cart/cart");
		mav.addObject("message" , selectedItem.getItemName()+"��(��)"+quantity+"�� �߰��߽��ϴ� ");
		mav.addObject("cart",cart);
		
		return mav;
    }
    @RequestMapping("cart/cartDelete")
    public ModelAndView cartDelete(int index,HttpSession session){
    	ModelAndView mav = new ModelAndView("cart/cart");
    	Cart cart = (Cart)session.getAttribute("CART_KEY");
    	String name = cart.remove(index);
    	mav.addObject("message",name + "��(��)"+"���� �߽��ϴ� .");
    	mav.addObject("cart",cart);
    	return mav;
    	
    }
   

}
