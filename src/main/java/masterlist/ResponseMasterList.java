package masterlist;

import java.util.ArrayList;

public class ResponseMasterList {

    private boolean success;
    private ArrayList<Master> data;

    public boolean isSuccess() {
        return success;
    }

    public ArrayList<Master> getData() {
        return data;
    }

}

