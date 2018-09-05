package com.overseaslabs.examples.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * Creates network objects required for communicating with the REST APIs
 */
public class NetObjectsFactory {

    @Value("${apiGate.uri}")
    private String apiGateUri;

    /**
     * Make specific HTTP headers
     *
     * @param host Host header
     * @return specific headers
     */
    private HttpHeaders makeHeaders(String host) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Host", host);
        return headers;
    }

    /**
     * Create an HTTP entity with the headers required by the API GATE
     *
     * @param host The value of the host header
     * @return HTTP entity
     */
    public HttpEntity makeHttpEntity(String host) {
        return new HttpEntity(makeHeaders(host));
    }

    /**
     * Create an HTTP entity with the headers required by the API GATE and body
     *
     * @param host The value of the host header
     * @param body The request body
     * @param <T>  The type of the body
     * @return HTTP entity
     */
    public <T> HttpEntity<T> makeHttpEntity(String host, T body) {
        return new HttpEntity<>(body, makeHeaders(host));
    }

    /**
     * Make URI builder
     * @param path API path
     * @return URI builder
     */
    private UriComponentsBuilder makeUriBuilder(String path) {
        return UriComponentsBuilder.fromUriString(apiGateUri).path(path);
    }

    /**
     * Make a URI pointing to the API gate
     *
     * @param path API gate path
     * @return URI
     */
    public URI makeUri(String path) {
        return makeUriBuilder(path).build().encode().toUri();
    }

    /**
     * Make a URI pointing to the API gate
     *
     * @param path     API gate path
     * @param pageable Pagination info
     * @return URI
     */
    public URI makeUri(String path, Pageable pageable) {
        return makeUriBuilder(path)
                .queryParam("page", pageable.getPageNumber())
                .queryParam("size", pageable.getPageSize())
                .queryParam("sort", "id,desc")
                .build()
                .encode()
                .toUri();
    }
}
