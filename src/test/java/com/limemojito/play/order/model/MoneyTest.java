/*
 * Copyright (c) Lime Mojito Pty Ltd 2011-2016
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.limemojito.play.order.model;

import org.junit.Test;

import java.util.Currency;

import static com.limemojito.play.order.util.ModelAsserter.assertModelBehaviour;
import static java.math.BigDecimal.valueOf;

public class MoneyTest {

    private static final Currency AUD = Currency.getInstance("AUD");

    @Test
    public void shouldBeASimpleModel() throws Exception {
        Money money = new Money(AUD, valueOf(23.0));
        Money duplicate = new Money(AUD, valueOf(23.0));
        Money other = new Money(Currency.getInstance("USD"), valueOf(23.0));
        assertModelBehaviour(money, duplicate, other);
    }
}
