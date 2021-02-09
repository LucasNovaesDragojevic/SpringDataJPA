package br.com.springdata.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.springdata.orm.UnidadeTrabalho;
import br.com.springdata.repository.UnidadeTrabalhoRepository;

@Service
public class CrudUnidadeTrabalhoService 
{	
	private Boolean system;
	private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;
	
	public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
		this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
	}

	public void inicial(Scanner scanner) {
		system = true;
		while (system) {
			System.out.println("Qual ação de unidade de trabalho deseja executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - Deletar");
			Integer action = scanner.nextInt();
			switch (action) {
				case 1:
					salvar(scanner);
					break;
				case 2:
					atualizar(scanner);
					break;
				case 3:
					visualizar();
					break;
				case 4:
					deletar(scanner);
					break;
				default:
					system = false;
					break;
			}
		}
	}

	private void salvar(Scanner scanner) {
		System.out.println("Descrição da unidade de trabalho.");
		String decricao = scanner.next();
		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
		unidadeTrabalho.setDescricao(decricao);
		unidadeTrabalhoRepository.save(unidadeTrabalho);
		System.out.println("Salvo");
	}
	
	private void atualizar(Scanner scanner) {
		System.out.println("Id");
		Integer id = scanner.nextInt();
		System.out.println("Descrição da unidade de trabalho.");
		String descricao = scanner.next();
		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
		unidadeTrabalho.setId(id);
		unidadeTrabalho.setDescricao(descricao);
		unidadeTrabalhoRepository.save(unidadeTrabalho);
		System.out.println("Atualizado.");
	}
	
	private void visualizar() {
		Iterable<UnidadeTrabalho> unidadeTrabalhos = unidadeTrabalhoRepository.findAll();
		unidadeTrabalhos.forEach(System.out::println);
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Id");
		Integer id = scanner.nextInt();
		unidadeTrabalhoRepository.deleteById(id);
		System.out.println("Deletado.");
	}
}
