package GabrielaCastillo_20240153.GabrielaCastillo_20240153.Models.DTO;

import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.logging.log4j.message.Message;
import org.hibernate.validator.constraints.UniqueElements;

@Getter @Setter
@EqualsAndHashCode @ToString
public class DTOProvedores {

    @NotNull(message = "El id no puede ser nulo o estar vacio")
    private Long id;

    @NotBlank(message ="El nombre del proveedor no puede estar vacio")
    @Size(max = 50, message = "Solo permite 50 caracteres")
    @UniqueElements
    private String nombreProvedor;

    @NotBlank(message ="El telefono del proveedor no puede estar vacio")
    @Size(max = 25, message = "Solo permite 25 caracteres")
    private String telefonoProveedor;

    @NotBlank(message ="la dirección del proveedor no puede estar vacio")
    private String direccionProverdor;

    @NotBlank(message ="El email del proveedor no puede estar vacio")
    @Email(message = "El correo electronico no es valido")
    private  String emailProveedor;

    @NotBlank(message ="El codigo del proveedor no puede estar vacio")
    private  String codigoProveedor;

    @NotNull(message = "El codigo del proveedor no puede ser nulo o estar vacio")
    @Min(value = 0, message = "El valor colo minimo tiene que ser 0")
    @Max(value = 1, message = "El estado solo puede ser 0 o 1")
    private Long estadoProveedor;

    private String comentarios;
}
