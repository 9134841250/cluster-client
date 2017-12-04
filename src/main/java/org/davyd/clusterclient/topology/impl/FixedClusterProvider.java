package org.davyd.clusterclient.topology.impl;

import org.davyd.clusterclient.topology.ClusterProvider;

import java.net.URI;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class FixedClusterProvider implements ClusterProvider {

    private final Set<URI> shards;

    public FixedClusterProvider(final Collection<URI> shards) {
        this.shards = new HashSet<>(shards);
    }

    @Override
    public Stream<URI> getCluster() {
        return shards.stream();
    }

    @Override
    public String toString() {
        return "FixedClusterProvider{" +
                "shards=" + shards +
                '}';
    }
}
