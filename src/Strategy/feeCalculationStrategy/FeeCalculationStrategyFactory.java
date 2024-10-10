package Strategy.feeCalculationStrategy;

public class FeeCalculationStrategyFactory {
    public static FeeCalculationStrategy getFeeCalculationStrategy(){
        return new LowFeeCalculationStrategy();
    }
}
