package aut.utcluj.isp.ex3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author stefan
 */
public class EquipmentController {

    private List<Equipment> equipmentList;

    public EquipmentController() {
        this.equipmentList = new ArrayList<>();
    }

    /**
     * Add new equipment to the list of equipments
     *
     * @param equipment - equipment to be added
     */
    public void addEquipment(final Equipment equipment) {
        this.equipmentList.add(equipment);
    }

    /**
     * Get current list of equipments
     *
     * @return list of equipments
     */
    public List<Equipment> getEquipments() {
        return this.equipmentList;
    }

    /**
     * Get number of equipments
     *
     * @return number of equipments
     */
    public int getNumberOfEquipments() {
        return this.equipmentList.size();
    }

    /**
     * Group equipments by owner
     *
     * @return a dictionary where the key is the owner and value is represented by list of equipments he owns
     */
    public Map<String, List<Equipment>> getEquipmentsGroupedByOwner() {
        Map<String, List<Equipment>> equipmentByOwner = new HashMap<>();
        for (Equipment equipment : this.equipmentList) {
            String owner = equipment.getOwner();
            if (equipmentByOwner.containsKey(owner)) {
                equipmentByOwner.get(owner).add(equipment);
            } else {
                List<Equipment> equipments = new ArrayList<>();
                equipments.add(equipment);
                equipmentByOwner.put(owner, equipments);
            }
        }
        return equipmentByOwner;
    }

    /**
     * Remove a particular equipment from equipments list by serial number
     * @param serialNumber - unique serial number
     * @return deleted equipment instance or null if not found
     */
    public Equipment removeEquipmentBySerialNumber(final String serialNumber) {
        for (Equipment equipment : equipmentList) {
            if (equipment.getSerialNumber().equals(serialNumber)) {
                equipmentList.remove(equipment);
                return equipment;
            }
        }
        return null;
    }
}
