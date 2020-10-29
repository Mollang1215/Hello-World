package kr.co.jdbcboard.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.jdbcboard.Bean.BoardBean;
import kr.co.jdbcboard.command.BCommand;
import kr.co.jdbcboard.command.BoardDelete;
import kr.co.jdbcboard.command.BoardDetailAction;
import kr.co.jdbcboard.command.BoardListCommand;
import kr.co.jdbcboard.command.BoardModify;
import kr.co.jdbcboard.command.BoardModifyAction;
import kr.co.jdbcboard.command.BoardReplyView;
import kr.co.jdbcboard.command.BoardWriteAction;
import kr.co.jdbcboard.util.Constant;

@Controller
public class JdbcController {
	BCommand command = null;

	private JdbcTemplate template;

	@Autowired // 자동 호출
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
		Constant.template = this.template;
	}

	@RequestMapping("/BoardList")
	public String board_list(HttpServletRequest request, Model model) {
		System.out.println("list()");
		model.addAttribute("request", request);
		command = new BoardListCommand();
		command.execute(model);

		return "qna_board_list";
	}
	
	@RequestMapping("BoardWrite")
	public String board_Write(BoardBean board)
	{
		System.out.println("TEST : "+board.getBoard_Num());
		System.out.println("BoardWrite()");
		
		return "qna_board_write";
	}
	
	@RequestMapping("BoardAddAction")
	public String board_WriteAction(HttpServletRequest request, Model model)
	{
		System.out.println("BoardAddAction()");
		model.addAttribute("request", request);
		
		command = new BoardWriteAction();
		command.execute(model);
		
		return "redirect:BoardList";
	}
	
	@RequestMapping("BoardDetailAction")
	public String BoardDetailAction(HttpServletRequest request, Model model)
	{
		System.out.println("BoardDetailAction()");
		model.addAttribute("request", request);
		
		command = new BoardDetailAction();
		command.execute(model);
		
		return "qna_board_view";
	}
	
	@RequestMapping("BoardModify")
	public String BoardModify(HttpServletRequest request, Model model)
	{
		System.out.println("BoardModify()");
		model.addAttribute("request", request);
		
		command = new BoardModify();
		command.execute(model);
		
		return "qna_board_modify";
		
	}
	
	@RequestMapping("BoardModifyAction")
	public String BoardModifyAction(HttpServletRequest request, Model model)
	{
		System.out.println("BoardModifyAction");
		model.addAttribute("request", request);
		
		command = new BoardModifyAction();
		command.execute(model);
		
		
		return "redirect:BoardDetailAction";
	}
	
	@RequestMapping("BoardDelete")
	public String BoardDelete(HttpServletRequest request, Model model)
	{
		System.out.println("BoardDelete");
		System.out.println(request.getParameter("num"));
		model.addAttribute("num", request.getParameter("num"));
		
		return "qna_board_delete";
	}
	
	
	@RequestMapping("BoardDeleteAction")
	public String BoardDeleteAction(HttpServletRequest request, Model model)
	{
		System.out.println("BoardDeleteAction");
		model.addAttribute("request", request);
		
		command = new BoardDelete();
		command.execute(model);
		
		return "redirect:BoardList";
	}
	
	@RequestMapping("BoardReplyAction")
	public String BoardReplyAction(HttpServletRequest request, Model model)
	{
		System.out.println("BoardReplyAction");
		model.addAttribute("request", request);
		command = new BoardDetailAction();
		command.execute(model);
		
		return "qna_board_reply";
	}
	
	@RequestMapping("BoardReplyView")
	public String BoardReplyView(HttpServletRequest request, Model model)
	{
		System.out.println("BoardReplyView");
		model.addAttribute("request", request);
		
		command = new BoardReplyView();
		command.execute(model);
		
		return "redirect:BoardList";
	}

}
