package GabrielaCastillo_20240153.GabrielaCastillo_20240153.Controllers;

import GabrielaCastillo_20240153.GabrielaCastillo_20240153.Models.DTO.DTOProvedores;
import GabrielaCastillo_20240153.GabrielaCastillo_20240153.Services.ProveedoresService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/ApiAccionesProveedores")
public class ControllerProveedores {
    //Se inyecta el service el cual tiene todos los metdoos que nos conectan con la base ya que ahi se ha inyectado el jpaRepository
    @Autowired
    private ProveedoresService acceso;

    //Metodo get
    @GetMapping("/ObtenerProveedores")
    public List<DTOProvedores> ObetnerProveedores(){
        //en este metodo obtenems todos los provedores sin filtarlos o encontrando alguno en especifico
        return acceso.obtenerProveedores();
    }

    //Metodo para obetner provedores segun su ID, para este metodo necesitamos el id del proveedor que queremos ver
    @GetMapping("/ObtenerProveedoresID/{id_provedores}")
    //Como el id viene en la url necesitamos del metodo Pathvariable para extraerlo de la url
    public List<DTOProvedores> ObetnerProveedoresPorID(@PathVariable Long id_provedores){
        //Se accede al metodo obtener proveedores por id y se le manda el id del proveedor que queremos ver
        return acceso.obtenerProveedoresporID(id_provedores);
    }

    //Metodo de insert
    @PostMapping("/IngresarProvedor")
    //Aqui va a regresar un requestbody lo que quiere decir que regresara un json con un cuerpo o una respuesta con un cuerpo
    public ResponseEntity<Map<String, Object>> RegistrarProveedor(@Valid @RequestBody DTOProvedores proveedor, HttpServletRequest request){
        try{
            //Para insertar necesitamos los dtos del provedor que va ser de tipo dto y este lo madamos al metodo insertarproveedor
            DTOProvedores respuesta = acceso.insertarProveedores(proveedor);
            //Si la respuesta es null significa que la inserción no se pudo completar por lo que se muestra el siguiente mensaje
            if (respuesta == null){
                //este es el cuerpo del mensaje que se enviara al frontend
                return  ResponseEntity.badRequest().body(Map.of(
                        "Status", "Inserción incorrecta",
                        "ErrorType", "VALIDATION_ERROR",
                        "Message", "Datos del proveedor invalidos"

                ));
            }
            //Si la respuesta no es null se manda un cuerpo con un codigo 201 que significa que el proveedor o el registro se ha creado
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "Satus","Succes",
                    "data", respuesta
            ));
        } catch (Exception e) {
            //Cualquier error interno se dirigue al catch y muestra el error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "Status", "error",
                    "Message", "Error al registrar el usuario",
                    "desatils", e.getMessage()
            ));
        }
    }

    //Metodo DELETE necesitamos el id del proveedor que queremos eliminar el cual viene en el url
    @DeleteMapping("/EliminarProveedor/{id_proveedor}")
    //PAra sacarlo del url necesitamos del pahvariabe el cual lo almacena en id_proveedir
    public  ResponseEntity<Map<String,String>> EliminarProveedor(@PathVariable Long id_proveedor){
        //Luego ese id se le envia al metodo eliminar proveedor y se esperara una respuesta de tipo true o fale
        boolean respuesta = acceso.EliminarProveedor(id_proveedor);

        //Si la respuesta es true manda un mensaje de tipo ok es decir 200 el cual avisa a nuestro frontend que todo a sido exitoso
        if (respuesta ){
            return ResponseEntity.ok().body(Map.of(
                    "Status", "SUCCESS",
                    "Message", "Se elimino exitosamente"
            ));

        }else {
            //si la respuesta es error nos mostrara el error al eliminar al proveedor
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
               "Status", "ERROR",
                    "Message", "Error al eliminar al proveedor"
            ));
        }
    }

    //Metodo Update para este metodo necesitamos tando los nuevos datos del proveedor como el id del proveedor que necesitamos actualizar
    @PutMapping("/ActualizarProveedores/{id_proveedor}")
    //Estos se extraen de la url gracias al pathvariable y se almacenan en sus respectivamos variables
    public  ResponseEntity<Map<String, Object>> actualizarProveedores(@PathVariable Long id_proveedor, @Valid @RequestBody DTOProvedores proveedor){
        try{
            //Se mandan los datos del proveedor y el id del proveedor al metodo actualizar proveedor
            DTOProvedores respuesta = acceso.ActualizarProveedor(proveedor, id_proveedor);
            //Luego se verifica la respuesta si la espuesta es null significa que el proveedor no se pudo actualizar
            if (respuesta == null){
                return  ResponseEntity.badRequest().body(Map.of(
                        "Status", "Actualización incorrecta",
                        "ErrorType", "VALIDATION_ERROR",
                        "Message", "Datos del proveedor invalidos"

                ));
            }
            //Pero si la respuesta no es null significa que el proveedor fue actualziado correctamente
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "Satus","Succes",
                    "Message", "Usuario Actualizado correctamente",
                    "data", respuesta
            ));
        }catch (Exception e) {
            //cualquier error interno se manejara por medio del catch el cual nos mostrara el error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "Status", "error",
                    "Message", "Error al registrar el usuario",
                    "desatils", e.getMessage()
            ));
        }
    }
}
