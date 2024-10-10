import Controller.TicketController;
import Dto.IssueTicketRequestDto;
import Dto.IssueTicketResponseDto;
import Model.ParkingLot;
import Model.VehicleType;
import Repository.*;
import Service.InitialisationService;
import Service.TicketService;

public class Main {
    private InitialisationService initialisationService;
    private TicketController ticketController;

    public Main() {
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
        TicketRepository ticketRepository = new TicketRepository();
        GateRepository gateRepository = new GateRepository();
        ParkingFloorRepository parkingFloorRepository = new ParkingFloorRepository();
        ParkingSpotRepository parkingSpotRepository = new ParkingSpotRepository();

        this.initialisationService = new InitialisationService(gateRepository, parkingFloorRepository, parkingSpotRepository, parkingLotRepository);
        this.ticketController = new TicketController(new TicketService(ticketRepository, parkingLotRepository, gateRepository));
    }
    public static void main(String[] args) {

        Main main = new Main();
        ParkingLot parkingLot = main.initialisationService.initialise();
        IssueTicketRequestDto issueTicketRequestDTO =
                new IssueTicketRequestDto(VehicleType.Four_Wheeler, "ABCD1234", "Black", "Mercedez", 1);
        IssueTicketResponseDto responseDto = main.ticketController.getTicket(issueTicketRequestDTO);
        System.out.println(responseDto);

    }
}