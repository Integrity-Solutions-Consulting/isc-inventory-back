package com.isc.api.dto.request;

import java.time.LocalDate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDTO {
	
	@NotNull(message = "El id del cliente es obligatorio")
	private Integer id;
	
	@NotNull(message = "El tipo de identificación es obligatorio")
	private Integer idIdentificationType;
    
	@NotNull(message = "El genero es obligatorio")
	private Integer idGender;
    
	@NotNull(message = "La posicion es obligatorio")
	private Integer idPosition;
    
	@NotNull(message = "El modo de trabajo es obligatorio")
	private Integer idWorkMode;
    
	@NotNull(message = "La nacionalidad es obligatoria")
	private Integer idNationality;
    
	@NotBlank(message = "El nombre es obligatorio")
    @Size(max = 80, message = "El nombre no puede tener más de 80 caracteres")
	private String firstName;
    
	@NotBlank(message = "El apellido es obligatorio")
    @Size(max = 80, message = "El apellido no puede tener más de 80 caracteres")
	private String lastName;
    
	@NotBlank(message = "La identificacion es obligatoria")
    @Size(max = 13, message = "La identificacion no puede tener más de 13 caracteres")
	private String identification;
    
	@Pattern(
	        regexp = "^\\+?[0-9\\s\\-]{7,10}$",
	        message = "El teléfono debe tener entre 7 y 10 caracteres, y contener solo dígitos, espacios o guiones"
	    )
	private String phone;
    
	@Email(message = "Debe proporcionar un correo electrónico válido")
    @Size(max = 100, message = "El correo electrónico no puede tener más de 100 caracteres")
	@NotBlank
	private String email;
    
	@Size(max = 255, message = "La dirección no puede tener más de 255 caracteres")
	private String address;
    
	@NotNull(message = "La fecha de inicio de contrato es obligatoria")

	private LocalDate contractDate;
    
	private LocalDate contractEndDate;
}
