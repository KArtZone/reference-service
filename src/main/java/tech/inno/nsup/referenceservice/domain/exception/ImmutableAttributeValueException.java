package tech.inno.nsup.referenceservice.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Не найден запрашиваемый ресурс
 *
 * @author IVIvanov
 * @since 22.07.2022
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ImmutableAttributeValueException extends RuntimeException {
}
