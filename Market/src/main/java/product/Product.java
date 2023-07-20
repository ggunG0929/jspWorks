package product;

import java.io.Serializable;

public class Product implements Serializable {	// import
	
	private static final long serialVersionUID = 11L;
	
	private String productId;		// 상품 코드
	private String pname;			// 상품 이름
	private Integer unitPrice;		// 상품 가격
	private String description;		// 상품 설명
	private String manufacturer;	// 상품 제조사
	private String category;		// 상품 분류
	private long unitsInStock;		// 상품 재고
	private String condition;		// 신상품 or 중고
	private String productImage;	// 상품 이미지
	private Integer quantity;	// 장바구니에 담긴 수량	// 우클>src>getter setter
	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	// 우클>source> generate getter, setter
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Integer getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public long getUnitsInStock() {
		return unitsInStock;
	}
	public void setUnitsInStock(long unitsInStock) {
		this.unitsInStock = unitsInStock;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

}
