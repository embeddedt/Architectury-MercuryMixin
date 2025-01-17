/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.cadixdev.mercury.mixin.annotation;

import org.cadixdev.mercury.shadow.org.eclipse.jdt.core.dom.IAnnotationBinding;
import org.cadixdev.mercury.shadow.org.eclipse.jdt.core.dom.IMemberValuePairBinding;

import java.util.Objects;

/**
 * A container for data held in the {@code @Shadow} annotation.
 *
 * @author Jamie Mansfield
 * @since 0.1.0
 */
public class ShadowData {

    // @Shadow(prefix="shadow$")
    public static ShadowData from(final IAnnotationBinding binding) {
        String prefix = "shadow$";

        for (final IMemberValuePairBinding pair : binding.getDeclaredMemberValuePairs()) {
            if (Objects.equals("prefix", pair.getName())) {
                prefix = (String) pair.getValue();
            }
        }

        return new ShadowData(prefix);
    }

    private final String prefix;

    public ShadowData(final String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String prefix(final String name) {
        return this.prefix + name;
    }

    public String stripPrefix(final String name) {
        if (name.startsWith(this.prefix)) {
            return name.substring(this.prefix.length());
        }
        return name;
    }

}
