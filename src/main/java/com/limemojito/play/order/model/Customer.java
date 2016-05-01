/*
 * Copyright (c) Lime Mojito Pty Ltd 2011-2016
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.limemojito.play.order.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Customer)) {
            return false;
        }
        Customer customer = (Customer) o;
        return employee == customer.employee
                && affiliate == customer.affiliate
                && Objects.equals(firstName, customer.firstName)
                && Objects.equals(firstShopDate, customer.firstShopDate)
                && Objects.equals(lastName, customer.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, employee, affiliate, firstShopDate, lastName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("employee", employee)
                .append("affiliate", affiliate)
                .append("firstShopDate", firstShopDate)
                .toString();
    }

}
