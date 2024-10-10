package Strategy.feeCalculationStrategy;

import Model.Ticket;

public interface FeeCalculationStrategy {
    long getFeeAmount(Ticket ticket);
}
