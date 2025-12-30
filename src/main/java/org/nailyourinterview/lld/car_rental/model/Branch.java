package org.nailyourinterview.lld.car_rental.model;

import lombok.Getter;
import lombok.Setter;
import org.nailyourinterview.lld.car_rental.enums.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Branch {
    private String id;
    private String city;

    private Map<VehicleType, List<Vehicle>> vehicles = new HashMap<>();

    public Branch(String id, String city){
        this.id = id;
        this.city = city;
    }

    public List<Vehicle> getVehiclesByType(VehicleType type) {
        return vehicles.getOrDefault(type, new ArrayList<>());
    }

    public void addVehicle(Vehicle vehicle){
        vehicles.computeIfAbsent(vehicle.getType(), k -> new ArrayList<>()).add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle){
        List<Vehicle> list = vehicles.get(vehicle.getType());
        if(list != null) list.remove(vehicle);
    }
}