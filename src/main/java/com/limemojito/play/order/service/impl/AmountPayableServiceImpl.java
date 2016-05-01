/*
 * Copyright (c) Lime Mojito Pty Ltd 2011-2016
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.limemojito.play.order.service.impl;

import com.limemojito.play.order.model.ShoppingCart;
import com.limemojito.play.order.service.AmountPayableService;
import com.limemojito.play.order.service.impl.discount.PercentDiscountRule;
import org.javamoney.moneta.Money;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.money.MonetaryAmount;
import java.util.List;

public class AmountPayableServiceImpl implements AmountPayableService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AmountPayableServiceImpl.class);
    private static final Money ZERO = Money.of(0, "AUD");
    private final List<DiscountRule> discountRules;

    public AmountPayableServiceImpl(List<DiscountRule> discountRules) {
        this.discountRules = discountRules;
    }

    @Override
    public MonetaryAmount calculateNetPayable(ShoppingCart cart) {
        LOGGER.info("Calculating net payable for {}", cart);
        final MonetaryAmount netPayable = cart.getGrossTotal().subtract(calculateDiscount(cart));
        LOGGER.info("Net Payable is {}", netPayable);
        return netPayable;
    }

    private MonetaryAmount calculateDiscount(ShoppingCart cart) {
        LOGGER.info("Calculating Discount");
        MonetaryAmount totalDiscount = ZERO;
        boolean percentRuleApplied = false;
        for (DiscountRule discountRule : discountRules) {
            if (canApplyRule(cart, percentRuleApplied, discountRule)) {
                LOGGER.info("Applying {}", discountRule.getClass().getSimpleName());
                final MonetaryAmount calculate = discountRule.calculate(cart);
                LOGGER.debug("Discount is {}", calculate);
                totalDiscount = totalDiscount.add(calculate);
                if (discountRule instanceof PercentDiscountRule) {
                    percentRuleApplied = true;
                    LOGGER.debug("A Percent discount has been applied.");
                }
            }
        }
        LOGGER.info("Total Discount is {}", totalDiscount);
        return totalDiscount;
    }

    private boolean canApplyRule(ShoppingCart cart, boolean percentRuleApplied, DiscountRule discountRule) {
        final boolean percentDiscount = discountRule instanceof PercentDiscountRule;
        if (percentDiscount) {
            return !percentRuleApplied && discountRule.applies(cart);
        } else {
            return discountRule.applies(cart);
        }
    }
}
