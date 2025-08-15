package com.isc.api.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierRequestDTO {
	
	@NotBlank(message = "El nombre del negocio es obligatorio")
	@Pattern(
		    regexp = "^[\\p{L}\\p{N}\\s\\.,\\-#áéíóúÁÉÍÓÚñÑ]*$",
		    message = "El nombre del negocio no debe contener caracteres inválidos o emojis"
		)
    @Size(min = 3, max=150, message = "El nombre del negocio no puede tener menos de 3 caracteres y exceder los 150 ")
	private String businessName;
    
	@NotBlank(message = "La direccion es obligatoria")
	@Pattern(
		    regexp = "^[\\p{L}\\p{N}\\s\\.,\\-#áéíóúÁÉÍÓÚñÑ]*$",
		    message = "El nombre del negocio no debe contener caracteres inválidos o emojis"
		)
    @Size(min= 3, max = 200, message = "La direccion no puede exceder los 200 caracteres")
	private String address;
    
	@NotBlank(message = "El teléfono es obligatorio")
	@Pattern(
	    regexp = "^[0-9]{7,15}$",
	    message = "El teléfono debe contener únicamente entre 7 y 15 dígitos numéricos"
	)
	private String phone;
	
	@NotBlank(message = "El RUC es obligatorio")
	@Pattern(
	    regexp = "^\\d{10}001$",
	    message = "El RUC debe tener 13 dígitos numéricos y terminar en '001'"
	)
	private String ruc;
    
	@NotBlank(message = "El correo electrónico es obligatorio")
	@Size(max = 100, message = "El correo no debe superar los 100 caracteres")
	@Pattern(
	    regexp = "^[A-Za-z0-9+_.-]+@(.+)\\.(com|ec|net|org|edu|gob|mil|co|info|xyz)$",
	    message = "El correo debe ser válido y terminar en .com, .ec, .net, etc."
	)
	private String email;
	
	private SupplierTypeRequestDTO supplierType;
	
	private NationalityRequestDTO  nationality;

}
