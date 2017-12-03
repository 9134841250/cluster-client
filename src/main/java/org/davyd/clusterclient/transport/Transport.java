package org.davyd.clusterclient.transport;

import org.davyd.clusterclient.model.ClusterRequest;
import org.davyd.clusterclient.model.ClusterResponse;
import reactor.core.publisher.Mono;

import java.io.Closeable;
import java.time.Duration;

public interface Transport extends Closeable {

    Mono<ClusterResponse> sendAsync(ClusterRequest request, Duration connectionTimeout, Duration readTimeout);
}
