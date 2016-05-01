/*
 * Copyright (c) Lime Mojito Pty Ltd 2011-2016
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.limemojito.play.order.service.impl.discount;

import com.limemojito.play.order.model.ShoppingCart;
import com.limemojito.play.order.service.impl.DiscountRule;
import org.javamoney.moneta.Money;

import javax.money.MonetaryAmount;

public class DollarsSpent extends DiscountRule {

    @Override
    public boolean applies(ShoppingCart cart) {
        return true;
    }

    @Override
    protected MonetaryAmount performMatchingCalculate(ShoppingCart cart) {
        final MonetaryAmount total = cart.getGrossTotal();
        final int fiveDollarDiscounts = total.getNumber().intValueExact() / 100;
        return Money.of(fiveDollarDiscounts * 5, total.getCurrency());
    }
}
