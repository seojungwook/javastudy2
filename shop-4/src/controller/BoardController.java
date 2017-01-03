package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dao.BoardDao;
import exception.ErroPassException;
import logic.Board;
import logic.Member;
import logic.Shop;

@Controller
public class BoardController {
	@Autowired
	public Shop shopService;
	@Autowired
	public BoardDao boardDao;

	@RequestMapping("board/list")
	public ModelAndView list(Integer pageNum) {
		if (pageNum == null)
			pageNum = 1;
		ModelAndView mav = new ModelAndView();
		int limit = 10;// �������� �Խù���
		// ��ϵ� ��ü �Խñۼ�
		int listcount = shopService.boardCount();
		// �ش� �������� ��µ� �Խù� ������ ����
		List<Board> boardlist = null;

		boardlist = shopService.getBoardList(pageNum, limit);

		// ��ü �������� ���� ����
		int maxpage = (int) ((double) listcount / limit + 0.95);
		// ȭ�� �ϴ��� �������� ���� ������ ��ȣ
		int startpage = (((int) ((double) pageNum / 10 + 0.9)) - 1) * 10 + 1;
		// ȭ�� �ϴ��� �������� �������� ��ȣ
		int endpage = startpage + 9;
		if (endpage > maxpage) {
			endpage = maxpage;
		}

		SimpleDateFormat sdate = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdate.format(new Date());
		String msg = "list";
		mav.addObject("today", today);
		mav.addObject("pageNum", pageNum);
		mav.addObject("maxpage", maxpage);
		mav.addObject("startpage", startpage);
		mav.addObject("endpage", endpage);
		mav.addObject("listcount", listcount);
		mav.addObject("boardlist", boardlist);
		mav.addObject("msg", msg);
		return mav;
	}

	@RequestMapping("board/detail")
	public ModelAndView detail(Integer num, Integer pageNum) {
		ModelAndView mav = new ModelAndView("board/detail");
		Board board = shopService.selectOne(num, pageNum);
		mav.addObject("board", board);
		mav.addObject("pageNum", pageNum);
		return mav;
	}

	@RequestMapping("board/add")
	public ModelAndView add() {
		ModelAndView mav = new ModelAndView("board/add");
		mav.addObject("board", new Board());
		return mav;
	}

	// �Խù� ���
	// 1.�Ű����� ���� : �Ķ���Ͱ�, ��ȿ�� ���� ,���Ͼ��ε�
	// 2.shopService�� db�� �����ϴ� �޼���
	// 3.���� view�� board/list.html
	@RequestMapping("board/write")
	public ModelAndView write(@Valid Board board, BindingResult bindingResult, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("board/add");
		if (bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			return mav;
		}
		shopService.boardWrite(board, request);
		mav.setViewName("redirect:/item/list.html");
		return mav;
	}

	@RequestMapping("board/reply")
	public ModelAndView reply(Integer num, Integer pageNum) {
		ModelAndView mav = new ModelAndView("board/reply");
		Board board = shopService.selectOne(num, pageNum);
		mav.addObject("board", board);
		mav.addObject("pageNum", pageNum);
		return mav;
	}

	@RequestMapping("board/replyr")
	public ModelAndView replyr(Integer num, Integer pageNum, Board board) {
		ModelAndView mav = new ModelAndView("board/reply");
		shopService.rewrite(board);
		mav.addObject("num", num);
		mav.addObject("pageNum", pageNum);
		mav.setViewName("redirect:/board/list.html");
		return mav;
	}

