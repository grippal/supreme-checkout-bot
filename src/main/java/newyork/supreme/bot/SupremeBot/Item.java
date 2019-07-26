package newyork.supreme.bot.SupremeBot;

public class Item extends Billing {
	private String type;
	private String name;
	private String color;
	private String size;
	
	public Item() {
		
	}

	public Item(String type, String name, String color, String size) {
		this.type = type;
		this.name = name;
		this.color = color;
		this.size = size;
	}

	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	
	
}
