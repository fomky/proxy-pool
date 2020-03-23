package org.primi.proxy;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.primi.proxy.handler.DemoHandler;
import org.primi.proxy.pool.DefaultProxyPool;
import org.primi.proxy.pool.ProxyPool;
import org.primi.proxy.pool.store.DefaultStoreSelect;

/**
 * Unit test for simple App.
 */
public class ApplicationLaucherTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        DefaultProxyPool pool = ProxyPool.base().initial();
    }
}
