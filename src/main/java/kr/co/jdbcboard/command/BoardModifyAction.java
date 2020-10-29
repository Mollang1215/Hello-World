package kr.co.jdbcboard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import kr.co.jdbcboard.Bean.BoardBean;
import kr.co.jdbcboard.Dao.JdbcDAO;

public class BoardModifyAction implements BCommand {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		JdbcDAO jdbcDAO = new JdbcDAO();
		BoardBean boarddata = new BoardBean();
		
		boarddata.setBoard_Num(Integer.parseInt(request.getParameter("BOARD_NUM")));
		boarddata.setBoard_Name(request.getParameter("BOARD_NAME"));
		boarddata.setBoard_Pass(request.getParameter("BOARD_PASS"));
		boarddata.setBoard_Subject(request.getParameter("BOARD_SUBJECT"));
		boarddata.setBoard_Content(request.getParameter("BOARD_CONTENT"));
		
		jdbcDAO.ModifyAction(boarddata);

	}

}
