package org.primi.proxy.pool.store;

import java.util.List;

/**
 * Create AT 2020/3/16 15:04:15:04
 *
 * @author Primi.Chen
 */
public interface ProxyStore {
    /**
     * update and replace
     * @param proxies Proxy list
     */
    void updateActives(List<ProxyStore> proxies);

}
