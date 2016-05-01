/*
 * Copyright (c) Lime Mojito Pty Ltd 2011-2016
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other 
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.limemojito.play.order;

import com.limemojito.play.order.model.Customer;
import org.junit.Test;

import static java.time.LocalDate.of;
import static java.time.Month.FEBRUARY;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CustomerTest {

    @Test
    public void shouldBeASimpleModel() throws Exception {
        Customer customer = new Customer("Mary", "Smith", true, false, of(2014, FEBRUARY, 22));
        assertThat(customer.getFirstName(), is("Mary"));
        assertThat(customer.getLastName(), is("Smith"));
        assertThat(customer.isAffiliate(), is(false));
        assertThat(customer.isEmployee(), is(true));
        assertThat(customer.getFirstShopDate(), is(of(2014, FEBRUARY, 22)));
    }
}
