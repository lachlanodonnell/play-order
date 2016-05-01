/*
 * Copyright (c) Lime Mojito Pty Ltd 2011-2016
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.limemojito.play.order.service.impl.discount;

import com.limemojito.play.order.model.LineItem;
import com.limemojito.play.order.model.ShoppingCart;
import com.limemojito.play.order.service.impl.DiscountRule;
import org.javamoney.moneta.Money;

import javax.money.MonetaryAmount;
import java.util.List;

public abstract class PercentDiscountRule implements DiscountRule {
    private static final Money ZERO = Money.of(0, "AUD");
    private final double discountRate;

    public PercentDiscountRule(double discountRate) {
        this.discountRate = discountRate;
    }

    @Override
    public MonetaryAmount calculate(ShoppingCart cart) {
        final List<LineItem> lineItems = cart.getLineItems();
        MonetaryAmount totalDiscountableAmount = ZERO;
        for (LineItem lineItem : lineItems) {
            if (lineItem.getCategory().isDiscountApplicable()) {
                totalDiscountableAmount = totalDiscountableAmount.add(lineItem.getTotal());
            }
        }

        return totalDiscountableAmount.multiply(discountRate);
    }
}
