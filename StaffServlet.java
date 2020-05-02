package com.mvc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.bean.StaffBean;
import com.mvc.dao.StaffDao;


/**
 * Servlet implementation class StaffServlet
 */
@WebServlet("/StaffServlet")
public class StaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StaffDao StaffDao1 = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffServlet() {
        super();
        // TODO Auto-generated constructor stub
        this.StaffDao1 = new StaffDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		System.out.println("\nreader*********************action=" + action);
		
		if (action == null || "".equals(action)) {
			request.setAttribute("error", "您的操作有误！");
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		} else if ("staffQuery".equals(action)) {
			selectstaff(request, response);
		} else if ("staffAdd".equals(action)) {
			addstaff(request, response);
		} else if ("StaffModifyQuery".equals(action)) {
			StaffModifyQuery(request, response);
		} else if ("StaffModify".equals(action)) {
			StaffModify(request, response);
		} else if ("staffDelete".equals(action)) {
			deletestaff(request, response);
		}
	}

	private void selectstaff(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String str = null;
		request.setAttribute("staffin", StaffDao.query(str));
		request.getRequestDispatcher("Staff.jsp").forward(request, response);
	}
	
	private void addstaff(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		StaffBean ss = new StaffBean();
		ss.setStaff_id(request.getParameter("staff_id"));
		ss.setStaff_name(request.getParameter("staff_name"));
		ss.setStaff_sex(request.getParameter("staff_sex"));
		ss.setStaff_work(request.getParameter("staff_work"));
		ss.setStaff_group(request.getParameter("staff_group"));
		
		int a = StaffDao1.insert(ss);
		
		if (a == 1) {
			request.getRequestDispatcher("Staff_ok.jsp?para=1").forward(request, response);
		} else if (a == 2) {
			request.setAttribute("error", "该员工信息已经添加！");
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		} else {
			request.setAttribute("error", "员工信息添加失败！");
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		}
	}
	
	private void deletestaff(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		StaffBean sf = new StaffBean();
		sf.setStaff_id(request.getParameter("Staff_id"));
		System.out.println("111111111111111");
		int ret = StaffDao1.delete(sf);
		if (ret == 0) {
			request.setAttribute("error", "删除成员信息失败！");
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		} else {
			request.getRequestDispatcher("Staff_ok.jsp?para=3").forward(
					request, response);
		}
	}

	/***********************查询修改信息**************************/
    private void StaffModifyQuery(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
   	    StaffBean s=new StaffBean();
   	    s.setStaff_id(request.getParameter("id"));
   	    s.setStaff_name(request.getParameter("name"));
   	    s.setStaff_sex(request.getParameter("sex"));
   	    s.setStaff_work(request.getParameter("work"));
   	    s.setStaff_group(request.getParameter("group"));
   	    
   	    System.out.println("Staff_id= "+s.getStaff_id());
   	    System.out.println("Staff_name= "+s.getStaff_name());
   	    
        request.setAttribute("StaffQueryif",StaffDao1.queryM(s));
        request.getRequestDispatcher("UpdateStaff.jsp").forward(request, response);
    }
    /***********************修改信息**************************/
    private void StaffModify(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
   	 StaffBean ss=new StaffBean();
   	 ss.setStaff_id(request.getParameter("staff_id"));
   	 ss.setStaff_name(request.getParameter("staff_name"));
     ss.setStaff_sex(request.getParameter("staff_sex"));
     ss.setStaff_work(request.getParameter("staff_work"));
     ss.setStaff_group(request.getParameter("staff_group"));
     
        int ret=StaffDao1.update(ss);
        if(ret==0){
            request.setAttribute("error","修改信息失败！");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }else{
       	 request.getRequestDispatcher("Staff_ok.jsp?para=2").forward(request, response);
        }
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
