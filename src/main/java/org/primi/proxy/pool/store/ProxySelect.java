package org.primi.proxy.pool.store;

import org.primi.proxy.pool.entity.ProxyStatistics;

/**
 * Create AT 2020/3/18 11:55:11:55
 *
 * @author Primi.Chen
 */
public interface ProxySelect {
    /**
     * 获取代理配置信息
     * @return {@link ProxyStatistics}
     */
    ProxyStatistics select();
}
