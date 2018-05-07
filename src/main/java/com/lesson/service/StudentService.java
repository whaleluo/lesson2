package com.lesson.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.lession.model.Student;
import com.lesson.dao.StudentDao;
public class StudentService extends HttpServlet {

	private static final long serialVersionUID = 112L;
	/**
	 * 根据action做请求分发
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");
		if("preadd".equals(action)) {
			this.preadd(req,resp);
		}else if("add".equals(action)) {
			this.add(req, resp);
		}else if("list".equals(action)){
			this.list(req,resp);
		}else if("rem".equals(action)){
			this.rem(req,resp);
		}
	}
	/**
	 * 根据data删除指定元素
	 * @param req
	 * @param resp
	 */
	private void rem(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String data = req.getParameter("data");
			StudentDao sd = new StudentDao();
			sd.studentRem(data);
			resp.sendRedirect("student?action=list");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 页面点击修改跳到修改页面
	 * 跟据id和score找到元素,放在请求域中,供修改页面显示
	 * @param req
	 * @param resp
	 */
	private void preadd(HttpServletRequest req, HttpServletResponse resp) {
		String id = req.getParameter("id");
		String score = req.getParameter("score");
		Student student =null;
		try {
			if((id!=null)&&(score!=null)) {
				StudentDao sd = new StudentDao();
				double dscore = Double.parseDouble(score);
				student = sd.findByScoreAndId(dscore, id);
				req.setAttribute("student",student);
			}
			req.getRequestDispatcher("preadd.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 分页查询
	 * @param req
	 * @param resp
	 */
	private void list(HttpServletRequest req, HttpServletResponse resp){
		StudentDao sd = new StudentDao();
		String currentPage = req.getParameter("currentPage");
		if( currentPage==null||currentPage.isEmpty()){
			currentPage="1";//如果当前页为空，使当前页为第一页
		}
		try {
			int parseInt = Integer.parseInt(currentPage);
			long ssp = sd.studentSumPages();
			int parseInt2 = Integer.parseInt(Long.toString(ssp));
			if(parseInt>parseInt2) {
				currentPage="1";
			}
		} catch (Exception e) {
			currentPage="1";
		}
		try {
			long lastPage = sd.studentSumPages();//获取总页数
			List<Student> studentList = sd.studentList(currentPage);
			//一页显示十条数据,每次根据当前页获取下页数据
			req.setAttribute("currentPage", currentPage);
			req.setAttribute("lastPage", lastPage);
			req.setAttribute("studentList", studentList);
			req.getRequestDispatcher("list.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 添加和修改
	 * @param req
	 * @param resp
	 */
	private void add(HttpServletRequest req, HttpServletResponse resp) {
		String olddata = req.getParameter("olddata");//删除用，添加不用，为空时是添加，不能修改
		
		String name = req.getParameter("name");
		String birthday =req.getParameter("birthday");
		String description =req.getParameter("description");
		String descr = description.replace(",", "，");
		String score = req.getParameter("score");
		if (isEmpte(name,birthday,descr,score)) {
			System.out.println("sda");
			return;
		}
		double sco;
		try {
			 sco= Double.parseDouble(score);
			 if(sco<0&&sco>100) {
				 return;
			 }
		} catch (Exception e) {
			System.out.println(e+"分数错误");
			return;
		}
		StudentDao sd = new StudentDao();
		String[] split = olddata.split(",");
		if(split!=null&&(split.length>0)&&!split[0].isEmpty()){
			//如果id可以获取到则是修改请求,否则使添加请求
			String id=split[0];
			String newdata=id+","+name+","+birthday+","+descr;
			sd.studentRem(olddata);//修改是先删除旧值再添加新值
			sd.studentAdd(newdata,sco);
		}else {
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			String data=uuid+","+name+","+birthday+","+descr;
			sd.studentAdd(data,sco);
		}
		try {
			resp.sendRedirect("student?action=list");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 拦截空字符串
	 * @param args
	 * @return
	 */
	public boolean isEmpte(String...args) {
		for (String str : args) {
			if(str==null||str.length()==0) {
				return true;
			}
		}
		return false;
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
