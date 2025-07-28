package GabrielaCastillo_20240153.GabrielaCastillo_20240153.Services;

import GabrielaCastillo_20240153.GabrielaCastillo_20240153.Entities.ProveedoresEntity;
import GabrielaCastillo_20240153.GabrielaCastillo_20240153.Exceptions.ExceptionErrorRegistrarProveedor;
import GabrielaCastillo_20240153.GabrielaCastillo_20240153.Exceptions.ExceptionIdProveedorNull;
import GabrielaCastillo_20240153.GabrielaCastillo_20240153.Exceptions.ExceptionProveedorNoEncontrado;
import GabrielaCastillo_20240153.GabrielaCastillo_20240153.Models.DTO.DTOProvedores;
import GabrielaCastillo_20240153.GabrielaCastillo_20240153.Repositories.ProveedoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


//con la etiqueta service spring reconoce que esta clase es la que se encargara de los metodos que se comunicaran con la base
@Service
public class ProveedoresService {

    @Autowired
    ProveedoresRepository repo; //Se inyecta en el service el Jpa repository el cul tiene los metodos save(), finAll(), findById(), etc, que nos ayudan a comunicarnos con la base

    //Metodo para obtener todos los proveedores sin filtrarlos
    public List<DTOProvedores> obtenerProveedores(){
        List<ProveedoresEntity> provedores = repo.findAll();

        //Stream nos sirve para separar los datos y que cada uno sea tomado de manera indiviaul
        return provedores.stream()
                //Antes de mostrar los proveedores que hemos obtenido ki tenemos que convertir a DTO ya que desde la base los datos vienen de tipo Entity y asi no se pueden mostrar en el Frontend
                .map(this::convertirAProvedoresDTO)
                .collect(Collectors.toList());

    }


    //Se convierte a dto para poderlo mostrar ya que los datos desde la BD viene de tipo entity y para mostrarlos en el frontend tiene que ser de tipo dto o json
    private DTOProvedores convertirAProvedoresDTO(ProveedoresEntity provedor) {

        //Colocamos un objeto del DTO para que en este se complete cada campo con los valores que vienne desde el entity cada uno de los atributos del dto que en los get estan vacios pasan a completarse con los valores que tare el entity
        DTOProvedores dto = new DTOProvedores();

        //Asignar cada valor de los atributos del entity a los del dto
        dto.setId(provedor.getId());
        dto.setNombreProvedor(provedor.getNombreProvedor());
        dto.setTelefonoProveedor(provedor.getTelefonoProveedor());
        dto.setDireccionProverdor(provedor.getDireccionProverdor());
        dto.setEmailProveedor(provedor.getEmailProveedor());
        dto.setCodigoProveedor(provedor.getCodigoProveedor());
        dto.setEstadoProveedor(provedor.getEstadoProveedor());
        dto.setComentarios(provedor.getComentarios());

        //Se retorna el dto ya con los valores que vienne  del entity
        return dto;
    }

    //Metodo para obtener proveedores segun su id, por medio de la url se manda el id del proveedor que queremos buscar
    public List<DTOProvedores> obtenerProveedoresporID(Long id_provedores){
        //Creamos un objeto de tipo optional en el que guardaremos el proveedor que encontremos por medio del metodo findbyId
        Optional<ProveedoresEntity> provedor = repo.findById(id_provedores);
        //Retornamos el proveedor pero antes lo tenemos que convertir en dto
        if (!provedor.isPresent()){
            throw new ExceptionProveedorNoEncontrado("El proveedor con id " + id_provedores + " no fue encontrado");
        }
        return provedor
                .map(entity -> List.of(convertirAProvedoresDTO(entity)))
                .orElse(List.of());
    }

    //Metodo insert
    public DTOProvedores insertarProveedores(DTOProvedores provedoresDTO){
        //Verificamos que los campos que son extrigtamente necesarios y que la base no permite null o empy esten llenos en este caso que exista un proveedor dto, con nombre y con estado
        if (provedoresDTO == null || provedoresDTO.getNombreProvedor().trim().isEmpty() || provedoresDTO.getEstadoProveedor() == null){
            throw new ExceptionErrorRegistrarProveedor("El nombre del proveedor o su estado esta vacio o es nulo");
        }
        try{
            //Convertimos el proveedor que actualmente es de tipo dto a un proveedor de tipo entity para que pueda interactuar con la base
            ProveedoresEntity provedorEntity = convertirAProvedorEntity(provedoresDTO);

            //Y procedemos a insertar nuestro proveedor con el metodo save que lo tiene nuestro jpaRepository
            ProveedoresEntity provedorGuardado = repo.save(provedorEntity);
            //Posteriormente convertimos a DTO para poder mostrar los datos el proveedor que hemos insertado
            return convertirAProvedoresDTO(provedorGuardado);
        } catch (Exception e) {
            //Si sucede algun error entra directamente al catch el cual nos enviara un error en el resgstro del proveedor y cual es el error
            System.out.println("Error al registrar al proveedor" + e.getMessage());
            //Por ultimo e retorna null
            return null;
        }
    }

