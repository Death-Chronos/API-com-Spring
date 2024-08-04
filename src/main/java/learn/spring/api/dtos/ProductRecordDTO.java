package learn.spring.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRecordDTO(@NotBlank String name, @NotNull Double value) {
    //Por padrão, seus atributos já são final e private
}
