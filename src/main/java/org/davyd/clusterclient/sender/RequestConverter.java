package org.davyd.clusterclient.sender;

import org.davyd.clusterclient.model.ClusterRequest;

import java.net.URI;
import java.util.Optional;

public interface RequestConverter {

    Optional<ClusterRequest> convertToAbsolute(ClusterRequest relative, URI shardUri);
}
