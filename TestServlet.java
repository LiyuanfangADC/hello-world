package com.mvc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.bean.TestBean;
import com.mvc.dao.TestDAO;

/**
 * Servlet implementation class building
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TestDAO TestDAO1 = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        
    		this.TestDAO1 = new TestDAO();
    	
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
				request.setAttribute("error", "您的操作有误！");
				request.getRequestDispatcher("error.jsp")
						.forward(request, response);
			} else if ("TestQuery".equals(action)) {
				SelectTest(request, response);
			} else if ("TestAdd".equals(action)) {
				TestAdd(request, response);
			} else if ("TestModifyQuery".equals(action)) {
				TestModifyQuery(request, response);
			} else if ("TestModify".equals(action)) {
				TestModify(request, response);
			} else if ("TestDel".equals(action)) {
				TestDel(request, response);
			} 
		}
	/*********************** 查询全部信息 **************************/
	private void SelectTest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String str = null;
		request.setAttribute("test", TestDAO.query(str));
		request.getRequestDispatcher("Test.jsp").forward(request, response);
	}
	
	/*********************** 添加信息 *******************************/
	private void TestAdd(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		TestBean bb=new TestBean();
		
		bb.setId( request.getParameter("id"));
		bb.setTest_men( request.getParameter( "test_men" ));
		bb.setTest_content( request.getParameter( "test_content" ));
		bb.setTest_date( request.getParameter("test_date"));
		bb.setTest_result( request.getParameter("test_result") );
		
		int a = TestDAO1.insert(bb);
		System.out.println("a = " + a);
		if (a == 0) {
			request.setAttribute("error", "信息添加失败！");
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		} else if (a == 2) {
			request.setAttribute("error", "该信息已经添加！");
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		} else {
			request.getRequestDispatcher("Test_ok.jsp?para=1")
			        .forward(request, response);
		}
	}
	
	/***********************查询修改信息**************************/
    private void TestModifyQuery(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
    	TestBean t_Bean=new TestBean();
    	t_Bean.setId(request.getParameter("ID"));
    	t_Bean.setTest_men(request.getParameter("test_men"));
    	t_Bean.setTest_content(request.getParameter("test_content"));
    	t_Bean.setTest_date(request.getParameter("test_date"));
    	t_Bean.setTest_result(request.getParameter("test_result"));
    	
   	    System.out.println(""+t_Bean.getId());
   	    System.out.println(""+t_Bean.getTest_men());
        request.setAttribute("TestQueryif",TestDAO1.queryM(t_Bean));
        request.getRequestDispatcher("UpdateTest.jsp").forward(request, response);
    }
    /***********************修改信息**************************/
    private void TestModify(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
    	TestBean t_Bean=new TestBean();
   	 t_Bean.setId(request.getParameter("id"));
   	 t_Bean.setTest_men(request.getParameter("test_men"));
     t_Bean.setTest_content(request.getParameter("test_content"));
     t_Bean.setTest_date(request.getParameter("test_date"));
     t_Bean.setTest_result(request.getParameter("test_result"));
     
        int ret=TestDAO1.update(t_Bean);
        if(ret==0){
            request.setAttribute("error","修改信息失败！");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }else{
       	 request.getRequestDispatcher("Test_ok.jsp?para=2").forward(request, response);
        }
    }
	
    /*********************** 删除读者信息 **************************/
	private void TestDel(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		TestBean bb = new TestBean();
		bb.setId(request.getParameter("ID"));
		//bb.setBuilding_id(request.getString("ID"));
		System.out.println("bb.getId()"+bb.getId());
		int ret = TestDAO1.delete(bb);
		if (ret == 0) {
			request.setAttribute("error", "删除楼宇信息失败！");
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		} else {
			request.getRequestDispatcher("Test_ok.jsp?para=3").forward(
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


