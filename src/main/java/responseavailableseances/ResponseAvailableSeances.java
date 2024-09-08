package responseavailableseances;

import java.util.ArrayList;

public class ResponseAvailableSeances {

    private boolean success;
    private ArrayList<SeancesData> data;
    private ArrayList<?> meta;

    public boolean isSuccess() {
        return success;
    }

    public ArrayList<SeancesData> getData() {
        return data;
    }

    public ArrayList<?> getMeta() {
        return meta;
    }
}
