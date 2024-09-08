package responsecloseravailableseances;

import java.util.ArrayList;
import java.util.List;

public class ResponseSeanceDate {

        private boolean success;
        private List<Data> data;
        private ArrayList<Object> meta;

        public boolean isSuccess() {
            return success;
        }

        public List<Data> getData() {
            return data;
        }

        public ArrayList<Object> getMeta() {
            return meta;
        }
    }