    //metodo para convertir nuestro proveedor a entity, se pide un proveedor de tipo dto
    private ProveedoresEntity convertirAProvedorEntity(DTOProvedores dto) {
        //Creamos un objeto de tipo entity
        ProveedoresEntity entity = new ProveedoresEntity();

        //A cada atributo del entity se le aigna el valor que tiene ese mismo atributo en el dto en este caso

        //Nombre del proveedor
        entity.setNombreProvedor(dto.getNombreProvedor());
        //teefono del rpoveedor
        entity.setTelefonoProveedor(dto.getTelefonoProveedor());
        //direccion del proveedor
        entity.setDireccionProverdor(dto.getDireccionProverdor());
        //email del proveedor
        entity.setEmailProveedor(dto.getEmailProveedor());
        //codigo del proveedor
        entity.setCodigoProveedor(dto.getCodigoProveedor());
        //estado del proveedor
        entity.setEstadoProveedor(dto.getEstadoProveedor());
        //comentarios acerca del proveedor
        entity.setComentarios(dto.getComentarios());

        //etornamos el entity
        return entity;
    }

    //En el medoto actualizar necesitamos un proveedor de tipo dto y el ida del proveedor
    public DTOProvedores ActualizarProveedor(DTOProvedores dto, Long id_provedor){
        //Verificamos la existencia del rpoveedor por medio del metodo findbyid y e metodo "orElseThrow que si este no existe salta una excepcion de tipo proveedor no encontrado"
        ProveedoresEntity ProvedorExiste = repo.findById(id_provedor).orElseThrow(() -> new ExceptionProveedorNoEncontrado("Proveedor no encontrado"));

        //Se asigna al provedor existe (tipo entity) los valores nuevos de un proveedor tipo dto
        ProvedorExiste.setNombreProvedor(dto.getNombreProvedor());
        ProvedorExiste.setTelefonoProveedor(dto.getTelefonoProveedor());
        ProvedorExiste.setDireccionProverdor(dto.getDireccionProverdor());
        ProvedorExiste.setEmailProveedor(dto.getEmailProveedor());
        ProvedorExiste.setCodigoProveedor(dto.getCodigoProveedor());
        ProvedorExiste.setEstadoProveedor(dto.getEstadoProveedor());
        ProvedorExiste.setComentarios(dto.getComentarios());

        //Luego se hace otro objeto de tipo entity y cn el miso metodo que guardamos solo que en este caso reescribe el proveedor ya existente segun el id
        ProveedoresEntity ProveedorActualizado = repo.save(ProvedorExiste);
        //retornamos el proveedor de tipo pero antes lo convertimos a dto
        return convertirAProvedoresDTO(ProveedorActualizado);
    }

    //En el metodo eliminar necesitamos unicamente el id del proveedor para saber que proveedor es el que vamos a eliminar
    public  Boolean EliminarProveedor(Long id_proveedor){
        //verificamos que el id_provedor no este null
        if (id_proveedor == null){
            //S esta nulll nos tira una alerta de tipo idprovedornull
            throw new ExceptionIdProveedorNull("El ID del proveedor no debe ser null en la URL ");
        }
        //si no se procede con el metodo
        try{
            //Se verifica que elprovedor exista
            Optional<ProveedoresEntity> ExisteProveedor = repo.findById(id_proveedor);
            //Si este no existe se manda una alerta o excepcion que el proveedor no se encontro
            if (!ExisteProveedor.isPresent()){
                throw new ExceptionProveedorNoEncontrado("El proveedor con Id " + id_proveedor + " no existe");
            }
            //Si se encontro se elimina segun el id es decir eliminamos unicamente al proveedor que coincida con el id ingresado
            repo.deleteById(id_proveedor);
            //luego retornamos true
            return true;

        } catch (Exception e) {
            //Si sucede algun error al eliminar el proveedor nos dirige al catch el cual nos muestra el error
            System.out.println("Error al eliminar el proveedor " + e);
            //Retorna false
            return false;
        }
    }

}
