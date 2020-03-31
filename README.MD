# Proxy pool use java


## Handlers

Use Handlers to configure the API list and implement methods for parsing API content.

You need to implement interface BaseHandler.

```java
public interface BaseHandler {

    List<ProxyListPage> parserLoadPages();

    List<ProxyStatistics> parserPageContent(Response response);

    default Response request(ProxyListPage page) throws IOException {
        Connection connection = Jsoup.connect(page.getUrl())
                .ignoreHttpErrors(true)
                .method(Optional.of(page.getMethod()).orElse(Method.GET));

        if (Objects.nonNull(page.getParameters())) {
            connection.data(page.getParameters());
        }
        return connection.execute();
    }

    default String handlerName() {
        return this.getClass().getName();
    }

    default boolean staticModel() {
        return false;
    }
}
```

Simple Demo: 

```java
public class DemoHandler implements BaseHandler {

    /* .... */

}
```


