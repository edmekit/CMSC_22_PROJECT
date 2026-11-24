/* Product class
 * 
 * This class is for creating Products that will be used throughout the project.
 * @author John Kirby Ocampo
 * */

package product;

import java.io.Serializable;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	private String prodId;
	private String prodName;
	private String description;
	private double price;
	
	public Product(String id,String name, String description, double price) {
		this.prodId = id;
		this.prodName = name;
		this.description = description;
		this.price = price;
	}
	public String getId() {return this.prodId;}
	public String getProdName() {
		return this.prodName;
	}
	public String getDescription() {
		return this.description;
	}
	public double getPrice() {
		return this.price;
	}
	public void setProdName(String pn) {this.prodName = pn;}
	public void setDescription(String d) {this.description = d;}
	public void setPrice(double p) {this.price = p;}
	public void setId(String id) {this.prodId = id;}
}
