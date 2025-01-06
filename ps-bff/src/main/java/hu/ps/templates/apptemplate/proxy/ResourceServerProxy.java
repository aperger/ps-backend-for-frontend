package hu.ps.templates.apptemplate.proxy;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.web.client.RestTemplate;


public class ResourceServerProxy {

  public static final String AUTHORIZATION = "Authorization";

  private final String resourceServerURL;

  private final RestTemplate restTemplate;

  public ResourceServerProxy(
      String resourceServerURL,
      RestTemplate restTemplate) {
    this.resourceServerURL = resourceServerURL;
    this.restTemplate = restTemplate;
  }

  public <T> ResponseEntity<T> callEndPoint(OAuth2AuthorizedClient client, String relativeApiUrl,
      Class<T> clazz) {
    String token = client.getAccessToken().getTokenValue();

    String url = resourceServerURL + relativeApiUrl;

    HttpHeaders headers = new HttpHeaders();
    headers.add(AUTHORIZATION, "Bearer " + token);
    HttpEntity<Void> request = new HttpEntity<>(headers);

    return restTemplate.exchange(url, HttpMethod.GET, request, clazz);
  }
}
