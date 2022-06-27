package br.com.itau.animals.configuration;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClientConfiguration {

    @Value("${api.cat.uri}")
    private String uri;

    @Bean("webclient_cat")
    public WebClient webClientCat() {
        return WebClient.builder()
                .clone()
                .clientConnector(connector())
                .baseUrl(uri)
                .build();
    }

    private ClientHttpConnector connector() {
        var httpClient = HttpClient.create()
                .tcpConfiguration(tcpClient -> tcpClient.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000))
                .doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(10))
                        .addHandlerLast(new WriteTimeoutHandler(10)));
        return new ReactorClientHttpConnector(httpClient.wiretap(true));
    }
}