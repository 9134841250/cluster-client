package org.davyd.clusterclient.model;

import java.util.Objects;

public class HttpHeader {

    private final String name;
    private final String value;

    private HttpHeader(final String name, final String value) {
        this.name = Objects.requireNonNull(name, "Header name must not be a null");
        this.value = Objects.requireNonNull(value, "Header value must not be a null");
    }

    public static HttpHeader create(final String name, final String value) {
        return new HttpHeader(name, value);
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HttpHeader that = (HttpHeader) o;

        return name.equals(that.name) && value.equals(that.value);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "HttpHeader{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
