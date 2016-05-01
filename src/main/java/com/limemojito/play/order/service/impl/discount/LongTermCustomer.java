/*
 * Copyright (c) Lime Mojito Pty Ltd 2011-2016
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.limemojito.play.order.service.impl.discount;

import com.limemojito.play.order.model.ShoppingCart;

import java.time.LocalDate;

public class LongTermCustomer extends PercentDiscountRule {

    private static final double DISCOUNT_RATE = 0.05;

    public LongTermCustomer() {
        super(DISCOUNT_RATE);
    }

    @Override
    public boolean applies(ShoppingCart cart) {
        final LocalDate twoYearsAgo = LocalDate.now().minusYears(2);
        final LocalDate firstShopDate = cart.getCustomer().getFirstShopDate();
        return firstShopDate != null && twoYearsAgo.isAfter(firstShopDate);
    }
}
