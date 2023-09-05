package in.fssa.productprice.model;

import java.awt.image.BufferedImage;

public class Category {
    private int id;
    private String name;
    private String imageURL;
    private boolean isActive;
    
    public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "Category id=" + id + ", name=" + name + ", isActive=" + isActive;
    }
}
