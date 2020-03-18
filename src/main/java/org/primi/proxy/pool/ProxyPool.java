package org.primi.proxy.pool;

import org.primi.proxy.pool.entity.ProxyStatistics;
import org.primi.proxy.pool.handler.BaseHandler;
import org.primi.proxy.pool.store.ProxySelect;
import org.primi.proxy.pool.store.ProxyStore;

/**
 * Create AT 2020/3/16 13:48:13:48
 *
 * @author Primi.Chen
 */
public interface ProxyPool {

    void registerSelect(ProxySelect select);

    void registerStore(ProxyStore select);

    void registerHandler(BaseHandler handler);

    void initial(ProxyPoolConfig config);

    /**
     * @return
     */
    ProxyStatistics select();

}
