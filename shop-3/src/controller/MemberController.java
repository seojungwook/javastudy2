package controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import logic.Shop;
import logic.User;

@Controller
public class MemberController {
	@Autowired
	Shop shopService;

	@RequestMapping("member/loginform")
	public String toLoginView() {
		return "member/login";
	}

	@ModelAttribute
	public User getUser() {
		return new User();
	}

	@RequestMapping("member/login")
	public ModelAndView onSubmit(@Valid User user, BindingResult bindingResult, HttpSession session) {
		ModelAndView mav = new ModelAndView("member/login");
		if (bindingResult.hasErrors()) {
			bindingResult.reject("error.login.user");
			mav.getModel().putAll(bindingResult.getModel());
			return mav;
		}
		try {
			User loginUser = shopService.getUserByIdAndPw(user.getUserId(), user.getPassword());
			session.setAttribute("USER_KEY", loginUser);
			if (session.getAttribute("CART_KEY") == null) {
				session.setAttribute("CART_KEY", shopService.getCart());
			}
			mav.setViewName("member/loginSuccess");
			mav.addObject("loginUser", loginUser);
			return mav;
		} catch (EmptyResultDataAccessException e) {
			bindingResult.reject("error.login.user");
			mav.getModel().putAll(bindingResult.getModel());
		}
		return mav;
	}

	@RequestMapping("member/registForm")
	public ModelAndView userEntry() {
		ModelAndView mav = new ModelAndView("member/userEntry");

		return mav;
	}
    //@Valid 맴버들을 객체에 넘기기전에 유효성 검사를 해라 ~
	@RequestMapping("member/userEntry")
	public ModelAndView userSubmit(@Valid User user, BindingResult bindingResult, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		if (bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			return mav;
		}
		try {
			shopService.createUser(user);
			mav.setViewName("member/login");
			mav.addObject("user", user);
		} catch (DataIntegrityViolationException e) {
			//이미 동일한 아이디가 존재 할때 나오는 예외~
			bindingResult.reject("error.duplicate.user");
			mav.getModel().putAll(bindingResult.getModel());
		}
		return mav;
	}
	//형변환 (String ->Date 타입으로 인식 + 유효성검사 )
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		//birthDay =>typeMismatch.birthDay 라는 오류코드 등록
		binder.registerCustomEditor(Date.class, "birthDay", 
				new CustomDateEditor(dateFormat, false));

	}
}
