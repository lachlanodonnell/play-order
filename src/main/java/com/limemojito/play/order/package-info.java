/*
 * Copyright (c) Lime Mojito Pty Ltd 2011-2016
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

/**
 * A simple net payment calculator that can apply a series of rule based discounts to a service.  Entry point is the AmountPayableServiceImpl.
 * The design is an immuatable model(Shopping Cart) that can be applied to a service (AmountPayableService).  Expectations are a web application
 * that would be deployed in spring or similar.
 * <p>
 * <ol>
 * <li>If the user is an employee of the store, he gets a 30% discount.</li>
 * <li>If the user is an affiliate of the store, he gets a 10% discount.</li>
 * <li>If the user has been a customer for over 2 years, he gets a 5% discount.</li>
 * <li>For every $100 on the bill, there would be a $ 5 discount (e.g. for $ 990, you get $ 45 as a discount).</li>
 * <li>The percentage based discounts do not apply on groceries.</li>
 * <li>A user can get only one of the percentage based discounts on a bill.</li>
 * </ol>
 */
package com.limemojito.play.order;
