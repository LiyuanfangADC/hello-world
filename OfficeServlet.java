package com.mvc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.bean.CostBean;
import com.mvc.bean.OfficeBean;
import com.mvc.dao.OfficeDAO;

/**
 * Servlet implementation class building
 */
@SuppressWarnings("unused")
@WebServlet("/OfficeServlet")
public class OfficeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OfficeDAO OfficeDAO1 = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OfficeServlet() {
        super();
        
    	this.OfficeDAO1 = new OfficeDAO();
    	
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
			String action = request.getParameter("action");
			System.out.println("\nreader*********************action=" + action);
			if (action == null || "".equals(action)) {
				request.setAttribute("error", "您的输入有误！");
				request.getRequestDispatcher("error.jsp")
						.forward(request, response);
			} else if ("OfficeQuery".equals(action)) {
				selectOffice(request, response);
			} else if ("OfficeAdd".equals(action)) {
				OfficeAdd(request, response);
			} else if ("OfficeModifyQuery".equals(action)) {
				OfficeModifyQuery(request, response);
			} else if ("OfficeModify".equals(action)) {
				OfficeModify(request, response);
			} else if ("OfficeDelete".equals(action)) {
				OfficeDelete(request, response);
			} else if ("readerDetail".equals(action)) {
				//readerDetail(request, response);
			}
		}

	/*********************************** 查询************************** */
	private void selectOffice(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String str = null;
		request.setAttribute("Office", OfficeDAO.query(str));
		request.getRequestDispatcher("Office.jsp").forward(request, response);
	}
	
	/*********************************** 增加 ***************************/
	private void OfficeAdd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/* System.out.println("yyyyy"); */
		OfficeBean bb=new OfficeBean();
		
		bb.setBuilding_id(request.getParameter("building_id"));
	    bb.setOffice_site(request.getParameter("office_site"));
	    bb.setOffice_phone(request.getParameter("office_phone"));
	    		
		
		int a = OfficeDAO1.insert(bb);
		if (a == 0) {
			request.setAttribute("error", "办公室信息添加失败！");
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		} else if (a == 2) {
			request.setAttribute("error", "该办公室信息已经添加！");
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		} else {
			request.getRequestDispatcher("Off_OK.jsp?para=1").forward(
					request, response);
		}
	}
	
	/***********************************删除 ***************************/
	private void OfficeDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		OfficeBean bb = new OfficeBean();
		bb.setBuilding_id(request.getParameter("ID"));
		bb.setOffice_site(request.getParameter("ID2"));
		System.out.println("111111111111111");
		int ret = OfficeDAO1.delete(bb);
		if (ret == 0) {
			request.setAttribute("error", "删除办公室信息失败！");
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		} else {
			request.getRequestDispatcher("Off_OK.jsp?para=3").forward(
					request, response);
		}
	}
	
	/***********************查询修改信息**************************/
    private void OfficeModifyQuery(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
    	
   	 OfficeBean con_Bean=new OfficeBean();
   	 
   	    con_Bean.setBuilding_id(request.getParameter("ID"));
   	    
   	    con_Bean.setOffice_site(request.getParameter("ID2"));
   	    con_Bean.setOffice_phone(request.getParameter("office_phone"));
   	      	    
        request.setAttribute("OfficeQueryif",OfficeDAO1.queryM(con_Bean));
        request.getRequestDispatcher("UpdateOffice.jsp").forward(request, response);
    }
    /***********************修改信息**************************/
    private void OfficeModify(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
    	
   	    OfficeBean con_Bean=new OfficeBean();
   	    con_Bean.setBuilding_id(request.getParameter("building_id"));
	    con_Bean.setOffice_site(request.getParameter("office_site"));
	    con_Bean.setOffice_phone(request.getParameter("office_phone"));
	    
	    int ret=OfficeDAO1.update(con_Bean);
	    
        if(ret==0){
            request.setAttribute("error","修改信息失败！");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }else{
       	 request.getRequestDispatcher("Off_OK.jsp?para=2").forward(request, response);
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


