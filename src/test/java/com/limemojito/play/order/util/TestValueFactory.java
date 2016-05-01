/*
 * Copyright (c) Lime Mojito Pty Ltd 2011-2016
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.limemojito.play.order.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * Creates a test value for a given class.
 */
public class TestValueFactory {

    private final Map<Class<?>, Object> instanceStore;

    public void registerTestInstanceFor(Class<?> clazz, Object instance) {
        instanceStore.put(clazz, instance);
    }

    /**
     * Creates a test value for a supplied type. All primitives and boxes are supported, unknown classes
     * are instantiated via a call to newInstance().
     *
     * @param type Type of class to create test value for.
     * @return a value for the supplied type.
     * @see Class#newInstance()
     */
    public Object createFor(Class<?> type) {
        Object rv = null;
        if (type.isEnum()) {
            rv = type.getEnumConstants()[0];
        }
        if (rv == null) {
            rv = instanceStore.get(type);
        }
        if (rv == null) {
            // fallback on a create if we have no intercepts.
            rv = instatiateClass(type);
        }
        return rv;
    }

    public TestValueFactory() {
        instanceStore = new HashMap<>();
        registerTestInstanceFor(UUID.class, UUID.randomUUID());
        registerTestInstanceFor(BigDecimal.class, new BigDecimal("99.999"));
        registerTestInstanceFor(BigInteger.class, new BigInteger("99"));
        registerCollections();
        registerPrimitives();
    }

    private void registerCollections() {
        registerTestInstanceFor(Set.class, new TreeSet<>());
        registerTestInstanceFor(Collection.class, new ArrayList<>());
        registerTestInstanceFor(List.class, new ArrayList<>());
        registerTestInstanceFor(Iterable.class, new ArrayList<>());
        registerTestInstanceFor(Map.class, new HashMap<>());
    }

    private void registerPrimitives() {
        registerTestInstanceFor(Integer.class, Integer.MAX_VALUE);
        registerTestInstanceFor(int.class, Integer.MAX_VALUE);
        registerTestInstanceFor(Byte.class, Byte.MAX_VALUE);
        registerTestInstanceFor(byte.class, Byte.MAX_VALUE);
        registerTestInstanceFor(Short.class, Short.MAX_VALUE);
        registerTestInstanceFor(short.class, Short.MAX_VALUE);
        registerTestInstanceFor(Long.class, Long.MAX_VALUE);
        registerTestInstanceFor(long.class, Long.MAX_VALUE);
        registerTestInstanceFor(Float.class, Float.MAX_VALUE);
        registerTestInstanceFor(float.class, Float.MAX_VALUE);
        registerTestInstanceFor(Double.class, Double.MAX_VALUE);
        registerTestInstanceFor(double.class, Double.MAX_VALUE);
        registerTestInstanceFor(Boolean.class, Boolean.TRUE);
        registerTestInstanceFor(boolean.class, Boolean.TRUE);
    }

    private Object instatiateClass(Class<?> type) {
        try {
            return type.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Could not create value for class " + type.getName(), e);
        }
    }
}
