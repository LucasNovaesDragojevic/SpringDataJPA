package br.com.springdata.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.springdata.orm.Funcionario;
import br.com.springdata.repository.FuncionarioRepository;
import br.com.springdata.specification.FuncionarioSpecification;

@Service
public class RelatorioFuncionarioDinamicoService
{
	private final FuncionarioRepository funcionarioRepository;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");

	public RelatorioFuncionarioDinamicoService(FuncionarioRepository funcionarioRepository)
	{
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial(Scanner scanner)
	{
		System.out.println("Digite o nome");
		String nome = scanner.next();
		if (nome.equalsIgnoreCase("NULL"))
			nome = null;
		
		System.out.println("Digite o CPF");
		String cpf = scanner.next();
		if (cpf.equalsIgnoreCase("NULL"))
			cpf = null;
		
		System.out.println("Digite o salário");
		Double salario = scanner.nextDouble();
		if (salario == 0)
			salario = null;
		
		System.out.println("Digite a data de contratação");
		String data = scanner.next();
		LocalDate dataContratacao;
		if (data.equalsIgnoreCase("NULL"))
			dataContratacao = null;
		else
			dataContratacao = LocalDate.parse(data, formatter);
		
		List<Funcionario> funcionarios = funcionarioRepository.findAll(Specification
				.where(
						FuncionarioSpecification.nome(nome))
						.or(FuncionarioSpecification.cpf(cpf))
						.or(FuncionarioSpecification.salario(salario))
						.or(FuncionarioSpecification.dataContratacao(dataContratacao))
				);
		funcionarios.forEach(System.out::println);
	}
}