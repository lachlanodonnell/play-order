/*
 * Copyright (c) Lime Mojito Pty Ltd 2011-2016
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.limemojito.play.order.util;

import org.hamcrest.core.Is;

import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertFalse;

public class CanonicalAsserter {
    /**
     * Assert equals, hashCode and toString.
     *
     * @param instance  Object to test
     * @param duplicate a duplicate of the test object (same id expected).
     * @param other     a "different" object.
     */
    public static void assertCanonical(Object instance, Object duplicate, Object other) {
        assertToString(instance);

        assertThat(instance, not(Is.is(nullValue())));
        assertThat(duplicate, not(Is.is(nullValue())));
        assertThat(other, not(Is.is(nullValue())));

        assertThat(instance, Is.is(instance));
        assertThat(instance, Is.is(duplicate));
        assertThat(instance.equals(other), Is.is(false));
        assertThat(instance.hashCode(), Is.is(duplicate.hashCode()));
    }

    private static void assertToString(Object instance) {
        final String defaultToString = instance.getClass().getName() + "@[0-9a-fA-F]*";
        final String foundToString = instance.toString();
        final boolean matches = Pattern.matches(defaultToString, foundToString);
        assertFalse(String.format("Default toString detected [%s]", foundToString), matches);
    }
}
