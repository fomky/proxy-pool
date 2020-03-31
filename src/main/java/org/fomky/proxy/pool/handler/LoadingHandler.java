package org.fomky.proxy.pool.handler;

import org.fomky.proxy.pool.entity.CheckStatus;
import org.fomky.proxy.pool.entity.ProxyNode;

import java.util.List;

/**
 * Create AT 2020/3/18 15:14:15:14
 *
 * @author Primi.Chen
 */
public interface LoadingHandler extends Runnable {

    /**
     * load the proxy list
     *
     * @param handler load
     * @return ProxyStatistics
     */
    List<ProxyNode> load(BaseHandler handler);

    /**
     * check the Proxy
     *
     * @param proxy proxy
     * @return {@link CheckStatus}
     */
    CheckStatus check(ProxyNode proxy);
}
