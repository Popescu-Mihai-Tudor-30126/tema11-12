package aut.utcluj.isp.ex5;

import aut.utcluj.isp.ex4.EquipmentHistoryDetails;
import aut.utcluj.isp.ex4.Operation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author stefan
 */
public class EquipmentHistory implements IEquipmentHistory {
    private List<EquipmentHistoryDetails> historyDetailsList;

    public EquipmentHistory() {
        this.historyDetailsList = new ArrayList<>();
    }

    public void addEquipmentHistory(final String owner, final Operation operation, final LocalDateTime providedDate) {
        historyDetailsList.add(new EquipmentHistoryDetails(owner, operation, providedDate));
    }

    public List<EquipmentHistoryDetails> filterEquipmentHistoryByOperation(final Operation operation) {
        List<EquipmentHistoryDetails> equipmentHistoryDetailsResult = new ArrayList<>();
        for (EquipmentHistoryDetails equipmentHistoryDetails : this.historyDetailsList) {
            if (equipmentHistoryDetails.getOperation().equals(operation)) {
                equipmentHistoryDetailsResult.add(equipmentHistoryDetails);
            }
        }
        return equipmentHistoryDetailsResult;
    }

    public List<EquipmentHistoryDetails> sortEquipmentHistoryByDateDesc() {
        Collections.sort(this.historyDetailsList, Comparator.comparing(EquipmentHistoryDetails::getDate).reversed());
        return this.historyDetailsList;
    }

    public List<EquipmentHistoryDetails> getHistoryDetailsList() {
        return historyDetailsList;
    }
}
