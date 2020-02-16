package utils;

public class ProductPOJO {
	 
	
	boolean availability;
	int categoryId;
	String color;
	String createdOn;
	String description;
	int discount;
	String materialDescription;
	String name ;
	int price ;
	String updateOn;
	int warranty ;

	public ProductPOJO(boolean availability, int categoryId, String color, String createdOn, String description,
			int discount, String materialDescription, String name, int price, String updateOn, int warranty) {
		
		this.availability = availability;
		this.categoryId = categoryId;
		this.color = color;
		this.createdOn = createdOn;
		this.description = description;
		this.discount = discount;
		this.materialDescription = materialDescription;
		this.name = name; 
		this.price = price;
		this.updateOn = updateOn;
		this.warranty = warranty;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getMaterialDescription() {
		return materialDescription;
	}

	public void setMaterialDescription(String materialDescription) {
		this.materialDescription = materialDescription;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getUpdateOn() {
		return updateOn;
	}

	public void setUpdateOn(String updateOn) {
		this.updateOn = updateOn;
	}

	public int getWarranty() {
		return warranty;
	}

	public void setWarranty(int warranty) {
		this.warranty = warranty;
	}	 
	 
}
