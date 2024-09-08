package services;

import java.util.ArrayList;

public class ResponseServices {

    private boolean success;
    private Services data;
    private ArrayList<?> meta;

    public Services getData() {
        return data;
    }

    public boolean isSuccess() {
        return success;
    }

    public ArrayList<?> getMeta() {
        return meta;
    }

    @Override
    public String toString() {
        return "ResponseServices{" +
                "data=" + data +
                '}';
    }
}
