package schedule;

import java.util.ArrayList;

public class ScheduleInfo {

    private int staff_id;
    private String date;

    private ArrayList<SlotsInfo> slots;
    private ArrayList<IntervalsInfo> busy_intervals;

    private int off_day_type;

    public int getStaff_id() {
        return staff_id;
    }

    public String getDate() {
        return date;
    }

    public ArrayList<SlotsInfo> getSlots() {
        return slots;
    }

    public ArrayList<IntervalsInfo> getBusy_intervals() {
        return busy_intervals;
    }

    public int getOff_day_type() {
        return off_day_type;
    }
}
