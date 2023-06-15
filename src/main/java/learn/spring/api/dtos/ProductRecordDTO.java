package learn.spring.api.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRecordDTO(@NotBlank String name, @NotNull BigDecimal value) {
    //Por padrão, seus atributos já são final e private
}
