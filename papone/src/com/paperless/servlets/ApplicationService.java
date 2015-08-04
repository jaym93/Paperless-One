package com.paperless.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.paperless.beans.AppPage;

import com.paperless.process.AppPageProcess;


/**
 * Servlet implementation class ApplicationService
 */
@WebServlet("/ApplicationService")
public class ApplicationService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplicationService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Gson json = new Gson();

		// Set response body content. response body is returned as Ajax Response
		// Text
		
		try {
			String ci = request.getParameter("ci");
					
			AppPage app = new AppPage();
			AppPageProcess process = new AppPageProcess();
			app =process.getAppData(ci);
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
			response.setHeader("Access-Control-Max-Age", "3600");
			response.setHeader("Access-Control-Allow-Headers", "x-requested-with");

			response.setContentType("application/json");
			
			PrintWriter writer = response.getWriter();
			writer.write(json.toJson(app));
			writer.close();
		} catch (Exception e) {

			// Set response body content. response body is returned as Ajax
			// Response
			// Text
			response.setContentType("application/json");
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
			response.setHeader("Access-Control-Max-Age", "3600");
			response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
			PrintWriter writer = response.getWriter();
			e.printStackTrace();
			writer.write(json.toJson("No Data Returned. Error! Check Logs."));
			writer.close();
		}
		
		
	
		
		
	}

}
