/*
 * Copyright (c) Lime Mojito Pty Ltd 2011-2016
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.limemojito.play.order.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

public class LineItem {
    private final InventoryCategory category;
    private final double quantity;
    private final Money unitCost;

    public LineItem(InventoryCategory category, double quantity, Money unitCost) {
        this.category = category;
        this.quantity = quantity;
        this.unitCost = unitCost;
    }

    public InventoryCategory getCategory() {
        return category;
    }

    public double getQuantity() {
        return quantity;
    }

    public Money getUnitCost() {
        return unitCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LineItem)) {
            return false;
        }
        LineItem lineItem = (LineItem) o;
        return Double.compare(lineItem.quantity, quantity) == 0
                && category == lineItem.category
                && Objects.equals(unitCost, lineItem.unitCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, quantity, unitCost);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("category", category)
                .append("quantity", quantity)
                .append("unitCost", unitCost)
                .toString();
    }


}
