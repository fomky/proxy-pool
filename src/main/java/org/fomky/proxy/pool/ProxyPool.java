package org.fomky.proxy.pool;

import org.fomky.proxy.pool.entity.ProxyNode;
import org.fomky.proxy.pool.handler.BaseHandler;
import org.fomky.proxy.pool.store.ProxySelect;
import org.fomky.proxy.pool.store.ProxyStore;

/**
 * Create AT 2020/3/16 13:48:13:48
 *
 * @author Primi.Chen
 */
public interface ProxyPool<T> {
    /**
     * Register ProxySelect
     *
     * @param select {@link ProxyStore}
     * @return {@link T}
     */
    T registerSelect(ProxySelect select);

    /**
     * Register ProxyStore
     *
     * @param select {@link ProxyStore}
     * @return {@link T}
     */
    T registerStore(ProxyStore select);

    /**
     * Register Pasear Handler
     *
     * @param handler {@link BaseHandler}
     * @return {@link T}
     */
    T registerHandler(BaseHandler handler);

    /**
     * initial
     *
     * @param config ProxyConfig
     * @return {@link T}
     */
    T initial(ProxyPoolConfig config);

    /**
     * initial
     *
     * @return {@link T}
     */
    default T initial() {
        return initial(ProxyPoolConfig.builder().build());
    }

    ;

    /**
     * Select proxy description
     *
     * @return {@link ProxyNode}
     */
    ProxyNode select();

    /**
     * create DefaultProxyPool
     *
     * @return create default {@link DefaultProxyPool}
     */
    static DefaultProxyPool base() {
        return DefaultProxyPool.builder().build();
    }

}
