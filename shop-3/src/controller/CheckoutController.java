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
		//현재 상태가 로그인 상태???
		User loginUser = (User) session.getAttribute("USER_KEY");
		if (loginUser == null) {
			throw new LoginRequiredException("로그인후 거래하세요 ");
		}
		Cart cart = (Cart) session.getAttribute("CART_KEY");
		if (cart == null || cart.isEmpty()) {
			throw new CartEmptyException("카트가 비어있습니다 .");
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
			throw new LoginRequiredException("로그인후 거래 하세요 ");
		}
		Cart cart = (Cart)session.getAttribute("CART_KEY");
		if(cart == null || cart.isEmpty()){
			throw new CartEmptyException("카트가 비어있습니다 .");
		}
		/*
		 * checkEnd : 1.주문 번호를 체번한다 .
		 *            2.sale 테이블의 레코드를 추가  -> 주문 정보
		 *            3.saleLine 테이블 레코드 추가 ->주문에 해당하는 상품정보
		 *  clearAll : 카트를 비워준다 .          
		 */
		Sale sale = shopService.checkEnd(loginUser,cart);
		//session의 카트 정보를 제거
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
