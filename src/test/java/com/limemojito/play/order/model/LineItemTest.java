/*
 * Copyright (c) Lime Mojito Pty Ltd 2011-2016
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.limemojito.play.order.model;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static com.limemojito.play.order.util.ModelAsserter.assertModelBehaviour;

public class LineItemTest {

    private static final Currency AUD = Currency.getInstance("AUD");

    @Test
    public void shouldBeASimpleModel() throws Exception {
        LineItem lineItem = new LineItem(InventoryCategory.GROCERIES, 12, new Money(AUD, new BigDecimal(12.50)));
        LineItem duplicate = new LineItem(InventoryCategory.GROCERIES, 12, new Money(AUD, new BigDecimal(12.50)));
        LineItem other = new LineItem(InventoryCategory.FURNITURE, 1, new Money(AUD, new BigDecimal(56.50)));
        assertModelBehaviour(lineItem, duplicate, other);
    }
}
