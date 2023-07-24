package controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import member.Member;
import member.MemberDAO;
import product.Product;
import product.ProductDAO;

@WebServlet("*.do")
public class MainController extends HttpServlet {	// rename file
	private static final long serialVersionUID = 1L;
	
	private ProductDAO productDAO;
	private MemberDAO memberDAO;	// import

	public void init(ServletConfig config) throws ServletException {
		productDAO = new ProductDAO();
		memberDAO = new MemberDAO();
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
		// 상품 controller
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
			productDAO.deleteProduct(id);
			nextPage = "/editProduct.do?edit=delete";

		}else if(command.equals("/updateProductForm.do")) {	// 상품 수정
			String id = request.getParameter("productId");
			Product product = productDAO.getProduct(id);
			request.setAttribute("product", product);
			nextPage = "/product/updateProductForm.jsp";
		}else if(command.equals("/updateProduct.do")) {
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
			// DAO 호출
			if(productImage != null) {	// 입력된 사진이 있을 때
				productDAO.updateProduct(product);
			}else {		// 입력된 사진이 없을 때
				productDAO.updateProductNoImage(product);
			}
			nextPage = "/editProduct.do?edit=update";

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
			// 장바구니에 담기 및 세션발급
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
			int total = 0, sum = 0;		// 소계, 중계
			for(int i=0; i<list.size(); i++) {
				Product product = list.get(i);
				total = product.getUnitPrice()*product.getQuantity();
				sum += total;
			}

			// 주문하기에 필요한 cartId를 생성
			String cartId = session.getId();
			// 모델 생성
			request.setAttribute("cartList", list);
			request.setAttribute("sum", sum);
			request.setAttribute("cartId", cartId);
			nextPage = "/product/cart.jsp";
			
		}else if(command.equals("/deleteCart.do")) {	// 장바구니 전체비우기
			// 세션 해제
			session.invalidate();
			
		}else if(command.equals("/removeCart.do")) {	// 장바구니에서 상품삭제
			String id = request.getParameter("productId");
			// 장바구니 가져오기 및 세션 유지
			List<Product> cartList = (ArrayList)session.getAttribute("cartList");
			Product selProduct = new Product();		// 삭제할 상품 객체
			for(int i=0; i<cartList.size(); i++) {
				selProduct = cartList.get(i);
				if(selProduct.getProductId().equals(id)) {
					cartList.remove(selProduct);
				}
			}
			
		}else if(command.equals("/shippingInfo.do")) {	// 주문하기
			String cartId = request.getParameter("cartId");
			request.setAttribute("cartId", cartId);
			nextPage = "/product/shippingInfo.jsp";
		}else if(command.equals("/processShippingInfo.do")) {
			// 쿠키 발행
			Cookie shippingId = new Cookie("Shipping_cartId", 	// import
					URLEncoder.encode(request.getParameter("cartId"), "utf-8"));	// import
			Cookie name = new Cookie("Shipping_name", 
					URLEncoder.encode(request.getParameter("name"), "utf-8"));
			Cookie shippingDate = new Cookie("Shipping_shippingDate", 
					URLEncoder.encode(request.getParameter("shippingDate"), "utf-8"));
			Cookie country = new Cookie("Shipping_country", 
					URLEncoder.encode(request.getParameter("country"), "utf-8"));
			Cookie zipCode = new Cookie("Shipping_zipCode", 
					URLEncoder.encode(request.getParameter("zipCode"), "utf-8"));
			Cookie addressName = new Cookie("Shipping_addressName", 
					URLEncoder.encode(request.getParameter("addressName"), "utf-8"));
			// 쿠키 유효기간 1일
			shippingId.setMaxAge(24*60*60);
			name.setMaxAge(24*60*60);
			shippingDate.setMaxAge(24*60*60);
			country.setMaxAge(24*60*60);
			zipCode.setMaxAge(24*60*60);
			addressName.setMaxAge(24*60*60);
			// 클라이언트 컴으로 쿠키 보내기
			response.addCookie(shippingId);
			response.addCookie(name);
			response.addCookie(shippingDate);
			response.addCookie(country);
			response.addCookie(zipCode);
			response.addCookie(addressName);
			// 인코딩된 쿠키 받아서 디코딩 -> 모델로 보내기
			// 변수 선언
			String shipping_cartId = "";	// 주문번호
			String shipping_name = "";		// 주문자
			String shipping_shippingDate = "";	// 배송일
			String shipping_country = "";	// 국가
			String shipping_zipCode = "";	// 우편번호
			String shipping_addressName = "";	// 주소
			// 쿠키를 받을 배열 생성
			Cookie[] cookies = request.getCookies();
			// 디코딩
			if(cookies !=null) {
				for(int i=0; i<cookies.length; i++) {
					Cookie cookie = cookies[i];
					String cname = cookie.getName();	// 쿠키 이름
					if(cname.equals("Shipping_cartId"))		// 쿠키 이름이 같으면 쿠키값을 복원(디코딩)
						shipping_cartId = URLDecoder.decode(cookie.getValue(), "utf-8");
					if(cname.equals("Shipping_name"))
						shipping_name = URLDecoder.decode(cookie.getValue(), "utf-8");
					if(cname.equals("Shipping_shippingDate"))
						shipping_shippingDate = URLDecoder.decode(cookie.getValue(), "utf-8");
					if(cname.equals("Shipping_country"))
						shipping_country = URLDecoder.decode(cookie.getValue(), "utf-8");
					if(cname.equals("Shipping_zipCode"))
						shipping_zipCode = URLDecoder.decode(cookie.getValue(), "utf-8");
					if(cname.equals("Shipping_addressName"))
						shipping_addressName = URLDecoder.decode(cookie.getValue(), "utf-8");
				}
			}
			// 상품관련 가져오기
			List<Product> list = (ArrayList)session.getAttribute("cartList");
			if(list == null) {
				list = new ArrayList<>();
			}
			// 총합계 계산하기
			int total = 0, sum = 0;		// 소계, 중계
			for(int i=0; i<list.size(); i++) {
				Product product = list.get(i);
				total = product.getUnitPrice()*product.getQuantity();
				sum += total;
			}
			// 배송정보 모델 생성
			request.setAttribute("shipping_name", shipping_name);
			request.setAttribute("shipping_shippingDate", shipping_shippingDate);
			request.setAttribute("shipping_zipCode", shipping_zipCode);
			request.setAttribute("shipping_addressName", shipping_addressName);
			// 상품관련 모델 생성
			request.setAttribute("cartList", list);
			request.setAttribute("sum", sum);
			// 이동할 페이지 - 주문완료
			nextPage = "/product/orderConfirm.jsp";
			
		}else if(command.equals("/thanksCustomer.do")) {	// 주문완료, 배송시작 페이지
			String shipping_cartId = "";	// 주문번호
			String shipping_shippingDate = "";	// 배송일
			Cookie[] cookies = request.getCookies();
			if(cookies != null) {
				for(int i=0; i<cookies.length; i++) {
					Cookie cookie = cookies[i];
					String cname = cookie.getName();
					if(cname.equals("Shipping_cartId"))
						shipping_cartId = URLDecoder.decode(cookie.getValue(), "utf-8");
					if(cname.equals("Shipping_shippingDate"))
						shipping_shippingDate = URLDecoder.decode(cookie.getValue(), "utf-8");
				}
			}
			request.setAttribute("shipping_cartId", shipping_cartId);
			request.setAttribute("shipping_shippingDate", shipping_shippingDate);
			// 세션 삭제 - 장바구니 비움
			session.invalidate();
			// 쿠키 삭제
			if(cookies !=null) {
				for(int i=0; i<cookies.length; i++) {
					Cookie cookie = cookies[i];
					String cname = cookie.getName();	// 쿠키 이름
					if(cname.equals("Shipping_cartId"))		// 쿠키 이름이 같으면 쿠키값을 삭제
						cookie.setMaxAge(0);	// 쿠키 유효기간을 0으로 초기화
					if(cname.equals("Shipping_name"))
						cookie.setMaxAge(0);
					if(cname.equals("Shipping_shippingDate"))
						cookie.setMaxAge(0);
					if(cname.equals("Shipping_country"))
						cookie.setMaxAge(0);
					if(cname.equals("Shipping_zipCode"))
						cookie.setMaxAge(0);
					if(cname.equals("Shipping_addressName"))
						cookie.setMaxAge(0);
				}
			}
			nextPage = "/product/thanksCustomer.jsp";

		}else if(command.equals("/checkOutCancel.do")) {	// 주문취소
			nextPage = "/product/checkOutCancel.jsp";
		}
		
		
		
