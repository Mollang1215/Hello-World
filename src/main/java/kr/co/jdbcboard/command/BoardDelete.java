package kr.co.jdbcboard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import kr.co.jdbcboard.Bean.BoardBean;
import kr.co.jdbcboard.Dao.JdbcDAO;

public class BoardDelete implements BCommand {

	@Override
	public void execute(Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		int num = Integer.parseInt(request.getParameter("num"));

		JdbcDAO jdbcDAO = new JdbcDAO();
		jdbcDAO.Delete(num);
	}

}
