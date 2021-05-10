package net.tomo1560.keycloak.broker.oidc.mappers;

import org.keycloak.broker.oidc.KeycloakOIDCIdentityProviderFactory;
import org.keycloak.broker.oidc.OIDCIdentityProviderFactory;
import org.keycloak.broker.oidc.mappers.AbstractClaimMapper;
import org.keycloak.broker.provider.BrokeredIdentityContext;
import org.keycloak.models.IdentityProviderMapperModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.social.bitbucket.BitbucketIdentityProviderFactory;
import org.keycloak.social.facebook.FacebookIdentityProviderFactory;
import org.keycloak.social.github.GitHubIdentityProviderFactory;
import org.keycloak.social.gitlab.GitLabIdentityProviderFactory;
import org.keycloak.social.google.GoogleIdentityProviderFactory;
import org.keycloak.social.instagram.InstagramIdentityProviderFactory;
import org.keycloak.social.linkedin.LinkedInIdentityProviderFactory;
import org.keycloak.social.microsoft.MicrosoftIdentityProviderFactory;
import org.keycloak.social.openshift.OpenshiftV3IdentityProviderFactory;
import org.keycloak.social.openshift.OpenshiftV4IdentityProviderFactory;
import org.keycloak.social.paypal.PayPalIdentityProviderFactory;
import org.keycloak.social.stackoverflow.StackoverflowIdentityProviderFactory;
import org.keycloak.social.twitter.TwitterIdentityProviderFactory;

import java.util.ArrayList;
import java.util.List;

public class UpdateNameMapper extends AbstractClaimMapper {

    public static final String[] COMPATIBLE_PROVIDERS = new String[] { KeycloakOIDCIdentityProviderFactory.PROVIDER_ID,
            OIDCIdentityProviderFactory.PROVIDER_ID, BitbucketIdentityProviderFactory.PROVIDER_ID,
            FacebookIdentityProviderFactory.PROVIDER_ID, GitHubIdentityProviderFactory.PROVIDER_ID,
            GitLabIdentityProviderFactory.PROVIDER_ID, GoogleIdentityProviderFactory.PROVIDER_ID,
            InstagramIdentityProviderFactory.PROVIDER_ID, LinkedInIdentityProviderFactory.PROVIDER_ID,
            MicrosoftIdentityProviderFactory.PROVIDER_ID, OpenshiftV3IdentityProviderFactory.PROVIDER_ID,
            OpenshiftV4IdentityProviderFactory.PROVIDER_ID, PayPalIdentityProviderFactory.PROVIDER_ID,
            StackoverflowIdentityProviderFactory.PROVIDER_ID, TwitterIdentityProviderFactory.PROVIDER_ID };

    public static final String PROVIDER_ID = "oidc-name-idp-update-mapper";

    private static final List<ProviderConfigProperty> configProperties = new ArrayList<>();


    @Override public String[] getCompatibleProviders() {
        return COMPATIBLE_PROVIDERS;
    }

    @Override public String getDisplayCategory() {
        return "UpdateBrokeredUser";
    }

    @Override public String getDisplayType() {
        return "Update Brokered User";
    }

    @Override public void updateBrokeredUser(KeycloakSession keycloakSession, RealmModel realmModel,
            UserModel userModel, IdentityProviderMapperModel identityProviderMapperModel,
            BrokeredIdentityContext brokeredIdentityContext) {
        userModel.setFirstName(brokeredIdentityContext.getFirstName());
        userModel.setLastName(brokeredIdentityContext.getLastName());
    }

    @Override public String getHelpText() {
        return "Synchronize the name with the Identity Provider every time login.";
    }

    @Override public List<ProviderConfigProperty> getConfigProperties() {
        return configProperties;
    }

    @Override public void preprocessFederatedIdentity(KeycloakSession session, RealmModel realm,
            IdentityProviderMapperModel mapperModel, BrokeredIdentityContext context) {
    }

    @Override public String getId() {
        return PROVIDER_ID;
    }
}
