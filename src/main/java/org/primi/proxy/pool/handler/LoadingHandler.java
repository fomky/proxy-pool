package org.primi.proxy.pool.handler;

import org.primi.proxy.pool.entity.CheckStatus;
import org.primi.proxy.pool.entity.ProxyStatistics;

import java.util.List;

/**
 * Create AT 2020/3/18 15:14:15:14
 *
 * @author Primi.Chen
 */
public interface LoadingHandler extends Runnable{

    List<ProxyStatistics> load(BaseHandler handler);

    CheckStatus check(ProxyStatistics proxy);
}
