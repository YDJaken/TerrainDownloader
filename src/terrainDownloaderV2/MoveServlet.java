package terrainDownloaderV2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DonloadServlet
 */
@WebServlet("/MoveServlet")
public class MoveServlet extends HttpServlet {

	/**
	 * 
	 */
	private static String terrain_token = "{\"type\":\"TERRAIN\",\"url\":\"https://assets.cesium.com/1/\",\"accessToken\":\"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiJjNTkyY2VjNS1mYzVkLTQ3MWMtODNlMS04MGQ3ZDM3ZmZiMTYiLCJpZCI6MjU5LCJhc3NldHMiOnsiMSI6eyJ0eXBlIjoiVEVSUkFJTiIsImV4dGVuc2lvbnMiOlt0cnVlLHRydWUsdHJ1ZV0sInB1bGxBcGFydFRlcnJhaW4iOnRydWV9fSwic3JjIjoiYjBkYzNkMWItODgzNi00MzAxLThiZjktZjQ5ZGNkNjYxODI3IiwiaWF0IjoxNTQ4MjkyMTEwLCJleHAiOjE1NDgyOTU3MTB9.lkT8n1tzQm2GVsr5IC0TxmcmGKoROMJquK6I2QCOmSY\",\"attributions\":[{\"html\":\"<span><a href=\\\"https://cesium.com\\\" target=\\\"_blank\\\"><img alt=\\\"Cesium ion\\\" src=\\\"https://assets.cesium.com/ion-credit.png\\\"></a></span>\",\"text\":\"Cesium ion\",\"url\":\"https://cesium.com\",\"image\":\"https://assets.cesium.com/ion-credit.png\",\"collapsible\":false},{\"html\":\"<span>Data available from the U.S. Geological Survey, � CGIAR-CSI, Produced using Copernicus data and information funded by the European Union - EU-DEM layers, Data available from Land Information New Zealand, Data available from data.gov.uk, Data courtesy Geoscience Australia</span>\",\"text\":\"Data available from the U.S. Geological Survey, � CGIAR-CSI, Produced using Copernicus data and information funded by the European Union - EU-DEM layers, Data available from Land Information New Zealand, Data available from data.gov.uk, Data courtesy Geoscience Australia\",\"collapsible\":true}]}";
	private static final long serialVersionUID = 1L;
	private static final String URL = "https://api.cesium.com/v1/assets/1/endpoint?access_token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiJiMGRjM2QxYi04ODM2LTQzMDEtOGJmOS1mNDlkY2Q2NjE4MjciLCJpZCI6MjU5LCJpYXQiOjE1MjU5NjYyMDd9.xW9loNLo68KE3ReAHd-Lp73M8qJKhI9vA0wYL-qJX_I";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MoveServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = request.getParameter("Type");
		if (type.equals("1")) {
			terrain_token = null;
		}
		if (terrain_token == null) {
			byte[] as = HttpRequestUtil.getAT(URL);
			while (as == null && terrain_token == null) {
				as = HttpRequestUtil.getAT(URL);
			}
			if (as != null)
				terrain_token = new String(as, "UTF-8");
		}
		response.getWriter().write(terrain_token);
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
