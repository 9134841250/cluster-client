package org.davyd.clusterclient.topology;

import java.net.URI;
import java.util.stream.Stream;

public interface ClusterProvider {

    Stream<URI> getCluster();
}
