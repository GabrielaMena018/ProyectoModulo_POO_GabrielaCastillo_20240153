package GabrielaCastillo_20240153.GabrielaCastillo_20240153.Entities;

import jakarta.persistence.*;
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
    //Aqui se sabe que el id se generara por medio de una secuencia
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_provider")
    //y aqui se coloca de que secuencia de la base viene ese numero y que se ira incrementando de 1 en 1
    @SequenceGenerator(name = "seq_provider", sequenceName = "seq_provider", allocationSize = 1)
    @Column(name = "PROVIDERID")
    private Long id;

    //Coluna  providerName
    @Column(unique = true, name = "PROVIDERNAME")
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
    @Column(unique = true, name = "PROVIDERCODE")
    private  String codigoProveedor;

    //Columna   providerStatus
    @Column(name = "PROVIDERSTATUS")
    private Long estadoProveedor;

    //Columna providerComments
    @Column(name = "PROVIDERCOMMENTS")
    private String comentarios;
}
