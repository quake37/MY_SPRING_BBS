package com.bbs.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bbs.domain.BoardVO;
import com.bbs.domain.Criteria;
import com.bbs.domain.PageMaker;
import com.bbs.domain.SearchCriteria;
import com.bbs.service.BoardService;

@Controller
@RequestMapping("sboard")
public class SearchBoardController {

	private static final Logger logger = LoggerFactory.getLogger(SearchBoardController.class);
	
	@Autowired
	private BoardService service;
	
	@RequestMapping(value="list", method = RequestMethod.GET)
	public String listPage(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		logger.info(cri.toString());
//		model.addAttribute("list", service.listCriteria(cri));
		model.addAttribute("list", service.listSearchCriteria(cri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
//		pageMaker.setTotalCount(service.listCountCriteria(cri));
		pageMaker.setTotalCount(service.listSearchCount(cri));
		
		model.addAttribute("pageMaker", pageMaker);
		return "sboard/list";
	}
	
	@RequestMapping(value = "readPage", method = RequestMethod.GET)
	public String read(@RequestParam("bno") int bno, @ModelAttribute("cri") SearchCriteria cri
			, Model model) throws Exception {
		model.addAttribute(service.read(bno));
		service.setViewCnt(bno);
		return "sboard/readPage";
	}
	
	@RequestMapping(value="removePage", method = RequestMethod.POST)
	public String remove(@RequestParam Map map, @ModelAttribute("cri") SearchCriteria cri, RedirectAttributes rttr) throws Exception {
		service.remove(map);

		rttr.addFlashAttribute("page", cri.getPage());
		rttr.addFlashAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("searchType", cri.getSearchType());
		rttr.addFlashAttribute("keyword", cri.getKeyword());
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/sboard/list";
	}
	
	@RequestMapping(value="modifyPage", method = RequestMethod.GET)
	public String modifyPage(@RequestParam("bno") int bno, @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		model.addAttribute("boardVO", service.read(bno));
		return "sboard/modifyPage";
	}
	
	@RequestMapping(value="modifyPage", method = RequestMethod.POST)
	public String modifyePagePOST(@RequestParam Map map, @ModelAttribute("cri") SearchCriteria cri, RedirectAttributes rttr) throws Exception {
		service.modify(map);

		rttr.addFlashAttribute("page", cri.getPage());
		rttr.addFlashAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("searchType", cri.getSearchType());
		rttr.addFlashAttribute("keyword", cri.getKeyword());
		
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/sboard/list";
	}
	
	@RequestMapping(value="register", method = RequestMethod.GET)
	public String registerGET(BoardVO board, Model model) throws Exception {
		logger.info("register get .....");
		return "sboard/register";
	}

	@RequestMapping(value="register", method = RequestMethod.POST)
	public String registerPOST(BoardVO board, Model model, RedirectAttributes ra) throws Exception {
		logger.info("register post .....");
		logger.info(board.toString());
		
		service.regist(board);
		ra.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/sboard/list";
	}
	
}
