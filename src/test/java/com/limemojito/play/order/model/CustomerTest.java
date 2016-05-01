/*
 * Copyright (c) Lime Mojito Pty Ltd 2011-2016
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other 
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.limemojito.play.order.model;

import org.junit.Test;

import static com.limemojito.play.order.util.ModelAsserter.assertModelBehaviour;
import static java.time.LocalDate.of;
import static java.time.Month.FEBRUARY;

public class CustomerTest {

    @Test
    public void shouldBeASimpleModel() throws Exception {
        // ok we're keeping it a litte simple here and using first name last name as equality.
        Customer customer = new Customer("Mary", "Smith", true, false, of(2014, FEBRUARY, 22));
        Customer duplicate = new Customer("Mary", "Smith", true, false, of(2014, FEBRUARY, 22));
        Customer other = new Customer("Jane", "Smith", true, false, of(2014, FEBRUARY, 22));
        assertModelBehaviour(customer, duplicate, other);
    }
}
