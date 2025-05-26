package com.isc.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String name;

    @NotBlank(message = "El callback es obligatorio")
    private String callback;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 100, message = "La descripción no puede tener más de 100 caracteres")
    private String description;

    private Integer gitlabRepositoryId;

    @Size(max = 10, message = "La versión actual no puede tener más de 10 caracteres")
    private String currentVersion;

    private Integer projectId;

}
