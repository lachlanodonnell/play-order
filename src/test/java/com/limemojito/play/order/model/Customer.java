/*
 * Copyright (c) Lime Mojito Pty Ltd 2011-2016
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.limemojito.play.order.model;

import java.time.LocalDate;

public class Customer {
    private final String firstName;
    private final boolean employee;
    private final boolean affiliate;
    private final LocalDate firstShopDate;
    private final String lastName;

    public Customer(String firstName, String lastName, boolean employee, boolean affiliate, LocalDate firstShopDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employee = employee;
        this.affiliate = affiliate;
        this.firstShopDate = firstShopDate;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public boolean isAffiliate() {
        return affiliate;
    }

    public LocalDate getFirstShopDate() {
        return firstShopDate;
    }

    public boolean isEmployee() {
        return employee;
    }
}
