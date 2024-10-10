package Controller;

import Dto.IssueTicketRequestDto;
import Dto.IssueTicketResponseDto;

import Dto.ResponseStatus;
import Model.Ticket;
import Service.TicketService;
import exception.InvalidRequestDataException;

public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public IssueTicketResponseDto getTicket(IssueTicketRequestDto issueTicketRequestDto){
        IssueTicketResponseDto issueTicketResponseDto = new IssueTicketResponseDto();
        Ticket ticket;
        try{
            if(issueTicketRequestDto.getGateId() == 0 ||
                    issueTicketRequestDto.getVehicleType() == null ||
                    issueTicketRequestDto.getVehicleNumber() == null )
                throw new InvalidRequestDataException("Ticket generation request data is invalid");
            ticket = ticketService.getTicket(issueTicketRequestDto.getVehicleType(),
                    issueTicketRequestDto.getVehicleNumber(),
                    issueTicketRequestDto.getVehicleColor(),
                    issueTicketRequestDto.getVehicleMake(),
                    issueTicketRequestDto.getGateId());
           // issueTicketResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
            issueTicketResponseDto.getResponseStatus(ResponseStatus.SUCCESS);
            issueTicketResponseDto.setTicket(ticket);
        } catch (Exception e ){
            issueTicketResponseDto.setResponseStatus(ResponseStatus.FAILURE);
            issueTicketResponseDto.setFailureReason(e.getMessage());
        }

        return issueTicketResponseDto;
    }
}
