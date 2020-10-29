package kr.co.jdbcboard.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import kr.co.jdbcboard.Dao.JdbcDAO;

public class BoardListCommand implements BCommand {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		JdbcDAO boarddao = new JdbcDAO();
		List boardlist = new ArrayList();

		int page = 1;
		int limit = 10;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		int listcount = boarddao.getListCount(); // �� ����Ʈ ���� �޾ƿ�.
		int startrow = (page-1)*10+1;
		int endrow = startrow+limit-1;
		boardlist = boarddao.getBoardList(startrow, endrow); // ����Ʈ�� �޾ƿ�.

		// �� ������ ��.
		int maxpage = (int) ((double) listcount / limit + 0.95); // 0.95�� ���ؼ� �ø� ó��.
		// ���� �������� ������ ���� ������ ��(1, 11, 21 ��...)
		int startpage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;
		// ���� �������� ������ ������ ������ ��.(10, 20, 30 ��...)
		int endpage = maxpage;

		if (endpage > startpage + 10 - 1)
			endpage = startpage + 10 - 1;

		model.addAttribute("page", page); // ���� ������ ��.
		model.addAttribute("maxpage", maxpage); // �ִ� ������ ��.
		model.addAttribute("startpage", startpage); // ���� �������� ǥ���� ù ������ ��.
		model.addAttribute("endpage", endpage); // ���� �������� ǥ���� �� ������ ��.
		model.addAttribute("listcount", listcount); // �� ��.
		model.addAttribute("boardlist", boardlist);
	}

}
