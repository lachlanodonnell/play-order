/*
 * Copyright (c) Lime Mojito Pty Ltd 2011-2016
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.limemojito.play.order.service.impl;

import com.limemojito.play.order.model.ShoppingCart;
import com.limemojito.play.order.service.DiscountService;
import org.javamoney.moneta.Money;

import javax.money.MonetaryAmount;
import java.util.List;

public class DiscountServiceImpl implements DiscountService {
    private static final Money ZERO = Money.of(0, "AUD");
    private final List<DiscountRule> discountRules;

    public DiscountServiceImpl(List<DiscountRule> discountRules) {
        this.discountRules = discountRules;
    }

    @Override
    public MonetaryAmount calculateDiscount(ShoppingCart cart) {
        MonetaryAmount totalDiscount = ZERO;
        for (DiscountRule discountRule : discountRules) {
            if (discountRule.applies(cart)) {
                totalDiscount = discountRule.calculate(cart);
            }
        }

        return totalDiscount;
    }
}
