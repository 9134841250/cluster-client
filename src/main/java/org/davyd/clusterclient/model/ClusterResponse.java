package org.davyd.clusterclient.model;

import org.reactivestreams.Publisher;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;

public class ClusterResponse {

    private final int code;
    private final Flux<byte[]> content;
    private Disposable interrupter;
    private HttpHeaders httpHeaders;

    private ClusterResponse(final int responseCode, final Publisher<byte[]> content) {
        this.code = responseCode;
        this.content = Flux.from(content);
    }

    private void setHeaders(final HttpHeaders headers) {
        this.httpHeaders = headers;
    }

    private void setInterrupter(final Disposable interrupter) {
        this.interrupter = interrupter;
    }

    public static Builder builder() {
        return new Builder();
    }

    public int getCode() {
        return code;
    }

    public Flux<byte[]> getContent() {
        if (content == null) {
            return Flux.empty();
        }
        return content;
    }

    public Flux<String> getStringContent() {
        return getContent().map(bytes -> new String(bytes, StandardCharsets.UTF_8));
    }

    public Optional<Disposable> interrupter() {
        return Optional.ofNullable(interrupter);
    }

    public Optional<HttpHeaders> getHeaders() {
        return Optional.ofNullable(httpHeaders);
    }

    @Override
    public String toString() {
        return "ClusterResponse{" +
                "code=" + code +
                ", content=" + content +
                ", httpHeaders=" + httpHeaders +
                '}';
    }

    public static class Builder {
        private int status = 200;
        private Publisher<byte[]> content;
        private Disposable interrupter;
        private HttpHeaders httpHeaders;

        private Builder() {}

        public Builder code(final int status) {
            this.status = status;
            return this;
        }

        public Builder content(final Publisher<byte[]> content) {
            this.content = Objects.requireNonNull(content, "Content cannot be a NULL");
            return this;
        }

        public Builder interrupter(final Disposable interrupter) {
            this.interrupter = Objects.requireNonNull(interrupter, "Interrupter cannot be a NULL");
            return this;
        }

        public Builder headers(final HttpHeaders httpHeaders) {
            if (this.httpHeaders == null) {
                this.httpHeaders =
                        HttpHeaders.from(Objects.requireNonNull(httpHeaders, "Http headers cannot be a NULL"));
            } else {
                this.httpHeaders =
                        this.httpHeaders.append(Objects.requireNonNull(httpHeaders, "Http headers cannot be a NULL"));
            }
            return this;
        }

        public Builder header(final HttpHeader httpHeader) {
            if (httpHeaders == null) {
                httpHeaders = HttpHeaders.create(Objects.requireNonNull(httpHeader, "Http header cannot be a NULL"));
            } else {
                httpHeaders = httpHeaders.append(Objects.requireNonNull(httpHeader, "Http header cannot be a NULL"));
            }
            return this;
        }

        public Builder header(final String name, final String value) {
            final HttpHeader header = HttpHeader.create(Objects.requireNonNull(name, "Header name cannot be a NULL"),
                    Objects.requireNonNull(value, "Header value cannot be a NULL"));
            return header(header);
        }

        public ClusterResponse build() {
            final ClusterResponse request = new ClusterResponse(status, content);
            request.setHeaders(httpHeaders);
            request.setInterrupter(interrupter);
            return request;
        }
    }
}
