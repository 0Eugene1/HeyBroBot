package services;

import java.util.ArrayList;
import java.util.List;

public class Services {

    private List<Services> services;
    private int id;
    private String title;
    private int category_id;
    private float price_min;
    private float price_max;
    private float discount;
    private String comment;
    private String image;
    private int seance_length;
    private List<Category> category;

    public List<Category> getCategory() {
        return category;
    }

    public List<Services> getServices() {
        return services;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getCategory_id() {
        return category_id;
    }

    public float getPrice_min() {
        return price_min;
    }

    public float getPrice_max() {
        return price_max;
    }

    public float getDiscount() {
        return discount;
    }

    public String getComment() {
        return comment;
    }

    public String getImage() {
        return image;
    }

    public int getSeance_length() {
        return seance_length;
    }

    @Override
    public String toString() {
        return "Services{" +
                "services=" + services +
                ", title='" + title + '\'' +
                ", price_min=" + price_min +
                ", comment='" + comment + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
