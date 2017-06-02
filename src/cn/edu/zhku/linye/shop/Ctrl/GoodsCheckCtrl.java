package cn.edu.zhku.linye.shop.Ctrl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.zhku.jsj.huangxin.bean.User;
import cn.edu.zhku.linye.shop.Goods;
import cn.edu.zhku.linye.shop.Service.GoodsService;

public class GoodsCheckCtrl extends HttpServlet {
	
	GoodsService gs = new GoodsService();


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//设定输入编码格式
		request.setCharacterEncoding("utf-8");
		//设定输出编码格式
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		
		HttpSession session = request.getSession();
		String shopName =  ((User)session.getAttribute("user")).getShopName();
		
		String goodsName = request.getParameter("goodsName"); //先null后“”
System.out.println("shopname:"+shopName);
		Goods goods = new Goods();
		goods.setShopName(shopName);
		goods.setGoodsName(goodsName);

		try{	
			request.setAttribute("list", gs.getGoods(goods));
	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			RequestDispatcher rd = request.getRequestDispatcher("/GoodsCheck.jsp");   //  “/”代表根目录
			rd.forward(request, response);
			
		}
	}
}
