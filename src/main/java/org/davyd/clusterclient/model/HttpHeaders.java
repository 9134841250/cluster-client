package org.davyd.clusterclient.model;

import java.util.*;
import java.util.stream.Collectors;

public class HttpHeaders implements Iterable<HttpHeader> {

    private final Set<HttpHeader> headers;

    public HttpHeaders() {
        this.headers = new HashSet<>();
    }

    public static HttpHeaders create(final String name, final String value) {
        return create(HttpHeader.create(name, value));
    }

    public static HttpHeaders create(final HttpHeader header) {
        final HttpHeaders result = new HttpHeaders();
        result.headers.add(Objects.requireNonNull(header, "Http header must not be a null"));
        return result;
    }

    public static HttpHeaders from(final HttpHeaders headers) {
        final HttpHeaders result = new HttpHeaders();
        result.headers.addAll(headers.headers);
        return result;
    }

    public int count() {
        return headers.size();
    }

    public Iterable<CharSequence> names() {
        return headers.stream().map(HttpHeader::getName).collect(Collectors.toList());
    }

    public Iterable<CharSequence> values() {
        return headers.stream().map(HttpHeader::getValue).collect(Collectors.toList());
    }

    @Override
    public Iterator<HttpHeader> iterator() {
        return new Iterator<HttpHeader>() {
            private final List<HttpHeader> list = new ArrayList<>(headers);
            private int index = 0;

            @Override
            public boolean hasNext() {
                return list.size() > index;
            }

            @Override
            public HttpHeader next() {
                return list.get(index++);
            }
        };
    }

    public Optional<HttpHeader> find(final String name) {
        Objects.requireNonNull(name, "Header name cannot be a NULL");
        return headers.stream().filter(header -> header.getName().equals(name)).findAny();
    }

    public Optional<CharSequence> get(final String name) {
        return find(name).map(HttpHeader::getValue);
    }

    public HttpHeaders append(final HttpHeader header) {
        final HttpHeaders result = HttpHeaders.from(this);
        result.headers.add(Objects.requireNonNull(header, "Http header cannot be a NULL"));
        return result;
    }

    public HttpHeaders append(final HttpHeaders headers) {
        Objects.requireNonNull(headers, "Http headers cannot be a NULL");
        final HttpHeaders result = HttpHeaders.from(this);
        result.headers.addAll(Objects.requireNonNull(headers.headers, "Http headers cannot be a NULL"));
        return result;
    }

    public HttpHeaders remove(final String name) {
        Objects.requireNonNull(name, "Header name cannot be a NULL");
        final HttpHeaders result = new HttpHeaders();
        result.headers.addAll(this.headers.stream()
                .filter(header -> !header.getName().equals(name))
                .collect(Collectors.toList()));
        return result;
    }

    public HttpHeaders remove(final HttpHeader header) {
        final HttpHeaders result = HttpHeaders.from(this);
        result.headers.remove(Objects.requireNonNull(header, "Http header cannot be a NULL"));
        return result;
    }

    @Override
    public String toString() {
        return "HttpHeaders{" +
                "headers=" + headers +
                '}';
    }
}
