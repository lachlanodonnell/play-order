/*
 * Copyright (c) Lime Mojito Pty Ltd 2011-2016
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.limemojito.play.order.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.javamoney.moneta.Money;

import javax.money.MonetaryAmount;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static java.util.Collections.unmodifiableList;

/**
 * Assuming one shopping cart per customer
 */
public class ShoppingCart {
    private static final MonetaryAmount ZERO = Money.of(0, "AUD");
    private final Customer customer;
    private final List<LineItem> lineItems;

    public ShoppingCart(Customer customer) {
        this.customer = customer;
        this.lineItems = new LinkedList<>();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void add(LineItem lineItem) {
        lineItems.add(lineItem);
    }


    public MonetaryAmount getGrossTotal() {
        MonetaryAmount total = ZERO;
        for (LineItem lineItem : lineItems) {
            total = total.add(lineItem.getTotal());
        }
        return total;
    }

    /**
     * Line items represented by the shopping cart.  They should not be altered except by calling methods on the cart.
     *
     * @return An immutable set of line items.  Only interact with line items via the ShoppingCart class.
     */
    public List<LineItem> getLineItems() {
        return unmodifiableList(lineItems);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShoppingCart)) {
            return false;
        }
        ShoppingCart that = (ShoppingCart) o;
        return Objects.equals(customer, that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("customer", customer)
                .toString();
    }

}
