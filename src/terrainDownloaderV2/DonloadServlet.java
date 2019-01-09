package terrainDownloaderV2;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DonloadServlet
 */
@WebServlet("/DonloadServlet")
public class DonloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String Donloadbase = File.separator + "data" + File.separator + "DownLoad" + File.separator
			+ "Terrain" + File.separator + "normal" + File.separator;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DonloadServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String AT = request.getParameter("AT");
		String perfix = request.getParameter("perfix");
		String endfix = request.getParameter("endfix");
		String currentX = request.getParameter("x");
		String currentZ = request.getParameter("z");
		int startY = Integer.parseInt(request.getParameter("startY"));
		int endY = Integer.parseInt(request.getParameter("endY"));
		for (int currentY = startY; currentY <= endY; currentY++) {
			String token = perfix + currentY + endfix + AT;
			File b = new File(
					Donloadbase + currentZ + File.separator + currentX + File.separator + currentY + ".terrain");
			if (b.exists() && b.length() != 0) {
				continue;
			} else {
				File c = new File(Donloadbase + currentZ + File.separator + currentX);
				if (!c.exists())
					c.mkdirs();
				b.createNewFile();
			}
			File a = new File(
					Donloadbase + currentZ + File.separator + currentX + File.separator + currentY + "丨.terrain");
			if (!a.exists())
				a.createNewFile();
			Detect401 as = HttpRequestUtil.postDownTerrain(token, a);
			int count = 0;
			while (as != null && as.getCode() == 300) {
				as = HttpRequestUtil.postDownTerrain(token, a);
				count++;
				if (count > 20) {
					response.getWriter().write("401");
					response.getWriter().flush();
					response.getWriter().close();
					b.delete();
					a.delete();
					return;
				}
			}
			count = 0;
			while (as != null && as.getCode() == 503) {
				as = HttpRequestUtil.postDownTerrain(token, a);
				count++;
				if (count > 20) {
					response.getWriter().write("401");
					response.getWriter().flush();
					response.getWriter().close();
					b.delete();
					a.delete();
					return;
				}
			}
			if (as == null) {
				response.getWriter().write("500");
				response.getWriter().flush();
				response.getWriter().close();
				b.delete();
				a.delete();
				return;
			}
			if (as.getCode() == 500) {
				System.out.println("z:" + currentZ + "x:" + currentX + "y:" + currentY + "远端服务器报错500已经跳过");
				b.delete();
				a.delete();
				continue;
			}
			if (as.getCode() == 401) {
				response.getWriter().write("401");
				response.getWriter().flush();
				response.getWriter().close();
				b.delete();
				a.delete();
				return;
			}
			if (FileUtil.unGzipFile(a, b) == false) {
				response.getWriter().write("500");
				response.getWriter().flush();
				response.getWriter().close();
				b.delete();
				a.delete();
				return;
			}
			a.delete();
		}
		response.getWriter().write("200");
		response.getWriter().flush();
		response.getWriter().close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
