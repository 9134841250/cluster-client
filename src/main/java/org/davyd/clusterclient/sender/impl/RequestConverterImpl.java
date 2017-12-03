package org.davyd.clusterclient.sender.impl;

import org.davyd.clusterclient.model.ClusterRequest;
import org.davyd.clusterclient.sender.RequestConverter;
import reactor.util.Logger;
import reactor.util.Loggers;

import java.net.URI;
import java.util.Optional;

public class RequestConverterImpl implements RequestConverter {

    private final Logger logger = Loggers.getLogger(RequestConverter.class);

    @Override
    public Optional<ClusterRequest> convertToAbsolute(ClusterRequest relative, URI shardUri) {
        final String query = shardUri.getQuery();

        if (!shardUri.isAbsolute() || shardUri.getHost() == null) {
            logger.error("Given shard uri is not absolute: " + shardUri);
            return Optional.empty();
        } else if (query != null && !query.isEmpty()) {
            logger.error("Shard uri contains query parameters: " + query);
            return Optional.empty();
        }
        final String baseUrl = shardUri.toString();
        boolean baseUrlEndsWithSlash = baseUrl.endsWith("/");

//        String appendedUrl = requestUrl.toString();
//        boolean appendedUrlStartsWithSlash = appendedUrl.startsWith("/");
//
//        StringBuilder builder = new StringBuilder(baseUrl.length() + appendedUrl.length() + 1);
//        builder.append(baseUrl);
//
//        if (baseUrlEndsWithSlash && appendedUrlStartsWithSlash) {
//            builder.append(appendedUrl.substring(1));
//        } else {
//            if (!baseUrlEndsWithSlash && !appendedUrlStartsWithSlash) {
//                builder.append('/');
//            }
//
//            builder.append(appendedUrl);
//        }
//
//        return new URI(builder.toString());
        return null;
    }
}
