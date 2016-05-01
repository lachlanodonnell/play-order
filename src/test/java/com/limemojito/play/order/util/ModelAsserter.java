/*
 * Copyright (c) Lime Mojito Pty Ltd 2011-2016
 *
 * Except as otherwise permitted by the Copyright Act 1967 (Cth) (as amended from time to time) and/or any other
 * applicable copyright legislation, the material may not be reproduced in any format and in any way whatsoever
 * without the prior written consent of the copyright owner.
 */

package com.limemojito.play.order.util;

import static com.limemojito.play.order.util.AccessorAsserter.assertGettersAndSetters;
import static com.limemojito.play.order.util.CanonicalAsserter.assertCanonical;

public class ModelAsserter {
    public static void assertModelBehaviour(Object model,
                                            Object duplicate,
                                            Object other,
                                            String... ignoreProperties) throws Exception {
        assertCanonical(model, duplicate, other);
        assertGettersAndSetters(model, ignoreProperties);
    }
}
