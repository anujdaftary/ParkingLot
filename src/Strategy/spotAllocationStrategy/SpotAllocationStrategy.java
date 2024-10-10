package Strategy.spotAllocationStrategy;

import Model.Gate;
import Model.ParkingSpot;
import Model.VehicleType;

public interface SpotAllocationStrategy {
    ParkingSpot getSpot(VehicleType vehicleType, Gate gate);

}
