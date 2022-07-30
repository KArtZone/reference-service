package tech.inno.nsup.referenceservice.abstracts;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_CLASS;

/**
 * Базовый класс тестирования контроллеров API Reference-сервиса
 *
 * @author IVIvanov
 * @since 22.07.2022
 */

@AutoConfigureWebTestClient
@DirtiesContext(classMode = BEFORE_CLASS)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class AbstractController {

    protected static WireMockServer wireMockServer;
    @Autowired
    protected WebTestClient webClient;

    @BeforeAll
    static void startWireMockServer() {
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().dynamicPort());
        wireMockServer.start();
        WireMock.configureFor("localhost", wireMockServer.port());
    }

    @AfterAll
    static void stopWireMockServer() {
        wireMockServer.stop();
    }
}
