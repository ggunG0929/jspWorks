package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import product.Product;
import product.ProductDAO;

@WebServlet("*.do")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductDAO productDAO;

	public void init(ServletConfig config) throws ServletException {
		productDAO = new ProductDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String uri = request.getRequestURI();
		String command = uri.substring(uri.lastIndexOf("/"));
		System.out.println(command);
		
		String nextPage = null;
		
		// 세션 객체 생성
		HttpSession session = request.getSession();		// import
		
		// 경로에 슬래시!!!!
		if(command.equals("/productList.do")) {			// 상품 목록 페이지
			// 목록 메서드 호출
			List<Product> productList = productDAO.getProductList();	// import list, product
			// 모델 생성해서 정보담기(context)
			request.setAttribute("productList", productList);
			nextPage = "/product/productList.jsp";
			
		}else if(command.equals("/productInfo.do")) {	// 상품 정보 페이지
			// productId 받기
			String id = request.getParameter("productId");
			// id로 상품 정보 메서드 호출
			Product product = productDAO.getProduct(id);
			// 정보담기
			request.setAttribute("product", product);
			nextPage = "/product/productInfo.jsp";
			
		}else if(command.equals("/productForm.do")) {	// 상품 등록 페이지
			nextPage = "/product/productForm.jsp";
		}else if(command.equals("/addProduct.do")) {
			String realFolder = "C:/Users/Administrator/git/jspWorks/Market/src/main/webapp/upload";
			MultipartRequest multi = new MultipartRequest(request, realFolder, 
					5*1024*1024, "utf-8", new DefaultFileRenamePolicy());
			// name 속성 가져오기
			String id = multi.getParameter("productId");
			String pname = multi.getParameter("pname");
			int unitPrice = Integer.parseInt(multi.getParameter("unitPrice"));
			String description = multi.getParameter("description");
			String category = multi.getParameter("category");
			String manufacturer = multi.getParameter("manufacturer");
			long unitsInStock = Long.parseLong(multi.getParameter("unitsInStock"));
			String condition = multi.getParameter("condition");
			// productImage속성 가져오기
			String name = "";
			String productImage = "";
			Enumeration<String> files = multi.getFileNames();
			if(files.hasMoreElements()) {
				name = files.nextElement();		// 파일이 있으면 이름을 저장
				productImage = multi.getFilesystemName(name);	// 이름을 매개변수로 서버에 저장된 파일을 저장
			}
			// Product 객체 생성
			Product product = new Product();
			product.setProductId(id);
			product.setPname(pname);
			product.setUnitPrice(unitPrice);
			product.setDescription(description);
			product.setCategory(category);
			product.setManufacturer(manufacturer);
			product.setUnitsInStock(unitsInStock);
			product.setCondition(condition);
			product.setProductImage(productImage);
			// DB 등록 처리
			productDAO.addProduct(product);
			nextPage = "/productList.do";
			
		}else if(command.equals("/editProduct.do")) {	// 상품 수정 페이지
			List<Product> productList = productDAO.getProductList();
			String edit = request.getParameter("edit");
			request.setAttribute("productList", productList);
			request.setAttribute("edit", edit);
			nextPage = "/product/editProduct.jsp";
			
		}else if(command.equals("/deleteProduct.do")) {	// 상품 삭제
			String id = request.getParameter("productId");
			String edit = request.getParameter("edit");
			productDAO.deleteProduct(id);
			nextPage = "/editProduct.do?edit=" + edit;
			
		}else if(command.equals("/addCart.do")) {		// 상품주문(장바구니에 넣기)
			String id = request.getParameter("productId");
			// 상품 목록
			List<Product> goodsList = productDAO.getProductList();
			Product goods = new Product();
			// 목록 중에서 추가한 상품을 찾음
			for(int i=0; i<goodsList.size(); i++) {
				goods = goodsList.get(i);
				if(goods.getProductId().equals(id)) {
					break;
				}
			}
			// 장바구니에 담기
			List<Product> list = (ArrayList)session.getAttribute("cartList");
			if(list==null) {
				list = new ArrayList<>();	// import
				session.setAttribute("cartList", list);	// 세션이름 - cartList
			}
			// 수량 증가 - 요청 아이디의 상품이 기존에 장바구니에 있으면 수량 증가
			int cnt = 0;
			Product goodsQnt = new Product();	// 상품 객체(개수를 가짐)
			
			for(int i=0; i<list.size(); i++) {
				goodsQnt = list.get(i);
				if(goodsQnt.getProductId().equals(id)) {
					cnt++;	// 해당 품목을 추가한 횟수
					int orderQuantity = goodsQnt.getQuantity() + 1;	// 주문수량
					goodsQnt.setQuantity(orderQuantity);
				}
			}
			// 장바구니에 이전에 담긴 품목이 아니면 수량을 1로 하고, 장바구니 목록에 추가함
			if(cnt == 0) {
				goods.setQuantity(1);
				list.add(goods);
			}
			
		}else if(command.equals("/cart.do")) {		// 장바구니 페이지
			List<Product> list = (ArrayList)session.getAttribute("cartList");
			if(list == null) {
				list = new ArrayList<>();
			}
			// 총합계 계산하기
			Product product = null;
			int total = 0, sum = 0;		// 소계, 중계
			for(int i=0; i<list.size(); i++) {
				product = list.get(i);
				total = product.getUnitPrice()*product.getQuantity();
				sum += total;
			}
			request.setAttribute("cartList", list);
			request.setAttribute("sum", sum);
			nextPage = "/product/cart.jsp";
		}
		
		
		// 페이지 포워딩
		if(command.equals("/addCart.do")) {
			String id = request.getParameter("productId");
			response.sendRedirect("/productInfo.do?productId=" + id);
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
		}
	}

}
