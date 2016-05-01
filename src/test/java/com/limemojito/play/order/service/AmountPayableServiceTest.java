/*
 * Copyright (c) Lime Mojito Pty Ltd 2011-2016
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.limemojito.play.order.service;

import com.limemojito.play.order.model.Customer;
import com.limemojito.play.order.model.InventoryCategory;
import com.limemojito.play.order.model.ShoppingCart;
import com.limemojito.play.order.model.UnitTest;
import com.limemojito.play.order.service.impl.AmountPayableServiceImpl;
import com.limemojito.play.order.service.impl.discount.Affiliate;
import com.limemojito.play.order.service.impl.discount.DollarsSpent;
import com.limemojito.play.order.service.impl.discount.Employee;
import com.limemojito.play.order.service.impl.discount.LongTermCustomer;
import org.hamcrest.Matcher;
import org.junit.Test;

import javax.money.MonetaryAmount;
import java.time.LocalDate;

import static com.limemojito.play.order.model.InventoryCategory.FURNITURE;
import static com.limemojito.play.order.model.InventoryCategory.GROCERIES;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.javamoney.moneta.Money.of;
import static org.junit.Assert.assertThat;

public class AmountPayableServiceTest extends UnitTest {
    private static final Customer NO_DISCOUNT_CUTOMER = new Customer("Bob", "Smith", false, false, null);
    private static final LocalDate FIRST_SHOP_LONG_TIME_AGO = LocalDate.now().minusYears(3);
    private final AmountPayableService service = new AmountPayableServiceImpl(asList(new Employee(),
                                                                                     new Affiliate(),
                                                                                     new LongTermCustomer(),
                                                                                     new DollarsSpent()));

    @Test
    public void shouldApplyNoDiscounts() throws Exception {
        ShoppingCart cart = new ShoppingCart(NO_DISCOUNT_CUTOMER);
        addDiscountableItemsWorthFifty(cart);

        assertThat(service.calculateNetPayable(cart), isAUD(50));
    }

    @Test
    public void shouldComputeAThirtyPercentDiscountForEmployee() throws Exception {
        ShoppingCart cart = new ShoppingCart(new Customer("Bob", "Smith", true, false, null));
        addDiscountableItemsWorthFifty(cart);

        assertThat(service.calculateNetPayable(cart), isAUD(35.0));
    }

    @Test
    public void shouldComputeAFivePercentDiscountForAffiliate() throws Exception {
        ShoppingCart cart = new ShoppingCart(new Customer("Bob", "Smith", false, true, null));
        addDiscountableItemsWorthFifty(cart);

        assertThat(service.calculateNetPayable(cart), isAUD(45.0));
    }

    @Test
    public void shouldComputeAFivePercentForLongTermCustomer() throws Exception {
        ShoppingCart cart = new ShoppingCart(new Customer("Bob", "Smith", false, false, FIRST_SHOP_LONG_TIME_AGO));
        addDiscountableItemsWorthFifty(cart);

        assertThat(service.calculateNetPayable(cart), isAUD(47.5));
    }

    @Test
    public void shouldComputeTenDollarDiscountForTwoHundredSpent() throws Exception {
        ShoppingCart cart = new ShoppingCart(NO_DISCOUNT_CUTOMER);
        addItemsThatSumToOneHundred(cart);
        addItemsThatSumToOneHundred(cart);

        assertThat(service.calculateNetPayable(cart), isAUD(190.0));
    }

    @Test
    public void shouldOnlyApplyToNonGroceryForDiscountRules() throws Exception {
        ShoppingCart cart = new ShoppingCart(new Customer("Bob", "Smith", true, false, null));
        addNonDiscountableItemsWorthFifty(cart);

        assertThat(service.calculateNetPayable(cart), isAUD(50.0));
    }

    @Test
    public void shouldOnlyApplyOnePercentageRule() throws Exception {
        ShoppingCart cart = new ShoppingCart(new Customer("Bob", "Smith", true, true, FIRST_SHOP_LONG_TIME_AGO));
        addDiscountableItemsWorthFifty(cart);

        assertThat(service.calculateNetPayable(cart), isAUD(35.0));
    }

    private void addItemsThatSumToOneHundred(ShoppingCart cart) {
        MonetaryAmount before = cart.getGrossTotal();
        addDiscountableItemsWorthFifty(cart);
        addNonDiscountableItemsWorthFifty(cart);
        assertTotalUpdatedTo(cart, before, 100);
    }

    private void addNonDiscountableItemsWorthFifty(ShoppingCart cart) {
        addItemsToTheValueOfFifty(cart, GROCERIES);
    }

    private void addDiscountableItemsWorthFifty(ShoppingCart cart) {
        addItemsToTheValueOfFifty(cart, FURNITURE);
    }

    private void addItemsToTheValueOfFifty(ShoppingCart cart, InventoryCategory category) {
        MonetaryAmount before = cart.getGrossTotal();
        cart.add(pojoFactory.createLineItemAud(category, 1, 28.39));
        cart.add(pojoFactory.createLineItemAud(category, 1, 21.61));
        assertTotalUpdatedTo(cart, before, 50);
    }

    private void assertTotalUpdatedTo(ShoppingCart cart, MonetaryAmount before, double updatedAmount) {
        assertThat(cart.getGrossTotal(), is(before.add(of(updatedAmount, before.getCurrency()))));
    }

    private Matcher<MonetaryAmount> isAUD(double audValue) {
        return is(of(audValue, "AUD"));
    }
}
