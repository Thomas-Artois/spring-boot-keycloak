package com.switchfully.spring_boot_keycloak.security;

import org.assertj.core.util.Lists;

import java.util.List;

import static com.switchfully.spring_boot_keycloak.security.Feature.*;

public enum Role {
    ADMIN("admin", GET_ADMIN_HELLO_WORLD, GET_CUSTOMER_HELLO_WORLD, ADD_PARKLOT),
    CUSTOMER("customer", GET_CUSTOMER_HELLO_WORLD);

    private final String label;
    private final List<Feature> featureList;

    Role(String label, Feature... featureList) {
        this.label = label;
        this.featureList = Lists.newArrayList(featureList);
    }

    public List<Feature> getFeatures() {
        return featureList;
    }

    public String getLabel() {
        return label;
    }
}
