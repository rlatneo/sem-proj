package semi.project.jsnr.board.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import semi.project.jsnr.board.model.exception.BoardException;
import semi.project.jsnr.board.model.service.BoardService;
import semi.project.jsnr.board.model.vo.Board;
import semi.project.jsnr.common.Pagination;
import semi.project.jsnr.common.model.vo.PageInfo;
import semi.project.jsnr.jibsa.model.vo.Jibsa;
import semi.project.jsnr.jibsa.model.vo.JibsaProfile;
import semi.project.jsnr.member.model.vo.Member;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService bService;
	
	@GetMapping("review_Main.bo")
	public String reviewBoardList(@RequestParam(value="page", required=false) Integer currentPage, Model model) {
		
		if(currentPage == null) {
			currentPage = 1;
		}
		
		int listCount = bService.getListCount(1);
		
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 6);
		
		ArrayList<Board> list = bService.reviewBoardList(pi, 1);
		
		
		if(list != null) {
			model.addAttribute("pi", pi);
			model.addAttribute("list", list);
			return "review_Main";
		} else {
			throw new BoardException("게시글 조회를 실패하였습니다.");
		}
	}
	
	@GetMapping("jibsa_List.bo")
	public String jibsaList(@RequestParam(value="page", required=false) Integer page,
							Model model) {
		int currentPage = 1;
		if(page != null) {
			currentPage = page;
		}
		
		int listCount = bService.getJibsaListCount();
		
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 6);
		
		ArrayList<JibsaProfile> pList = bService.selectJibsaProfileList(pi);
				
		if(pList != null) {
			model.addAttribute("pi", pi);
			model.addAttribute("pList", pList);
		}
		return "jibsa_List";
	}
	
	@GetMapping("jibsa_Detail.bo")
	public String selectProfile(@RequestParam(value="page", required=false) Integer page,
								@RequestParam("mId") int mId,
								HttpSession session,
								Model model) {
		Jibsa jibsa = bService.getJibsaInfo(mId);
		JibsaProfile jp = bService.getJibsaProfile(mId);
		
		if(jibsa != null) {
			model.addAttribute("page", page);
			model.addAttribute("jibsa", jibsa);
			model.addAttribute("jp", jp);
			return "jibsa_Detail";
		} else {
			throw new BoardException("게시글 조회를 실패하였습니다.");
		}
	}

	
	@RequestMapping("search.bo")
	public String searchListCount(@RequestParam(value="page", required=false) Integer currentPage, HttpServletRequest request, Model model) {
		
		if(currentPage == null) {
			currentPage = 1;
		}
		
		
		String condition = request.getParameter("condition");
		String value = request.getParameter("value");
		
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("condition", condition);
		map.put("value", value);
		int listCount = bService.getSearchListCount(map);
		
		
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 6);
		ArrayList<Board> list = bService.selectSearchList(map, pi);
		
		if(list != null) {
			model.addAttribute("list", list);
			model.addAttribute("pi", pi);
			model.addAttribute("condition", condition);
			model.addAttribute("value", value);
		
			return "review_Main";
		} else {
			throw new BoardException("리뷰 검색을 실패하였습니다.");
		}
	}
	
	@RequestMapping("review_Detail.bo")
	public ModelAndView reviewDetail(@RequestParam("page") int page, ModelAndView mv, HttpSession session, @RequestParam("mId") int mId, @RequestParam("writer") String writer) {
		
		
		Member m = (Member)session.getAttribute("loginUser");
		String login = null;
		if(m != null) {
			login = m.getMemberName();
		}
		boolean yn = false;
		if(!writer.equals(login)) {
			yn = true;
		}
		
		Board b = bService.reviewDetail(mId, yn);	
		ArrayList<Board> list = bService.reviewDetailReply(mId);
			
		
		if(b != null) {
			mv.addObject("b", b);
			mv.addObject("page", page);
			mv.addObject("list", list);

			mv.setViewName("review_Detail");
			return mv;
		} else {
			throw new BoardException("게시글 상세보기를 실패하였습니다.");
		}
		
	}
}
	
