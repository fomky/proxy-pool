package org.fomky.proxy.pool.store;

import org.fomky.proxy.pool.entity.ProxyNode;

import java.util.List;

/**
 * Create AT 2020/3/16 15:04:15:04
 *
 * @author Primi.Chen
 */
public interface ProxyStore {
    /**
     * update and replace
     *
     * @param proxies Proxy list {@link ProxyNode}
     */
    void updateActives(List<ProxyNode> proxies);

    /**
     * Add one proxy;
     *
     * @param proxy proxy Object {@link ProxyNode}
     */
    void addActive(ProxyNode proxy);

    /**
     * resume from sotre
     *
     * @return {}
     */
    default List<ProxyNode> resume() {
        return null;
    }

    /**
     * Add all ProxyStatistics
     * @param proxyStatistics {@link ProxyNode}
     */
    void addActives(List<ProxyNode> proxyStatistics);
}
