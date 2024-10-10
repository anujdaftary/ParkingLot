package Strategy.spotAllocationStrategy;

import Repository.ParkingLotRepository;

public class SpotAllocationFactory  {
    public static SpotAllocationStrategy getSpotAllocationStrategy(ParkingLotRepository parkingLotRepository){
        return new RandomSpotAllocationStrategy(parkingLotRepository);
    }
}
