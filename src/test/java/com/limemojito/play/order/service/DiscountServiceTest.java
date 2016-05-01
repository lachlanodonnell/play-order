/*
 * Copyright (c) Lime Mojito Pty Ltd 2011-2016
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.limemojito.play.order.service;

import com.limemojito.play.order.model.Customer;
import com.limemojito.play.order.model.ShoppingCart;
import com.limemojito.play.order.model.UnitTest;
import com.limemojito.play.order.service.impl.DiscountServiceImpl;
import com.limemojito.play.order.service.impl.discount.Affiliate;
import com.limemojito.play.order.service.impl.discount.DollarsSpent;
import com.limemojito.play.order.service.impl.discount.Employee;
import com.limemojito.play.order.service.impl.discount.LongTermCustomer;
import org.junit.Test;

import javax.money.MonetaryAmount;
import java.time.LocalDate;

import static com.limemojito.play.order.model.InventoryCategory.FURNITURE;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.javamoney.moneta.Money.of;
import static org.junit.Assert.assertThat;

public class DiscountServiceTest extends UnitTest {
    private static final Customer NO_DISCOUNT_CUTOMER = new Customer("Bob", "Smith", false, false, null);
    private final DiscountService service = new DiscountServiceImpl(asList(new Employee(),
                                                                           new Affiliate(),
                                                                           new LongTermCustomer(),
                                                                           new DollarsSpent()));

    @Test
    public void shouldApplyNoDiscounts() throws Exception {
        ShoppingCart cart = new ShoppingCart(NO_DISCOUNT_CUTOMER);
        addItemsThatSumToFifty(cart);

        performDiscount(cart, 0);
    }

    @Test
    public void shouldComputeAThirtyPercentDiscountForEmployee() throws Exception {
        ShoppingCart cart = new ShoppingCart(new Customer("Bob", "Smith", true, false, null));
        addItemsThatSumToFifty(cart);

        performDiscount(cart, 15.0);
    }

    @Test
    public void shouldComputeAFivePercentDiscountForAffiliate() throws Exception {
        ShoppingCart cart = new ShoppingCart(new Customer("Bob", "Smith", false, true, null));
        addItemsThatSumToFifty(cart);

        performDiscount(cart, 5.0);
    }

    @Test
    public void shouldComputeAFivePercentForLongTermCustomer() throws Exception {
        ShoppingCart cart = new ShoppingCart(new Customer("Bob", "Smith", false, false, LocalDate.now().minusYears(3)));
        addItemsThatSumToFifty(cart);

        performDiscount(cart, 2.5);
    }

    @Test
    public void shouldComputeTenDollarDiscountForTwoHundredSpent() throws Exception {
        ShoppingCart cart = new ShoppingCart(NO_DISCOUNT_CUTOMER);
        addItemsThatSumToOneHundred(cart);
        addItemsThatSumToOneHundred(cart);

        performDiscount(cart, 10.0);
    }

    private void addItemsThatSumToOneHundred(ShoppingCart cart) {
        MonetaryAmount before = cart.getGrossTotal();
        addItemsThatSumToFifty(cart);
        addItemsThatSumToFifty(cart);
        assertTotalUpdatedTo(cart, before, 100);
    }

    private void performDiscount(ShoppingCart cart, double discountAmount) {
        assertAmountAud(service.calculateDiscount(cart), discountAmount);
    }

    private void assertAmountAud(MonetaryAmount discount, double discountAmount) {
        assertThat(discount, is(of(discountAmount, "AUD")));
    }

    private void addItemsThatSumToFifty(ShoppingCart cart) {
        MonetaryAmount before = cart.getGrossTotal();
        cart.add(pojoFactory.createLineItemAud(FURNITURE, 1, 28.39));
        cart.add(pojoFactory.createLineItemAud(FURNITURE, 1, 21.61));
        assertTotalUpdatedTo(cart, before, 50);
    }

    private void assertTotalUpdatedTo(ShoppingCart cart, MonetaryAmount before, double updatedAmount) {
        assertThat(cart.getGrossTotal(), is(before.add(of(updatedAmount, before.getCurrency()))));
    }

}
