package org.primi.proxy.handler;

import lombok.Builder;
import org.jsoup.Connection;
import org.primi.proxy.pool.entity.ProxyListPage;
import org.primi.proxy.pool.entity.ProxyStatistics;
import org.primi.proxy.pool.handler.BaseHandler;

import java.util.List;

/**
 * Create AT 2020/3/23 11:14:11:14
 *
 * @author Primi.Chen
 */
@Builder
public class DemoHandler implements BaseHandler {
    @Override
    public List<ProxyListPage> parserLoadPages() {
        return null;
    }

    @Override
    public List<ProxyStatistics> parserPageContent(Connection.Response response) {
        return null;
    }
}
