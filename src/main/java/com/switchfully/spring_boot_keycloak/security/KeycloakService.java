package com.switchfully.spring_boot_keycloak.security;

import org.assertj.core.util.Lists;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.ClientResource;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@Service
public class KeycloakService {

    private final RealmResource realmResource;
    private final String clientID;

    public KeycloakService(Keycloak keycloak, @Value("${keycloak.realm}") String realmName, @Value("${keycloak.resource}") String clientId) {
        this.clientID = clientId;
        this.realmResource = keycloak.realm(realmName);
    }

    public void addUser(KeycloakUserDTO keycloakUserDTO) {
        String createdUserId = createUser(keycloakUserDTO);
        getUser(createdUserId).resetPassword(createCredentialRepresentation(keycloakUserDTO.password()));
        addRole(getUser(createdUserId), keycloakUserDTO.role().getLabel());
    }

    private String createUser(KeycloakUserDTO keycloakUserDTO) {
        try {
            return CreatedResponseUtil.getCreatedId(createUser(keycloakUserDTO.userName()));
        } catch (WebApplicationException exception) {
            throw new UserAlreadyExistsException(keycloakUserDTO.userName());
        }
    }

    private CredentialRepresentation createCredentialRepresentation(String password) {
        CredentialRepresentation passwordCredentials = new CredentialRepresentation();
        passwordCredentials.setTemporary(false);
        passwordCredentials.setType(CredentialRepresentation.PASSWORD);
        passwordCredentials.setValue(password);
        return passwordCredentials;
    }

    private void addRole(UserResource user, String roleName) {
        user.roles().clientLevel(getClientUUID()).add(Lists.newArrayList(getRole(roleName)));
    }

    private String getClientUUID() {
        return realmResource.clients().findByClientId(clientID).get(0).getId();
    }

    private Response createUser(String username) {
        return realmResource.users().create(createUserRepresentation(username));
    }

    private UserResource getUser(String userId) {
        return realmResource.users().get(userId);
    }

    private RoleRepresentation getRole(String roleToAdd) {
        return getClientResource().roles().get(roleToAdd).toRepresentation();
    }

    private ClientResource getClientResource() {
        return realmResource.clients().get(getClientUUID());
    }

    private UserRepresentation createUserRepresentation(String username) {
        UserRepresentation user = new UserRepresentation();
        user.setUsername(username);
        user.setEnabled(true);
        return user;
    }
}
