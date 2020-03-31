package org.fomky.proxy.pool.tools;

import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;

/**
 * @author fomky
 */
@Slf4j
public class HTTPSTrustManager implements javax.net.ssl.TrustManager, javax.net.ssl.X509TrustManager {

    static HostnameVerifier hostnameVerifier = (urlHostName, session) -> true;

    @Override
    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
        return null;
    }

    public boolean isServerTrusted(java.security.cert.X509Certificate[] certs) {
        return true;
    }

    public boolean isClientTrusted(java.security.cert.X509Certificate[] certs) {
        return true;
    }

    @Override
    public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
    }

    @Override
    public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
    }

    private void trustCertificates() throws Exception {
        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
        trustAllCerts[0] = this;
        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }

    public static void trustAllHttpsCertificates() throws Exception {
        new HTTPSTrustManager().trustCertificates();
        HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);
    }
}