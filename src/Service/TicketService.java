package Service;

import Model.*;
import Repository.GateRepository;
import Repository.ParkingLotRepository;
import Repository.TicketRepository;
import Strategy.spotAllocationStrategy.SpotAllocationFactory;
import Strategy.spotAllocationStrategy.SpotAllocationStrategy;

import java.time.LocalDateTime;

public class TicketService {
    private TicketRepository ticketRepository;
    private ParkingLotRepository parkingLotRepository;
    private GateRepository gateRepository;

    public TicketService(TicketRepository ticketRepository, ParkingLotRepository parkingLotRepository, GateRepository gateRepository) {
        this.ticketRepository = ticketRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.gateRepository = gateRepository;
    }

    public Ticket getTicket(VehicleType vehicleType, String vehicleNumber, String vehicleColor, String vehicleMake, int gateId){
        Gate gate = gateRepository.get(gateId);
        SpotAllocationStrategy spotAllocationStrategy = SpotAllocationFactory.getSpotAllocationStrategy(parkingLotRepository);
        ParkingSpot parkingSpot = spotAllocationStrategy.getSpot(vehicleType, gate);
        parkingSpot.setStatus(Status.NOT_AVAILABLE);

        Ticket ticket = new Ticket();
        ticket.setVehicle(new Vehicle(vehicleNumber, vehicleColor, vehicleMake, vehicleType));
        ticket.setEntryTime(LocalDateTime.now());
        ticket.setParkingSpot(parkingSpot);
        return ticket;
    }
}