		// 회원 controller
		if(command.equals("/memberList.do")) {			// 회원 목록 페이지
			List<Member> memberList = memberDAO.getMemberList();	// import
			request.setAttribute("memberList", memberList);		// 모델 생성
			nextPage = "/member/memberList.jsp";

		}else if(command.equals("/memberInfo.do")) {	// 회원 정보
			String mid = request.getParameter("mid");
			Member member = memberDAO.getMember(mid);
			request.setAttribute("member", member);
			nextPage = "/member/memberInfo.jsp";
		
		}else if(command.equals("/memberForm.do")) {	// 회원 가입
			nextPage = "/member/memberForm.jsp";
		}else if(command.equals("/addMember.do")) {
			// 회원 가입 폼 데이터 받기
			String mid = request.getParameter("mid");
			String passwd = request.getParameter("passwd1");
			String mname = request.getParameter("mname");
			String gender = request.getParameter("gender");
			// birth
			String birthyy = request.getParameter("birthyy");
			String birthmm = request.getParameterValues("birthmm")[0];
			String birthdd = request.getParameter("birthdd");
			String birth = birthyy + "/" + birthmm + "/" + birthdd;
			// email
			String email1 = request.getParameter("email1");
			String email2 = request.getParameter("email2");
			String email = email1 + "@" + email2;
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			Member newMember = new Member();
			newMember.setMid(mid);
			newMember.setPasswd(passwd);
			newMember.setMname(mname);
			newMember.setGender(gender);
			newMember.setBirth(birth);
			newMember.setEmail(email);
			newMember.setPhone(phone);
			newMember.setAddress(address);
			// 자동 로그인 - 세션발급
			session.setAttribute("sessionId", mid);
			// memberDAO의 addMember 함수호출
			memberDAO.addMember(newMember);
			nextPage = "/index.jsp";
			
		}else if(command.equals("/loginForm.do")) {		// 로그인
			nextPage = "/member/loginForm.jsp";
		}else if(command.equals("/processLogin.do")) {
			// 로그인 폼에 입력된 데이터 받기
			String mid = request.getParameter("mid");
			String passwd = request.getParameter("passwd");
			// 로그인 할 member 객체 생성
			Member loginMember = new Member();
			loginMember.setMid(mid);
			loginMember.setPasswd(passwd);
			// memberDAO의 함수호출
			boolean result = memberDAO.checkLogin(loginMember);
			if(result) {	// result == true인 경우
				session.setAttribute("sessionId", mid);		// id로 세션 발급
				nextPage = "/index.jsp";
			}else {
				String error = "아이디나 비밀번호를 확인해 주세요.";
				request.setAttribute("error", error);
				nextPage = "/loginForm.do";
			}
			
		}else if(command.equals("/logout.do")) {			// 로그아웃
			session.invalidate();
			nextPage = "/index.jsp";
		}
		
		
		
		// 페이지 포워딩
		if(command.equals("/addCart.do")) {				// 장바구니 담기 후 이동
			String id = request.getParameter("productId");
			response.sendRedirect("/productInfo.do?productId=" + id);
			
		}else if(command.equals("/deleteCart.do") || command.equals("/removeCart.do")) {	// 장바구니 전체 및 상품 삭제 후 이동
				response.sendRedirect("/cart.do");
			
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
		}
	}

}
