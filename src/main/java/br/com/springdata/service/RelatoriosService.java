package br.com.springdata.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.springdata.orm.Funcionario;
import br.com.springdata.orm.FuncionarioProjecao;
import br.com.springdata.repository.FuncionarioRepository;

@Service
public class RelatoriosService 
{
	private Boolean system;
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private final FuncionarioRepository funcionarioRepository;

	public RelatoriosService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}

	public void inicial(Scanner scanner) {
		system = true;
		while (system) {
			System.out.println();
			System.out.println("Qual ação de cargo deseja executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Busca funcionario nome");
			System.out.println("2 - Busca funcionario nome, data contratação e salário maior que");
			System.out.println("3 - Busca funcionario pela data de contratação");
			System.out.println("4 - Pesquisa salários de funcionários");
			Integer action = scanner.nextInt();
			switch (action) {
				case 1:
					buscaFuncionarioNome(scanner);
					break;
				case 2:
					buscaFuncionarioNomeSalarioMaiorData(scanner);
					break;
				case 3:
					buscaFuncionarioDataContratacao(scanner);
					break;
				case 4:
					pesquisaFuncionarioSalario();
					break;
				default:
					system = false;
					break;
			}
		}
	}

	private void buscaFuncionarioNome(Scanner scanner) {
		System.out.println("Qual o nome do funcionário?");
		String nome = scanner.next();
		List<Funcionario> funcionarios = funcionarioRepository.findByNome(nome);
		funcionarios.forEach(System.out::println);
	}
	
	private void buscaFuncionarioNomeSalarioMaiorData(Scanner scanner) {
		System.out.println("Qual o nome do funcionário?");
		String nome = scanner.next();
		
		System.out.println("Qual a data de contratação?");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, formatter);
		
		System.out.println("Qual o salário?");
		Double salario = scanner.nextDouble();
		
		List<Funcionario> funcionarios = funcionarioRepository.findNomeSalarioMaiorDataContratacao(nome, salario, localDate);
		
		funcionarios.forEach(System.out::println);
	}
	
	private void buscaFuncionarioDataContratacao(Scanner scanner) {
		System.out.println("Qual a data de contratação que deseja pesquisar?");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, formatter);
		List<Funcionario> list = funcionarioRepository.findDataContratacaoMaior(localDate);
		list.forEach(System.out::println);
	}
	
	private void pesquisaFuncionarioSalario() {
		List<FuncionarioProjecao> funcionarios = funcionarioRepository.findFuncionarioSalario();
		funcionarios.forEach(funcionario -> System.out.println("Funcionário: " + funcionario.getId() + " " + funcionario.getNome() + " " + funcionario.getSalario()));
	}
}
