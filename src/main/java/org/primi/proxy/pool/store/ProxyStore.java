package org.primi.proxy.pool.store;

import org.primi.proxy.pool.entity.ProxyStatistics;

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
     * @param proxies Proxy list {@link ProxyStatistics}
     */
    void updateActives(List<ProxyStatistics> proxies);

    /**
     * Add one proxy;
     *
     * @param proxy proxy Object {@link ProxyStatistics}
     */
    void addActive(ProxyStatistics proxy);

    /**
     * resume from sotre
     *
     * @return {}
     */
    default List<ProxyStatistics> resume() {
        return null;
    }
}
