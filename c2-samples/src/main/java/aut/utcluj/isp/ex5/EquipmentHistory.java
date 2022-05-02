package aut.utcluj.isp.ex5;

import aut.utcluj.isp.ex4.EquipmentHistoryDetails;
import aut.utcluj.isp.ex4.Operation;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author stefan
 */
public class EquipmentHistory {
    private List<EquipmentHistoryDetails> historyDetailsList;

    public void addEquipmentHistory(final String owner, final Operation operation, final LocalDateTime providedDate) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<EquipmentHistoryDetails> filterEquipmentHistoryByOperation(final Operation operation) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<EquipmentHistoryDetails> sortEquipmentHistoryByDateDesc() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<EquipmentHistoryDetails> getHistoryDetailsList() {
        return historyDetailsList;
    }
}
