package com.SSDI;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ssdi.dao.EmpTimekeeperDao;
import edu.ssdi.dao.HrDao;
import edu.ssdi.dao.TeamDao;
import edu.ssdi.model.EmpTimekeeper;
import edu.ssdi.model.EmployeeBean;
import edu.ssdi.model.LoginBean;

/**
 * Servlet implementation class AddServlet
 */
@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmpTimekeeperDao tkdao;
	private TeamDao teamdao;
	private HrDao hrdao;
	
//	String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime());
//    String tkDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
//    EmpTimekeeper emptk = new EmpTimekeeper();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddServlet() {
        super();
        tkdao = new EmpTimekeeperDao();
        teamdao = new TeamDao();
        hrdao = new HrDao();
     //    time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime());
     //   tkDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        
        // TODO Auto-generated constructor stub
    }

    public void getEmpId(int empId) {
    	
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 response.setContentType("text/html");
         //get stream obj
         //PrintWriter pw = response.getWriter();
         //write req processing logic
      /*   java.util.Date date = new java.util.Date();
         pw.println("<h2>"+"Current Date & Time: " +date.toString()+"</h2>");*/
        // String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(Calendar.getInstance().getTime());
        // String tkDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        // PrintWriter out = response.getWriter();
        
         
         
        // EmpTimekeeper emptk = new EmpTimekeeper();
         
        // emptk.setTkDate(tkDate);
       //  emptk.setEmpId(1);
         //out.println("timedate"+ emptk.getTkDate());
		 //HttpSession session = request.getSession();
	//	 String empId = (String)(request.getAttribute("empId"));

		// Integer empId = (Integer)(request.getAttribute("empId"));
		 //System.out.println(empId);
		 //Integer empId= Integer.parseInt(request.getParameter("empId"));
		//System.out.print("EMPLOYEEID:"+empId);
			
         String action = request.getServletPath();
         String page = null;
         
         switch (action) {
         case "/Home":
        	 RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
    	         dispatcher.forward(request, response);
    	         break;
        	 
         case "/ClockInTime":
        	 
        	
     		//if (session.getAttribute("currentUser") != null) {
     			  getTimeInOut(request, response);

     	
        	
        	   break;
        	 
 		 case "/timein": 
 		   addTimeIn(request, response) ;
 		    		    
 		     break;
 		 case "/timeout":
 			addTimeOut( request, response) ;
 			
 		     break;
 		     
 		     
 		case "/EmpCalendar" :
 		//	getEmpTime(request, response);
 		System.out.println("hi");
 		
 		    Integer id;
 			if(request.getParameter("empId")==null)
 			{
 				id=1;
 			}
 			else
 			{
 			id = Integer.parseInt(request.getParameter("empId"));
 			}
 			System.out.println(id);

 		
 
 			
 			    List<EmpTimekeeper> listEmpTime = tkdao.getEmployeeTime(id);
 			// String userId = request.getParameter("userId");
 			    
          request.setAttribute("listEmpTime",  listEmpTime); 
       
 			 
 	        
 	        RequestDispatcher rdp = request.getRequestDispatcher("Emp_Calendar.jsp");
 	         rdp.forward(request, response);
 			
 	    
 			 
 			 break;
 			 
 		 case "/TeamView" :
 			 
 			 
 			 getTeamView(request, response); 			 
 			 break;
 			 
 		 case "/Org":
 			   System.out.println("Org");
         
         	   getOrgView(request, response);
         	
         	   break;
 		 
 		 default:
 			System.out.println("Welcome");}
         
    
     
       
       
     
         
	}

	private void getTimeInOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// int id = Integer.parseInt(request.getParameter("id"));
		 String time = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss").format(Calendar.getInstance().getTime());
         String tkDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		 //Integer empId = (Integer)(request.getAttribute("empId"));
         Integer empid;
         if(request.getParameter("empId")==null)
			{
				empid=2;
			}
			else
			{
			empid = Integer.parseInt(request.getParameter("empId"));
			}
		// Integer empId= Integer.parseInt(request.getParameter("empId"));
		 System.out.println("hi");
		 System.out.println("Get Emp ID"+ empid);
		 int id = 1;

         EmpTimekeeper emptk = new EmpTimekeeper();
		    emptk.setTkDate(tkDate);
            emptk.setEmpId(id);
	        EmpTimekeeper Emptk = tkdao.getTime(emptk);
	        System.out.println(Emptk.getTimeIn());
	        RequestDispatcher dispatcher = request.getRequestDispatcher("ClockInTime.jsp");
	        request.setAttribute("Emptk", Emptk);
	        dispatcher.forward(request, response);
		// TODO Auto-generated method stub
		
	}
	
	private void addTimeIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("addtime");
		 String time = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss").format(Calendar.getInstance().getTime());
		 String tkDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		 EmpTimekeeper emptk = new EmpTimekeeper();
		
		 emptk.setTimeIn(time);
		 emptk.setTkDate(tkDate);
         emptk.setEmpId(1);
		    System.out.println("Time In");
		    tkdao.checkTimeIn(emptk);
		 
		     
		     
		     
		    getTimeInOut(request, response);
		
	}
	
	private void addTimeOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("addtime");
		 String time = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss").format(Calendar.getInstance().getTime());
		 String tkDate = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		 EmpTimekeeper emptk = new EmpTimekeeper();
		
		 emptk.setTimeOut(time);
		 emptk.setTkDate(tkDate);
         emptk.setEmpId(1);
		    System.out.println("Time Out");
		   
		    
	 		    tkdao.checkTimeOut(emptk);
		    getTimeInOut(request, response);
		
	}
	
	
	private void getEmpTime(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		System.out.println("hi");
 		
		    Integer id;
			if(request.getParameter("empId")==null)
			{
				id=1;
			}
			else
			{
			id = Integer.parseInt(request.getParameter("empId"));
			}
			System.out.println(id);

		

			
			    List<EmpTimekeeper> listEmpTime = tkdao.getEmployeeTime(id);
		
			    
      request.setAttribute("listEmpTime",  listEmpTime); 
   
			 
	        
	        RequestDispatcher rdp = request.getRequestDispatcher("Emp_Calendar.jsp");
	         rdp.forward(request, response);
	}

	
	private void getTeamView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 int empId = 1;
		// System.out.println("Team Calendar");
		    List<EmployeeBean> listTeam = teamdao.getTeam(empId);
		  
		// String userId = request.getParameter("userId");
            request.setAttribute("listTeam",  listTeam); 
        RequestDispatcher dp = request.getRequestDispatcher("Team_View.jsp");
         dp.forward(request, response);
	}
	
	private void getOrgView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 String dept  = "Finance";
		  List<EmployeeBean> listOrg = hrdao.getOrg();
			
		// String userId = request.getParameter("userId");
        request.setAttribute("listOrg",  listOrg); 
       // page = "Emp_Calendar.jsp";
			 
	        
	        RequestDispatcher rdp = request.getRequestDispatcher("Department.jsp");
	         rdp.forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	
}
}
