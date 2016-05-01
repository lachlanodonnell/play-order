/*
 * Copyright (c) Lime Mojito Pty Ltd 2011-2016
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.limemojito.play.order.service.impl;

import com.limemojito.play.order.model.ShoppingCart;

import javax.money.MonetaryAmount;

public abstract class DiscountRule {
    public abstract boolean applies(ShoppingCart cart);

    public MonetaryAmount calculate(ShoppingCart cart) {
        return performMatchingCalculate(cart);
    }

    protected abstract MonetaryAmount performMatchingCalculate(ShoppingCart cart);
}
