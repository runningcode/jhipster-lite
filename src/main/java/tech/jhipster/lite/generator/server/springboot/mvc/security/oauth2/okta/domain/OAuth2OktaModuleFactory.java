package tech.jhipster.lite.generator.server.springboot.mvc.security.oauth2.okta.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.documentationTitle;
import static tech.jhipster.lite.module.domain.JHipsterModule.from;
import static tech.jhipster.lite.module.domain.JHipsterModule.moduleBuilder;
import static tech.jhipster.lite.module.domain.JHipsterModule.propertyKey;
import static tech.jhipster.lite.module.domain.JHipsterModule.propertyValue;
import static tech.jhipster.lite.module.domain.JHipsterModule.to;

import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.javaproperties.SpringProfile;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

public class OAuth2OktaModuleFactory {

  private static final JHipsterSource SOURCE = from("server/springboot/mvc/security/oauth2/okta");
  private static final SpringProfile OKTA_SPRING_PROFILE = new SpringProfile("okta");
  private static final String CLIENT_ID_PROPERTY = "clientId";
  private static final String OKTA_DOMAIN_PROPERTY = "oktaDomain";

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    Assert.notNull("properties", properties);

    String issuerUri = "https://" + properties.getString(OKTA_DOMAIN_PROPERTY) + "/oauth2/default";
    String clientId = properties.getString(CLIENT_ID_PROPERTY);

    //@formatter:off
    return moduleBuilder(properties)
      .documentation(documentationTitle("Okta"), SOURCE.template("documentation/okta.md"))
      .files()
        .add(SOURCE.file("okta.sh"), to("okta.sh"))
        .add(SOURCE.file("documentation/images/security-add-claim.png"), to("documentation/images/security-add-claim.png"))
        .and()
      .springMainProperties(OKTA_SPRING_PROFILE)
        .set(propertyKey("spring.security.oauth2.client.provider.oidc.issuer-uri"), propertyValue(issuerUri))
        .set(propertyKey("spring.security.oauth2.client.registration.oidc.client-id"), propertyValue(clientId))
        .and()
      .build();
    //@formatter:on
  }
}
