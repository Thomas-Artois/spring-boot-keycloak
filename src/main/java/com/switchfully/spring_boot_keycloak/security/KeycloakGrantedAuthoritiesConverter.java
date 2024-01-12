package com.switchfully.spring_boot_keycloak.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class KeycloakGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {


    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        Map<String, Object> resourceAccess = source.getClaimAsMap("resource_access");
        Map<String, Object> clientAccess = (Map<String, Object>) resourceAccess.get("keycloak-example");
        List<String> roles = (List<String>) clientAccess.get("roles");
        return roles.stream()
                .map(role -> Role.valueOf(role))
                .flatMap(role -> role.getFeatures().stream())
                .map(feature -> new SimpleGrantedAuthority(feature.name()))
                .collect(Collectors.toList());
    }
}
