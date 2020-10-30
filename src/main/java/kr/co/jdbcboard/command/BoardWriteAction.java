package kr.co.jdbcboard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.jdbcboard.Bean.BoardBean;
import kr.co.jdbcboard.Dao.JdbcDAO;

public class BoardWriteAction implements BCommand {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		String realFolder = "";
		String saveFolder = "boardupload";

		int fileSize = 5 * 1024 * 1024;

		realFolder = request.getRealPath(saveFolder);

		boolean result = false;

		try {

			MultipartRequest multi = null;

			multi = new MultipartRequest(request, realFolder, fileSize, "euc-kr", new DefaultFileRenamePolicy());

			JdbcDAO jdbcDAO = new JdbcDAO();
			BoardBean boarddata = new BoardBean();

			boarddata.setBoard_Name(multi.getParameter("Board_Name"));
			boarddata.setBoard_Pass(multi.getParameter("BOARD_PASS"));
			boarddata.setBoard_Subject(multi.getParameter("BOARD_SUBJECT"));
			boarddata.setBoard_Content(multi.getParameter("BOARD_CONTENT"));
			boarddata.setBoard_File(multi.getParameter("BOARD_FILE"));
			boarddata.setBoard_File(multi.getFilesystemName((String) multi.getFileNames().nextElement()));
			jdbcDAO.Write(boarddata);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
