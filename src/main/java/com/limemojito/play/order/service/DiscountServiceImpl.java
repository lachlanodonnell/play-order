/*
 * Copyright (c) Lime Mojito Pty Ltd 2011-2016
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.limemojito.play.order.service;

import com.limemojito.play.order.model.ShoppingCart;
import org.javamoney.moneta.Money;

import javax.money.MonetaryAmount;

public class DiscountServiceImpl implements DiscountService {

    private static final double THRITY_PERCENT_DISCOUNT = 0.3;

    @Override
    public MonetaryAmount calculateDiscount(ShoppingCart cart) {
        MonetaryAmount totalDiscount = Money.of(0, "AUD");

        if (cart.getCustomer().isEmployee()) {
            totalDiscount = cart.getGrossTotal().multiply(THRITY_PERCENT_DISCOUNT);
        }

        return totalDiscount;
    }
}
