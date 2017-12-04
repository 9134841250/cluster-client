package org.davyd.clusterclient.transport;

import org.davyd.clusterclient.model.ClusterRequest;
import org.davyd.clusterclient.model.ClusterResponse;
import reactor.core.publisher.Mono;

import java.io.Closeable;
import java.time.Duration;

/**
 * Represents an client used as a transport for cluster communication.
 */
public interface Transport extends Closeable {

    Mono<ClusterResponse> sendAsync(ClusterRequest request, Duration connectionTimeout, Duration readTimeout);
}
