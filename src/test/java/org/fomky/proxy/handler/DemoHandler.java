package org.fomky.proxy.handler;

import lombok.Builder;
import org.fomky.proxy.pool.entity.ProxyPage;
import org.fomky.proxy.pool.entity.ProxyNode;
import org.fomky.proxy.pool.handler.BaseHandler;

import java.util.List;

/**
 * Create AT 2020/3/23 11:14:11:14
 *
 * @author Primi.Chen
 */
@Builder
public class DemoHandler implements BaseHandler {
    @Override
    public List<ProxyPage> parserLoadPages() {
        return null;
    }

    @Override
    public List<ProxyNode> parser(String content) {
        return null;
    }

    @Override
    public boolean staticModel() {
        return false;
    }
}
