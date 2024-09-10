package repositories;

import masterlist.Master;

import java.util.ArrayList;
import java.util.List;

public class MastersRepository {

    private ArrayList<Master> masterNames;

    public MastersRepository() {
        this.masterNames = new ArrayList<>();
    }

    public void clearAndAddMasters(List<Master> newList) {
        masterNames.clear();
        masterNames.addAll(newList);
    }


    public int getStaffByIndex(int index) {
        Master master = masterNames.get(index);
        return master.getId();
    }

    public String getMastersChoice() {
        StringBuilder builder = new StringBuilder();
        int counter = 1;
        for (Master name : masterNames) {
            builder.append("(")
                    .append(counter)
                    .append(")")
                    .append(name)
                    .append("\n");
            counter++;
        }
        builder.append("Выберите мастера");

        return builder.toString();
    }

}
