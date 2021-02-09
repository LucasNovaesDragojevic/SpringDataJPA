package br.com.springdata.orm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "funcionarios")
@Getter
@Setter
@ToString
public class Funcionario 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String cpf;
	private Double salario;
	private LocalDate dataContratacao;
	@ManyToOne(fetch = FetchType.EAGER)
	private Cargo cargo;
	@ManyToMany(fetch = FetchType.EAGER)
	private List<UnidadeTrabalho> unidadeTrabalhos = new ArrayList<>();
}
