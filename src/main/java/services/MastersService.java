package services;

import masterlist.Master;
import repositories.MastersRepository;

import java.util.List;

public class MastersService {
    private MastersRepository mastersRepository;

    public MastersService(MastersRepository mastersRepository) {
        this.mastersRepository = mastersRepository;
    }

    public void clearAndAddMasters(List<Master> newMastersList) {
        mastersRepository.clearAndAddMasters(newMastersList);
    }

//    public String getStaffIdByIndex(String indexAsString) { //FIXME VOID to STRING
//        try {
//            mastersRepository.getStaffByIndex(Integer.parseInt(indexAsString));
//        } catch (NumberFormatException e) {
//            throw new RuntimeException();       // fixme
//        }
//    }

    public String getMastersChoice() {
       return mastersRepository.getMastersChoice();
    }
}
