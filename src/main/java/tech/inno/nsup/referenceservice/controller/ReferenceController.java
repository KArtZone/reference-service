package tech.inno.nsup.referenceservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.inno.nsup.referenceservice.domain.dto.ItemDto;
import tech.inno.nsup.referenceservice.service.ReferenceService;

import java.util.UUID;

/**
 * Контроллер взаимодействия с нормативно-справочной информацией
 */
@Slf4j
@RestController
@RequestMapping("/references")
@RequiredArgsConstructor
@Tag(name = "ReferenceController", description = "Методы по работе с нормативно-справочной информацией")
public class ReferenceController {

    private final ReferenceService referenceService;

    /**
     * @param itemId идентификатор справочника
     *
     * @return справочник
     */
    @Operation(description = "Получения справочника")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Справочник получен"),
            @ApiResponse(responseCode = "401", description = "Ошибка авторизации пользователя"),
            @ApiResponse(responseCode = "404", description = "Запрашиваемый ресурс не найден")
    })
    @GetMapping(value = "/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemDto read(@PathVariable UUID itemId) {
        log.info("Get reference by id: {}", itemId);
        return referenceService.get(itemId);
    }

    /**
     * @param itemId идентификатор справочника
     *
     * @return справочник
     */
    @Operation(description = "Создание новой записи справочника")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Новая запись добавлена в справочник"),
            @ApiResponse(responseCode = "400", description = "Справочник не может быть изменен"),
            @ApiResponse(responseCode = "404", description = "Запрашиваемый ресурс не найден")
    })
    @PostMapping(value = "/{itemId}/{attributeId}",
            consumes = MediaType.TEXT_PLAIN_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemDto addAttributeValue(@PathVariable UUID itemId,
                                     @PathVariable UUID attributeId,
                                     @RequestBody String value) {
        log.info("Update reference {} attribute {} by value {}", itemId, attributeId, value);
        return referenceService.addAttributeValue(itemId, attributeId, value);
    }

}
