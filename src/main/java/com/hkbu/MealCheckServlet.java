package com.hkbu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hkbu.util.AES;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class MealCheck
 */
@WebServlet(name = "MealCheckServlet", urlPatterns = { "/MealCheckServlet" })
public class MealCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MealCheckServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		// 设置后端传给前端响应数据的字符编码
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		
		String code = request.getParameter("code");
		if (code == null) {
			return;
		}
		JSONObject json = new JSONObject();
		try {
			String decrypt = AES.Decrypt(code, "4BE57B0B4BE57B0B");
			boolean start = decrypt.startsWith("Bob_code");
			if (start) {
				String substring = decrypt.substring(8);
				if ("00000001".equals(substring)||"00000010".equals(substring)||"99999999".equals(substring)) {
					json.put("success", false);
					response.getWriter().write(json.toString());
					return;
				}
				@SuppressWarnings("unused")
				int number = Integer.parseInt(substring);
				json.put("success", true);
				json.put("flag", "CTF_GAME");
				response.getWriter().write(json.toString());
				return;
			}
		} catch (Exception e) {
		}
		json.put("success", false);
		response.getWriter().write(json.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
