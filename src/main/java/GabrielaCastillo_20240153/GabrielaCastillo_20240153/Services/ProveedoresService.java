package GabrielaCastillo_20240153.GabrielaCastillo_20240153.Services;

import GabrielaCastillo_20240153.GabrielaCastillo_20240153.Entities.ProveedoresEntity;
import GabrielaCastillo_20240153.GabrielaCastillo_20240153.Models.DTO.DTOProvedores;
import GabrielaCastillo_20240153.GabrielaCastillo_20240153.Repositories.ProveedoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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


    //Se convierte a dto para poderlo mostrar ya que los datos desde la BD viene de tipo entity y para mostrarlos en el frontend tiene que ser de tipo dto o json
    private DTOProvedores convertirAProvedoresDTO(ProveedoresEntity provedor) {

        DTOProvedores dto = new DTOProvedores();
        dto.setId(provedor.getId());
        dto.setNombreProvedor(provedor.getNombreProvedor());
        dto.setTelefonoProveedor(provedor.getTelefonoProveedor());
        dto.setDireccionProverdor(provedor.getDireccionProverdor());
        dto.setEmailProveedor(provedor.getEmailProveedor());
        dto.setCodigoProveedor(provedor.getCodigoProveedor());
        dto.setEstadoProveedor(provedor.getEstadoProveedor());
        dto.setComentarios(provedor.getComentarios());

        return dto;
    }

    //Metodo para obtener proveedores segun su id
    public List<DTOProvedores> obtenerProveedoresporID(Long id_provedores){
        Optional<ProveedoresEntity> provedor = repo.findById(id_provedores);
        return provedor
                .map(entity -> List.of(convertirAProvedoresDTO(entity)))
                .orElse(List.of());
    }

    //Metodo insert
    public DTOProvedores insertarProveedores(DTOProvedores provedoresDTO){
        if (provedoresDTO == null || provedoresDTO.getNombreProvedor().trim().isEmpty() || provedoresDTO.getEstadoProveedor() == null){
            throw new IllegalArgumentException("El nombre del proveedor o su estado esta vacio o es nulo");
        }
        try{
            ProveedoresEntity provedorEntity = convertirAProvedorEntity(provedoresDTO);

            ProveedoresEntity provedorGuardado = repo.save(provedorEntity);
            return convertirAProvedoresDTO(provedorGuardado);
        } catch (Exception e) {
            System.out.println("Error al registrar al proveedor" + e.getMessage());
            return null;
        }
    }

    private ProveedoresEntity convertirAProvedorEntity(DTOProvedores dto) {
        ProveedoresEntity entity = new ProveedoresEntity();

        entity.setNombreProvedor(dto.getNombreProvedor());
        entity.setTelefonoProveedor(dto.getTelefonoProveedor());
        entity.setDireccionProverdor(dto.getDireccionProverdor());
        entity.setEmailProveedor(dto.getEmailProveedor());
        entity.setCodigoProveedor(dto.getCodigoProveedor());
        entity.setEstadoProveedor(dto.getEstadoProveedor());
        entity.setComentarios(dto.getComentarios());

        return entity;
    }

}
