/*
 * Copyright (c) Lime Mojito Pty Ltd 2011-2016
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.limemojito.play.order.model;

import static com.limemojito.play.order.model.InventoryCategory.GROCERIES;
import static java.time.LocalDate.of;
import static java.time.Month.FEBRUARY;
import static org.javamoney.moneta.Money.of;

public class PojoFactory {
    public Customer createSimpleCustomer() {
        return new Customer("Mary", "Smith", false, false, of(2014, FEBRUARY, 22));
    }

    public LineItem createLineItemAud(InventoryCategory category, double quantity, double currencyAmount) {
        return new LineItem(GROCERIES, quantity, of(currencyAmount, "AUD"));
    }
}
