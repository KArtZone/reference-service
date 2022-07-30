package tech.inno.nsup.referenceservice.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import tech.inno.nsup.referenceservice.abstracts.AbstractController;
import tech.inno.nsup.referenceservice.util.JsonUtils;
import tech.inno.nsup.referenceservice.domain.dto.ItemDto;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author IVIvanov
 * @since 22.07.2022
 */
@ActiveProfiles("test")
class ReferenceControllerTest extends AbstractController {

    private static final String SCHEMA = "test";
    private static final String TEST_VALUE = "TestValue";
    private static final String responseRootPath = "tech/inno/nsup/referenceservice/controller/__files/reference/response/";

    private static final UUID unitItemId = UUID.fromString("079f10d9-78e7-41ca-88cc-d675b4a0eeaa");
    private static final UUID unitItemAttributeId = UUID.fromString("bd2e0a67-ebc7-41f8-9218-03580b01b4f3");

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUp() {
        jdbcTemplate.execute("delete from " + SCHEMA + ".attribute_multiple_value where value = '" + TEST_VALUE + "'");
    }

    @Test
    @DisplayName("Get unit references with ok result")
    void getItemShouldReturnItemWhenAllOk() {
        final ItemDto expected = JsonUtils.createDummyDto(responseRootPath + "getReferences.json", ItemDto.class);

        final ItemDto actual = webClient.get()
                                        .uri("/references/" + unitItemId)
                                        .accept(MediaType.APPLICATION_JSON)
                                        .exchange()
                                        .expectStatus().isOk()
                                        .expectBody(ItemDto.class)
                                        .returnResult()
                                        .getResponseBody();

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("Get unit references with 404 response status")
    void getItemShouldReturn404WhenItemNotFound() {
        webClient.get()
                 .uri("/references/" + UUID.randomUUID())
                 .accept(MediaType.APPLICATION_JSON)
                 .exchange()
                 .expectStatus().isNotFound();
    }

    @Test
    @DisplayName("Add item attribute value with ok result")
    void addItemAttributeValueShouldReturnItemWhenAllOk() {
        final ItemDto before = webClient.get()
                                        .uri("/references/" + unitItemId)
                                        .accept(MediaType.APPLICATION_JSON)
                                        .exchange()
                                        .expectStatus().isOk()
                                        .expectBody(ItemDto.class)
                                        .returnResult()
                                        .getResponseBody();
        assertThat(before).isNotNull();
        assertThat(before.getId()).isEqualTo(unitItemId);
        assertThat(before.getAttributes().stream()
                         .filter(a -> "values".equals(a.getKey()))
                         .flatMap(a -> a.getDetails().stream())
                         .noneMatch(v -> TEST_VALUE.equals(v.getValue())))
                .isTrue();

        final ItemDto actual = webClient.post()
                                        .uri("/references/" + unitItemId + "/" + unitItemAttributeId)
                                        .contentType(MediaType.TEXT_PLAIN)
                                        .accept(MediaType.APPLICATION_JSON)
                                        .bodyValue(TEST_VALUE)
                                        .exchange()
                                        .expectStatus().isOk()
                                        .expectBody(ItemDto.class)
                                        .returnResult()
                                        .getResponseBody();

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(unitItemId);
        assertThat(actual.getAttributes().stream()
                         .flatMap(a -> a.getDetails().stream())
                         .anyMatch(v -> TEST_VALUE.equals(v.getValue())))
                .isTrue();
    }

    // TODO
    //  Дублирование значения

    @Test
    @DisplayName("Add item attribute value with with 404 response status when item not found")
    void addItemAttributeValueShouldReturn404WhenItemNotFound() {
        webClient.post()
                 .uri("/references/" + UUID.randomUUID() + "/" + unitItemAttributeId)
                 .contentType(MediaType.TEXT_PLAIN)
                 .accept(MediaType.APPLICATION_JSON)
                 .bodyValue(TEST_VALUE)
                 .exchange()
                 .expectStatus().isNotFound();
    }

    @Test
    @DisplayName("Add item attribute value with with 404 response status when item attribute not found")
    void addItemAttributeValueShouldReturn404WhenItemAttributeNotFound() {
        webClient.post()
                 .uri("/references/" + unitItemId + "/" + UUID.randomUUID())
                 .contentType(MediaType.TEXT_PLAIN)
                 .accept(MediaType.APPLICATION_JSON)
                 .bodyValue(TEST_VALUE)
                 .exchange()
                 .expectStatus().isNotFound();
    }

    @Test
    @DisplayName("Add item attribute value with with 404 response status when item attribute is immutable")
    void addItemAttributeValueShouldReturn400WhenItemAttributeImmutable() {
        webClient.post()
                 .uri("/references/" + unitItemId + "/c498179f-6ee7-4912-b563-218796539f43")
                 .contentType(MediaType.TEXT_PLAIN)
                 .accept(MediaType.APPLICATION_JSON)
                 .bodyValue(TEST_VALUE)
                 .exchange()
                 .expectStatus().isBadRequest();
    }

}
