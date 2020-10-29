package kr.co.jdbcboard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import kr.co.jdbcboard.Bean.BoardBean;
import kr.co.jdbcboard.Dao.JdbcDAO;

public class BoardModify implements BCommand{

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		JdbcDAO jdbcDAO = new JdbcDAO();
		
		int num=Integer.parseInt(request.getParameter("num"));
		BoardBean boarddata = jdbcDAO.Detail(num);
		
		model.addAttribute("board", boarddata);
		
	}

}
