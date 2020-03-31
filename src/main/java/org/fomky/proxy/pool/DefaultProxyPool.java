package org.fomky.proxy.pool;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Data;
import org.fomky.proxy.pool.entity.ProxyNode;
import org.fomky.proxy.pool.handler.BaseHandler;
import org.fomky.proxy.pool.store.DefaultStoreSelect;
import org.fomky.proxy.pool.store.ProxySelect;
import org.fomky.proxy.pool.store.ProxyStore;

import java.util.List;

/**
 * Create AT 2020/3/18 14:27:14:27
 *
 * @author Primi.Chen
 */
@Data
@Builder
public class DefaultProxyPool implements ProxyPool<DefaultProxyPool> {

    @Builder.Default
    protected DefaultStoreSelect storeSelect = DefaultStoreSelect.builder().build();

    @Builder.Default
    protected ProxyPoolConfig config = ProxyPoolConfig.builder().build();

    @Builder.Default
    protected List<BaseHandler> handlers = Lists.newArrayList();


    @Override
    public DefaultProxyPool initial(ProxyPoolConfig config) {
        this.config = config;
        return this;
    }

    @Override
    public DefaultProxyPool registerSelect(ProxySelect select) {
        storeSelect.setSelect(select);
        return this;
    }

    @Override
    public DefaultProxyPool registerStore(ProxyStore store) {
        storeSelect.setStore(store);
        return this;
    }

    @Override
    public DefaultProxyPool registerHandler(BaseHandler handler) {
        handlers.add(handler);
        return this;
    }

    @Override
    public ProxyNode select() {
        return storeSelect.select();
    }
}
