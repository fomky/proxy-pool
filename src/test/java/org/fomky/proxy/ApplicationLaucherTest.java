package org.fomky.proxy;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.fomky.proxy.pool.DefaultProxyPool;
import org.fomky.proxy.pool.ProxyPool;

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
