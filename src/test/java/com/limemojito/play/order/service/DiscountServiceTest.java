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
import com.limemojito.play.order.service.impl.discount.Affiliate;
import com.limemojito.play.order.service.impl.DiscountServiceImpl;
import com.limemojito.play.order.service.impl.discount.Employee;
import com.limemojito.play.order.service.impl.discount.LongTermCustomer;
import org.javamoney.moneta.Money;
import org.junit.Test;

import javax.money.MonetaryAmount;

import java.time.LocalDate;

import static com.limemojito.play.order.model.InventoryCategory.FURNITURE;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DiscountServiceTest extends UnitTest {

    private final DiscountService service = new DiscountServiceImpl(asList(new Employee(), new Affiliate(), new LongTermCustomer()));

    @Test
    public void shouldApplyNoDiscounts() throws Exception {
        ShoppingCart cart = new ShoppingCart(new Customer("Bob", "Smith", false, false, null));
        addItemsThatSumToOneHundred(cart);

        performDiscount(cart, 0);
    }

    @Test
    public void shouldComputeAThirtyPercentDiscountForEmployee() throws Exception {
        ShoppingCart cart = new ShoppingCart(new Customer("Bob", "Smith", true, false, null));
        addItemsThatSumToOneHundred(cart);

        performDiscount(cart, 30.0);
    }

    @Test
    public void shouldComputeAFivePercentDiscountForAffiliate() throws Exception {
        ShoppingCart cart = new ShoppingCart(new Customer("Bob", "Smith", false, true, null));
        addItemsThatSumToOneHundred(cart);

        performDiscount(cart, 10.0);
    }

    @Test
    public void shouldComputeAFivePercentForLongTermCustomer() throws Exception {
        ShoppingCart cart = new ShoppingCart(new Customer("Bob", "Smith", false, true, LocalDate.now().minusYears(3)));
        addItemsThatSumToOneHundred(cart);

        performDiscount(cart, 5.0);
    }

    private void performDiscount(ShoppingCart cart, double discountAmount) {
        assertAmountAud(service.calculateDiscount(cart), discountAmount);
    }

    private void assertAmountAud(MonetaryAmount discount, double discountAmount) {
        assertThat(discount, is(Money.of(discountAmount, "AUD")));
    }

    private void addItemsThatSumToOneHundred(ShoppingCart cart) {
        cart.add(pojoFactory.createLineItemAud(FURNITURE, 1, 56.78));
        cart.add(pojoFactory.createLineItemAud(FURNITURE, 2, 21.61));
        assertAmountAud(cart.getGrossTotal(), 100.00);
    }

}
