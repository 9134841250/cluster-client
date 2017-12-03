package org.davyd.clusterclient.sender;

import org.davyd.clusterclient.model.ClusterRequest;
import org.davyd.clusterclient.model.ClusterResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

public interface RequestSender {

    Mono<ClusterResponse> sendToShard(URI shardUri, ClusterRequest request);
}
