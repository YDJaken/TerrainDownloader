/*package terrainDownloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

*//**
 * Servlet implementation class DonloadServlet
 *//*
@WebServlet("/MoveServlet")
public class MoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String Filebase = "/data/Terrain/";
	private static final String Donloadbase = "/data/DownLoad/";
	private String[] array;

	*//**
	 * @see HttpServlet#HttpServlet()
	 *//*
	public MoveServlet() {
		super();
	}

	*//**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *//*
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		String[] input = request.getParameterValues("Array1[]");
		String currentX = request.getParameter("x");
		String currentZ = request.getParameter("z");
		String path = Filebase+currentZ+File.separator+currentX+File.separator;
		File c = new File(path);
		if(c.exists()) {
			c.mkdirs();
		}
		for (int i = 0; i < input.length; i++) {
				moveFile(path, input[i]);
		}
	}

	*//**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *//*
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public static void cutFile(File file1, File file2){
		FileOutputStream fileOutputStream = null;
		InputStream inputStream = null;
		byte[] bytes = new byte[1024];
		int temp = 0;
		try {
			inputStream = new FileInputStream(file1);
			fileOutputStream = new FileOutputStream(file2);
			while((temp = inputStream.read(bytes)) != -1){
				fileOutputStream.write(bytes, 0, temp);
				fileOutputStream.flush();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	
	private void moveFile(String path, String File) {
		File a = new File(Donloadbase + File);
		File b = new File(path + File);
		File c = new File(path);
		if (a.exists()) {
			if (!b.exists()) {
				try {
					c.mkdirs();
					b.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			cutFile(a,b);
			a.delete();
		}
	}

}
*/