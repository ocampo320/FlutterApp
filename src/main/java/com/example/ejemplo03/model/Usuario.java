package com.example.ejemplo03.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table()

public class Usuario {

	@Id
	@Column()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUsuario;


	@Column()
	private String nombeUsuario;


	@Column()
	private String apellidoUsuario;

  @Column()
  private String emailUsuario;

  @Column()
  private String telefonoUsuario;

  @Column()
  private String direccionUsuario;

}
