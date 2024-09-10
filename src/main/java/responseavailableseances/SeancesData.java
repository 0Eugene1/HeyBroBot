package responseavailableseances;

public class SeancesData {

  //  @JsonAttribute?     todo
    private String time;
    private int seance_length;
    private String dateTime;

    public String getTime() {
        return time;
    }

    public int getSeance_length() {
        return seance_length;
    }

    public String getDateTime() {
        return dateTime;
    }


    // todo toString()
}
