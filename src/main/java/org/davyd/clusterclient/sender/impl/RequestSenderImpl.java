package org.davyd.clusterclient.sender.impl;

import org.davyd.clusterclient.model.ClusterRequest;
import org.davyd.clusterclient.model.ClusterResponse;
import org.davyd.clusterclient.sender.RequestSender;
import reactor.core.publisher.Mono;

import java.net.URI;

public class RequestSenderImpl implements RequestSender {

    @Override
    public Mono<ClusterResponse> sendToShard(URI shardUri, ClusterRequest request) {
        return null;
    }
}
