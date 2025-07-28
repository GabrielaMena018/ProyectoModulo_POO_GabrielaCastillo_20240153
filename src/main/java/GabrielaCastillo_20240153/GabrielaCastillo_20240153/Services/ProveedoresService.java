package GabrielaCastillo_20240153.GabrielaCastillo_20240153.Services;

import GabrielaCastillo_20240153.GabrielaCastillo_20240153.Entities.ProveedoresEntity;
import GabrielaCastillo_20240153.GabrielaCastillo_20240153.Models.DTO.DTOProvedores;
import GabrielaCastillo_20240153.GabrielaCastillo_20240153.Repositories.ProveedoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProveedoresService {
    @Autowired
    ProveedoresRepository repo;

    //Metodo para obtener todos los proveedores sin filtrarlos
    public List<DTOProvedores> obtenerProveedores(){
        List<ProveedoresEntity> provedores = repo.findAll();

        //Stream nos sirve para separar los datos y que cada uno sea tomado de manera indiviaul
        return provedores.stream()
                .map(this::convertirAProvedoresDTO)
                .collect(Collectors.toList());

    }

    private DTOProvedores convertirAProvedoresDTO(ProveedoresEntity provedor) {
        DT
    }

}
