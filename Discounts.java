package bcpa.backend;

public class Discounts {
	private int promotionId;
	private double discountPercentage;
	private String description;

	public Discounts(int promotion, double percentage, String description) {
		this.setPromotionId(promotion);
		this.setDiscountPercentage(percentage);
		this.setDescription(description);
	}

	public int getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(int promotionId) {
		this.promotionId = promotionId;
	}

	public double getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
