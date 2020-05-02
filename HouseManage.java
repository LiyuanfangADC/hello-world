package com.mvc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.bean.BuildingBean;
import com.mvc.bean.HouseBean;
import com.mvc.dao.BuildingDAO;
import com.mvc.dao.HouseDAO;

/**
 * Servlet implementation class building
 */
@WebServlet("/HouseServlet")
public class HouseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HouseDAO HouseDAO1 = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HouseServlet() {
        super();
        
    		this.HouseDAO1 = new HouseDAO();
    	
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
			String action = request.getParameter("action");
			System.out.println("\nhouse*********************action=" + action);
			if (action == null || "".equals(action)) {
				request.setAttribute("error", "您的操作有误！");
				request.getRequestDispatcher("error.jsp")
						.forward(request, response);
			} else if ("houseQuery".equals(action)) {
				selecthouse(request, response);
			} else if ("houseAdd".equals(action)) {
				houseAdd(request, response);
			} else if ("houseModifyQuery".equals(action)) {
				houseModifyQuery(request, response);
			} else if ("houseModify".equals(action)) {
				houseModify(request, response);
			} else if ("houseDel".equals(action)) {
				houseDel(request, response);
			} else if ("readerDetail".equals(action)) {
				//readerDetail(request, response);
			}
		}
	/*********************** 查询全部用户房产信息 **************************/
	private void selecthouse(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String str = null;
		request.setAttribute("house", HouseDAO1.query(str));
		request.getRequestDispatcher("House.jsp").forward(request, response);
	}
	
	/*********************** 添加用户房产信息 **************************/
	private void houseAdd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HouseBean hb=new HouseBean();
		hb.setHouse_id(request.getParameter("house_id"));
		hb.setResident_id(request.getParameter("resident_id"));
		hb.setNam(request.getParameter("nam"));
		hb.setBuilding_id(request.getParameter("building_id"));
		hb.setHome_id(request.getParameter("home_id"));
		hb.setPhone(request.getParameter("phone"));
		int a = HouseDAO1.insert(hb);
		if (a == 0) {
			request.setAttribute("error", "用户房产信息添加失败！");
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		} else if (a == 2) {
			request.setAttribute("error", "该用户房产信息已经添加！");
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		} else {
			request.getRequestDispatcher("House_ok.jsp?para=1").forward(
					request, response);
		}
	}
	
	/*********************** 删除用户房产信息 **************************/
	private void houseDel(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HouseBean hb = new HouseBean();
		hb.setHouse_id(request.getParameter("ID"));
		//bb.setBuilding_id(request.getString("ID"));
		System.out.println("111111111111111");
		int ret = HouseDAO1.delete(hb);
		if (ret == 0) {
			request.setAttribute("error", "删除用户房产信息失败！");
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		} else {
			request.getRequestDispatcher("House_ok.jsp?para=3").forward(
					request, response);
		}
	}
	
	/*********************** 查询修改用户房产信息 **************************/
	private void houseModifyQuery(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HouseBean hb=new HouseBean();
		System.out.println("查询修改用户房产信息：" + request.getParameter("house_id"));
		hb.setHouse_id(request.getParameter("house_id"));
		request.setAttribute("houseQueryif", HouseDAO1.queryM(hb));
		request.getRequestDispatcher("House_Modify.jsp").forward(request,
				response);
	}
	
	/*********************** 修改用户房产信息 **************************/
	private void houseModify(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HouseBean hb=new HouseBean();
		hb.setHouse_id(request.getParameter("house_id"));
		hb.setResident_id(request.getParameter("resident_id"));
		hb.setNam(request.getParameter("nam"));
		hb.setBuilding_id(request.getParameter("building_id"));
		hb.setHome_id(request.getParameter("home_id"));
		hb.setPhone(request.getParameter("phone"));
		int ret = HouseDAO1.update(hb);
		if (ret == 0) {
			request.setAttribute("error", "修改用户房产信息失败！");
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		} else {
			request.getRequestDispatcher("House_ok.jsp?para=2").forward(
					request, response);
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


