package com.sharecare;

import info.magnolia.ui.api.availability.AbstractAvailabilityRule;
import info.magnolia.ui.api.availability.AvailabilityRule;

import java.util.Collection;

public class AlwaysShowRule implements AvailabilityRule {

    @Override
    public boolean isAvailable(Collection<?> itemIds) {
        return true;
    }
}
