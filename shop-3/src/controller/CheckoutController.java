package controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import exception.CartEmptyException;
import exception.LoginRequiredException;
import logic.Cart;
import logic.ItemSet;
import logic.Sale;
import logic.SaleLine;
import logic.Shop;
import logic.User;

@Controller
public class CheckoutController {
	@Autowired
	private Shop shopService;

	@RequestMapping("checkout/checkout")
	public ModelAndView checkout(HttpSession session) {
		//���� ���°� �α��� ����???
		User loginUser = (User) session.getAttribute("USER_KEY");
		if (loginUser == null) {
			throw new LoginRequiredException("�α����� �ŷ��ϼ��� ");
		}
		Cart cart = (Cart) session.getAttribute("CART_KEY");
		if (cart == null || cart.isEmpty()) {
			throw new CartEmptyException("īƮ�� ����ֽ��ϴ� .");
		}
		ModelAndView mav = new ModelAndView();
		mav.addObject("loginUser", loginUser);
		List<ItemSet> itemList = cart.getItemList();
		mav.addObject("itemList",itemList);
		Integer totalAmount = shopService.calculateTotalAmount(itemList);
		mav.addObject("totalAmount",totalAmount);
		return mav;
	}
	@RequestMapping("checkout/end")
	public ModelAndView end(HttpSession session){
		User loginUser =(User)session.getAttribute("USER_KEY");
		if(loginUser == null){
			throw new LoginRequiredException("�α����� �ŷ� �ϼ��� ");
		}
		Cart cart = (Cart)session.getAttribute("CART_KEY");
		if(cart == null || cart.isEmpty()){
			throw new CartEmptyException("īƮ�� ����ֽ��ϴ� .");
		}
		/*
		 * checkEnd : 1.�ֹ� ��ȣ�� ü���Ѵ� .
		 *            2.sale ���̺��� ���ڵ带 �߰�  -> �ֹ� ����
		 *            3.saleLine ���̺� ���ڵ� �߰� ->�ֹ��� �ش��ϴ� ��ǰ����
		 *  clearAll : īƮ�� ����ش� .          
		 */
		Sale sale = shopService.checkEnd(loginUser,cart);
		//session�� īƮ ������ ����
		cart.clearAll(session);
		ModelAndView mav = new ModelAndView();
		mav.addObject("loginUser",loginUser);
		mav.addObject("sale",sale);
		List<SaleLine> itemList = sale.getSaleLine();
		Integer totalAmount = shopService.calculateTotalAmount2(itemList);
		mav.addObject("totalAmount",totalAmount);
		return mav;
		
	}
	
}
