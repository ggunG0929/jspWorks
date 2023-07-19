package product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCUtil;

public class ProductDAO {
	Connection conn = null;		// import
	PreparedStatement pstmt = null;		// import
	ResultSet rs = null;	// import
	
	// 상품 목록 보기
	public List<Product> getProductList() {		// import
		List<Product> productList = new ArrayList<>();	// import
		conn = JDBCUtil.getConnection();
		String sql = "SELECT * FROM product";
		try {
			pstmt = conn.prepareStatement(sql);		// surround with try catch
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Product product = new Product();
				
				product.setProductId(rs.getString("p_id"));
				product.setPname(rs.getString("p_name"));
				product.setUnitPrice(rs.getInt("p_unitPrice"));
				product.setDescription(rs.getString("p_description"));
				product.setCategory(rs.getString("p_category"));
				product.setManufacturer(rs.getString("p_manufacturer"));
				product.setUnitsInStock(rs.getLong("p_unitsInStock"));
				product.setCondition(rs.getString("p_condition"));
				product.setProductImage(rs.getString("p_productImage"));
				
				productList.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, pstmt, rs);
		}
		return productList;
	}
}
