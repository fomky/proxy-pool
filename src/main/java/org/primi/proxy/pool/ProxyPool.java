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
public interface ProxyPool<T> {

    T registerSelect(ProxySelect select);

    T registerStore(ProxyStore select);

    T registerHandler(BaseHandler handler);

    T initial(ProxyPoolConfig config);

    /**
     * @return
     */
    default T initial() {
        return initial(ProxyPoolConfig.builder().build());
    }

    ;

    /**
     * @return
     */
    ProxyStatistics select();


    static DefaultProxyPool base() {
        return DefaultProxyPool.builder().build();
    }

}
