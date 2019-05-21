package practice3;

import java.math.BigDecimal;
import java.util.List;

public class PriceCaculator {
    private List<OrderLineItem>orderLineItemList;
    private List<BigDecimal>discounts;
    private BigDecimal tax;

    public PriceCaculator(Order order){
        this.orderLineItemList = order.getOrderLineItemList();
        this.discounts = order.getDiscounts();
        this.tax = order.getTax();
    }
    public BigDecimal calculate(){
        BigDecimal subTotal = new BigDecimal(0);
        subTotal = calculateOrderItem(subTotal);
        subTotal = calculateDiscount(subTotal);
        BigDecimal tax = subTotal.multiply(this.tax);

        return subTotal.add(tax);
    }
    private BigDecimal calculateDiscount(BigDecimal subTotal){
        for (BigDecimal discount : discounts) {
            subTotal = subTotal.subtract(discount);
        }
        return subTotal;
    }
    private BigDecimal calculateOrderItem(BigDecimal subTotal) {
        for (OrderLineItem lineItem : orderLineItemList) {
            subTotal = subTotal.add(lineItem.getPrice());
        }
        return subTotal;
    }
}
