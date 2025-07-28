package GabrielaCastillo_20240153.GabrielaCastillo_20240153.Controllers;

import GabrielaCastillo_20240153.GabrielaCastillo_20240153.Models.DTO.DTOProvedores;
import GabrielaCastillo_20240153.GabrielaCastillo_20240153.Services.ProveedoresService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
        return acceso.obtenerProveedores();
    }

    //Metodo para obetner provedores segun su ID
    @GetMapping("/ObtenerProveedoresID/{id_provedores}")
    public List<DTOProvedores> ObetnerProveedoresPorID(@PathVariable Long id_provedores){
        return acceso.obtenerProveedoresporID(id_provedores);
    }

    //Metodo de insert

}
