package masterlist;


import java.util.List;

public class Master {

    private int id;
    private boolean bookable;
    private int show_rating;
    private float rating;
    private int votes_counter;
    private int comments_count;

    private String avatar;
    private  String name;
    private String seance_date;
    private List<Object> meta;

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

    public boolean isBookable() {
        return bookable;
    }

    public void setBookable(boolean bookable) {
        this.bookable = bookable;
    }

    public int getShow_rating() {
        return show_rating;
    }

    public void setShow_rating(int show_rating) {
        this.show_rating = show_rating;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getVotes_counter() {
        return votes_counter;
    }

    public void setVotes_counter(int votes_counter) {
        this.votes_counter = votes_counter;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public String getSeance_date() {
        return seance_date;
    }

    public void setSeance_date(String seance_date) {
        this.seance_date = seance_date;
    }

    public List<Object> getMeta() {
        return meta;
    }

    public void setMeta(List<Object> meta) {
        this.meta = meta;
    }
}
