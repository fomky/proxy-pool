package org.primi.proxy.pool;

import com.google.common.collect.Lists;
import org.primi.proxy.pool.handler.BaseHandler;
import org.primi.proxy.pool.handler.DefaultLoadingHandler;
import org.primi.proxy.pool.store.DefaultStoreSelect;
import org.primi.proxy.pool.store.ProxySelect;
import org.primi.proxy.pool.store.ProxyStore;

import java.util.List;

/**
 * Create AT 2020/3/18 14:27:14:27
 *
 * @author Primi.Chen
 */
public abstract class BaseProxyPool implements ProxyPool {

    private DefaultStoreSelect storeSelect = DefaultStoreSelect.builder().build();

    private ProxyPoolConfig config = ProxyPoolConfig.builder().build();

    private List<BaseHandler> handlers = Lists.newArrayList();


    @Override
    public void initial(ProxyPoolConfig config) {
    }

    @Override
    public void registerSelect(ProxySelect select) {
        storeSelect.setSelect(select);
    }

    @Override
    public void registerStore(ProxyStore store) {
        storeSelect.setStore(store);
    }

    @Override
    public void registerHandler(BaseHandler handler) {
        handlers.add(handler);
    }

}
