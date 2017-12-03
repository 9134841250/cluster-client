package org.davyd.clusterclient.model;

import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;
import java.util.Objects;
import java.util.Optional;

public class ClusterRequest {

    private final String method;
    private final URI uri;
    private Duration writeIdle;
    private Runnable writeIdleHandler;
    private Publisher<?> content;
    private HttpHeaders headers;

    private ClusterRequest(final String method, final URI uri) {
        this.method = Objects.requireNonNull(method, "Method cannot be a NULL");
        this.uri = Objects.requireNonNull(uri, "Uri cannot be a NULL");
    }

    private void setContent(Publisher<?> content) {
        this.content = content;
    }

    private void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }

    private void setWriteIdle(Duration writeIdle) {
        this.writeIdle = writeIdle;
    }

    private void setWriteIdleHandler(Runnable writeIdleHandler) {
        this.writeIdleHandler = writeIdleHandler;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Optional<HttpHeaders> getHeaders() {
        return Optional.ofNullable(headers);
    }

    public Optional<Duration> writeIdle() {
        return Optional.ofNullable(writeIdle);
    }

    public Optional<Runnable> writeIdleHandler() {
        return Optional.ofNullable(writeIdleHandler);
    }

    public Publisher<?> getContent() {
        return content == null ? Mono.empty() : content;
    }

    public URI getUri() {
        return uri;
    }

    public String getMethod() {
        return method;
    }

    public static final class Builder {
        private String method;
        private URI uri;
        private Publisher<?> content;
        private HttpHeaders httpHeaders;

        private Builder() {}

        public Builder get(final URI uri) {
            this.method = "GET";
            this.uri = Objects.requireNonNull(uri, "Uri cannot be a null");
            return this;
        }

        public Builder get(final String uri) {
            this.method = "GET";
            this.uri = URI.create(Objects.requireNonNull(uri, "Uri cannot be a null"));
            return this;
        }

        public Builder post(final URI uri) {
            this.method = "POST";
            this.uri = Objects.requireNonNull(uri, "Uri cannot be a null");
            return this;
        }

        public Builder post(final String uri) {
            this.method = "POST";
            this.uri = URI.create(Objects.requireNonNull(uri, "Uri cannot be a null"));
            return this;
        }

        public Builder put(final URI uri) {
            this.method = "PUT";
            this.uri = Objects.requireNonNull(uri, "Uri cannot be a null");
            return this;
        }

        public Builder put(final String uri) {
            this.method = "PUT";
            this.uri = URI.create(Objects.requireNonNull(uri, "Uri cannot be a null"));
            return this;
        }

        public Builder delete(final URI uri) {
            this.method = "DELETE";
            this.uri = Objects.requireNonNull(uri, "Uri cannot be a null");
            return this;
        }

        public Builder delete(final String uri) {
            this.method = "DELETE";
            this.uri = URI.create(Objects.requireNonNull(uri, "Uri cannot be a null"));
            return this;
        }

        public Builder content(final Publisher<?> content) {
            this.content = Objects.requireNonNull(content, "Content cannot be a NULL");
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

        public ClusterRequest build() {
            final ClusterRequest request = new ClusterRequest(method, uri);
            request.setHeaders(httpHeaders);
            request.setContent(content);
            return request;
        }
    }
}