	@RequestMapping("board/deletef")
	public ModelAndView deletef(Integer num, Integer pageNum) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("num", num);
		mav.addObject("pageNum", pageNum);
		return mav;

	}

	@RequestMapping("board/delete")
	public ModelAndView delete(Integer num, Integer pageNum, String inputpass) {
		ModelAndView mav = new ModelAndView("board/deletef");
		Board board = shopService.selectOne(num, pageNum);
		mav.addObject("pageNum", pageNum);
		if (board.getPass().equals(inputpass)) {
			shopService.delete(board);
			mav.setViewName("redirect:/board/list.html?pageNum" + pageNum);

		} else {

			mav.setViewName("board/deletef");
			throw new ErroPassException("��й�ȣ ����");
		}
		return mav;
	}

	/*
	 * controller�ۼ� 1.�Ķ���� (file1 : �������� ,file2:���� �̸� ),��ȿ�� ���� ,���� ���ε� (�Խù� ��Ͻ�
	 * �ڵ�) 2.db�ڵ� 3. redirect:list.html(�Խù���� ����)
	 * 
	 */
	@RequestMapping("board/modify")
	public ModelAndView modify(@Valid Board board, HttpServletRequest request, Integer num, Integer pageNum,
			BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView("board/modifyf");
		Board boarddb = shopService.selectOne(num, pageNum);

		if (bindingResult.hasErrors()) {
			mav.getModel().putAll(bindingResult.getModel());
			return mav;
		}
		if (boarddb.getPass().equals(board.getPass())) {
			shopService.update(board, request);
			mav.addObject("num", num);
			mav.addObject("pageNum", pageNum);
			mav.setViewName("redirect:/board/list.html?pageNum=" + request.getParameter("pageNum"));
			// ���ο� ÷�� ������ ������
			if (board.getFile1().isEmpty()) {
				board.setFileUrl(request.getParameter("file2"));
			} else {
				board.setFileUrl(board.getFile1().getOriginalFilename());
			}
			return mav;
		} else {
			mav.setViewName("board/modifyf");
			throw new ErroPassException("��й�ȣ ����");
		}

	}

	@RequestMapping("board/modifyf")
	public ModelAndView modifyf(Integer num, Integer pageNum) {
		ModelAndView mav = new ModelAndView("board/modifyf");
		Board board = shopService.selectOne(num, pageNum);
		mav.addObject("board", board);
		mav.addObject("num", num);
		mav.addObject("pageNum", pageNum);
		return mav;
	}

	@RequestMapping("board/search")
	public ModelAndView search(String searchtype, String searchContent,Integer pageNum) {
		if (pageNum == null)
			pageNum = 1;
		ModelAndView mav = new ModelAndView();
		List<Board> boardlist = new ArrayList<Board>();
		
		int listcount = boardDao.searchCount(searchtype, searchContent);
		int maxpage = (int) ((double) listcount / 10 + 0.95);
		// ȭ�� �ϴ��� �������� ���� ������ ��ȣ
		int startpage = (((int) ((double) pageNum / 10 + 0.9)) - 1) * 10 + 1;
		// ȭ�� �ϴ��� �������� �������� ��ȣ
		int endpage = startpage + 9;
		if (endpage > maxpage) {
			endpage = maxpage;
		}
		boardlist = shopService.search(searchtype, searchContent,pageNum);
		
		SimpleDateFormat sdate = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdate.format(new Date());
		String msg = "search";
		mav.addObject("today", today);
		mav.addObject("pageNum", pageNum);
		mav.addObject("maxpage", maxpage);
		mav.addObject("startpage", startpage);
		mav.addObject("endpage", endpage);
		mav.addObject("listcount", listcount);
		mav.addObject("boardlist", boardlist);
		mav.addObject("searchtype",searchtype);
		mav.addObject("searchContent",searchContent);
		mav.addObject("msg", msg);
		mav.setViewName("board/list");
		return mav;
	}

	@RequestMapping("board/filedown")
	public void filedown(String filename,HttpServletRequest request,HttpServletResponse response) {
		try {
			filename = new String(filename.getBytes("8859_1"), "euc-kr");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String filepath = request.getServletContext().getRealPath("/") 
				+ "/upload/" + filename;
		byte[] buf = new byte[4096];
		FileInputStream fis = null;
		OutputStream out = null;
		try {
			fis = new FileInputStream(filepath);
			out = response.getOutputStream();// �������� ����ϴ� ���� ��ü
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition",
					"attachment; filename=" + 
			new String(filename.getBytes("euc-kr"), "8859_1"));
			int readcnt = 0;
			while ((readcnt = fis.read(buf)) != -1) {
				out.write(buf, 0, readcnt);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null)
					fis.close();
				if (out != null) {
					out.flush();
					out.close();
				}
			} catch (IOException e) {
			}
		}
	}

}
