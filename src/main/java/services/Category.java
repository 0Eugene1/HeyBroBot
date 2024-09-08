package services;

public class Category {

    private int id;
    private String title;
    private String api_id;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getApi_id() {
        return api_id;
    }

    @Override
    public String toString() {
        return "Category{" +
                "title='" + title + '\'' +
                '}';
    }
}
