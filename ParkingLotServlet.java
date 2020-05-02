package com.mvc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.bean.ParkingLotBean;
import com.mvc.dao.ParkingLotDAO;

/**
 * Servlet implementation class ParkingLotServlet
 */
@WebServlet("/ParkingLotServlet")
public class ParkingLotServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private ParkingLotDAO ParkingLotDAO1 = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParkingLotServlet() {
        super();
        	this.ParkingLotDAO1 = new ParkingLotDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		System.out.println("\nreader*********************action=" + action);
		if(action == null || "".equals(action)) {
			request.setAttribute("error", "您的操作有误！");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}else if("parkinglotQuery".equals(action)) {
			selectparkinglot(request,response);
		}else if("addparkinglot".equals(action)) {
			addparkinglot(request,response);
		}else if("deleteparkinglot".equals(action)) {
			deleteparkinglot(request,response);
		}else if("parkinglotmodifyquery".equals(action)) {
			ParkingLotModifyQuery(request,response);
		}else if("parkinglotmodify".equals(action)) {
			ParkingLotModify(request,response);
		}
	}
	/*******
	 * 查询停车场
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void selectparkinglot(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{
		String str = null;
		request.setAttribute("parkinglot", ParkingLotDAO.query(str));
		request.getRequestDispatcher("ParkingLot.jsp").forward(request, response);
	}
	/*********
	 * 添加停车场
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addparkinglot(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{
		ParkingLotBean pb = new ParkingLotBean();
		pb.setParkinglot_id(request.getParameter("parkinglot_id"));
		pb.setParkinglot_name(request.getParameter("parkinglot_name"));
		pb.setParkinglot_num(request.getParameter("parkinglot_num"));
		pb.setParkinglot_info(request.getParameter("parkinglot_info"));
		int a = ParkingLotDAO1.insert(pb);
		if(a == 0) {
			request.setAttribute("error", "停车场信息添加失败！");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}else if(a == 2) {
			request.setAttribute("error", "该车位信息已经添加！");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("ParkingLot_ok.jsp?para=1").forward(request, response);
		}
	}
	
	/******
	 * 删除停车场信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void deleteparkinglot(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{
		ParkingLotBean pb = new ParkingLotBean();
		pb.setParkinglot_id(request.getParameter("ID"));
		int ret = ParkingLotDAO1.delete(pb);
		if(ret == 0){
			request.setAttribute("error", "删除停车场信息失败！");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("ParkingLot_ok.jsp?para=3").forward(request, response);
		}
	}
	
	/****
	 * 由于修改时对的查询
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void ParkingLotModifyQuery(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException{
		ParkingLotBean pb = new ParkingLotBean();
		pb.setParkinglot_id(request.getParameter("id"));
		pb.setParkinglot_name(request.getParameter("name"));
		pb.setParkinglot_num(request.getParameter("num"));
	    pb.setParkinglot_info(request.getParameter("info"));
		//pb = (ParkingLotBean)request.getAttribute("modify");
		System.out.println(""+pb.getParkinglot_id());
		System.out.println(""+pb.getParkinglot_name());
		System.out.println(""+pb.getParkinglot_info());
		request.setAttribute("ParkinglotQueryif", ParkingLotDAO1.queryM(pb));
		request.getRequestDispatcher("UpdateParkingLot.jsp").forward(request, response);
	}
	
	/******
	 * 修改信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void ParkingLotModify(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException{
		ParkingLotBean pb = new ParkingLotBean();
		pb.setParkinglot_id(request.getParameter("parkinglot_id"));
		pb.setParkinglot_name(request.getParameter("parkinglot_name"));
		pb.setParkinglot_num(request.getParameter("parkinglot_num"));
		pb.setParkinglot_info(request.getParameter("parkinglot_info"));
		int ret = ParkingLotDAO1.update(pb);
		if(ret == 0) {
			request.setAttribute("error", "修改信息失败！");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("ParkingLot_ok.jsp?para=2").forward(request, response);
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
