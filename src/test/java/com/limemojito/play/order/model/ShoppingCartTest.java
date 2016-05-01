/*
 * Copyright (c) Lime Mojito Pty Ltd 2011-2016
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.limemojito.play.order.model;

import org.javamoney.moneta.Money;
import org.junit.Test;

import static com.limemojito.play.order.model.InventoryCategory.FURNITURE;
import static com.limemojito.play.order.util.ModelAsserter.assertModelBehaviour;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ShoppingCartTest extends UnitTest {

    @Test
    public void shouldBeASimpleModel() throws Exception {
        ShoppingCart cart = new ShoppingCart(pojoFactory.createSimpleCustomer());
        ShoppingCart duplicate = new ShoppingCart(pojoFactory.createSimpleCustomer());
        ShoppingCart other = new ShoppingCart(new Customer("Bob", "Smith", true, false, null));
        assertModelBehaviour(cart, duplicate, other);
    }

    @Test
    public void shouldComputeTotal() throws Exception {
        ShoppingCart cart = new ShoppingCart(pojoFactory.createSimpleCustomer());

        cart.add(pojoFactory.createLineItemAud(FURNITURE, 1, 56.78));

        assertThat(cart.getTotal(), is(Money.of(56.78, "AUD")));
    }
}
