package GabrielaCastillo_20240153.GabrielaCastillo_20240153.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter
@ToString @EqualsAndHashCode
@Table(name = "TBPROVIDER")
public class ProveedoresEntity {

    //LLAVE PRIMARIA COLUMNA  providerID
    @Id
    @Column(name = "PROVIDERID")
    private Long id;

    //Coluna  providerName
    @Column(name = "PROVIDERNAME")
    private String nombreProvedor;

    //Columna  providerPhone
    @Column(name = "PROVIDERPHONE")
    private String telefonoProveedor;

    //Columna providerAddress
    @Column(name = "PROVIDERADDRESS")
    private String direccionProverdor;

    //Columna  providerEmail
    @Column(name = "PROVIDEREMAIL")
    private  String emailProveedor;

    //Columna  providerCode
    @Column(name = "PROVIDERCODE")
    private  String codigoProveedor;

    //Columna   providerStatus
    @Column(name = "PROVIDERSTATUS")
    private Long estadoProveedor;

    //Columna providerComments
    @Column(name = "PROVIDERCOMMENTS")
    private String comentarios;
}
