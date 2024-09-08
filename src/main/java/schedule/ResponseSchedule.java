package schedule;

import java.util.ArrayList;

public class ResponseSchedule {

    private boolean success;
    private ArrayList<ScheduleInfo> data;
    private MetaInfo meta;

    public MetaInfo getMeta() {
        return meta;
    }

    public boolean isSuccess() {
        return success;
    }

    public ArrayList<ScheduleInfo> getData() {
        return data;
    }


}
