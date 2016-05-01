/*
 * Copyright (c) Lime Mojito Pty Ltd 2011-2016
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.limemojito.play.order.model;

import org.junit.Test;

import static com.limemojito.play.order.model.InventoryCategory.FURNITURE;
import static com.limemojito.play.order.model.InventoryCategory.GROCERIES;
import static com.limemojito.play.order.util.ModelAsserter.assertModelBehaviour;
import static org.javamoney.moneta.Money.of;

public class LineItemTest extends UnitTest {

    @Test
    public void shouldBeASimpleModel() throws Exception {
        LineItem lineItem = pojoFactory.createLineItemAud(GROCERIES, 12, 12.5);;
        LineItem duplicate = new LineItem(GROCERIES, 12, of(12.50, "AUD"));
        LineItem other = new LineItem(FURNITURE, 1, of(33.50, "AUD"));
        assertModelBehaviour(lineItem, duplicate, other);
    }
}
