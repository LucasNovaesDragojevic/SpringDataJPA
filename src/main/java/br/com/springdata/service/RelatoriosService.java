package br.com.springdata.service;

import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.springdata.orm.Funcionario;
import br.com.springdata.repository.FuncionarioRepository;

@Service
public class RelatoriosService 
{
	private Boolean system;
	
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
			Integer action = scanner.nextInt();
			switch (action) {
				case 1:
					buscaFuncionarioNome(scanner);
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
}
