package br.edu.iftm.jdbctemplate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class JdbctemplateApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(JdbctemplateApplication.class, args);
	}

	@Autowired
	JdbcTemplate jdbcTemplate;
	private String telefone;

	@Override
	public void run(String... args) throws Exception {

		jdbcTemplate.execute("DROP TABLE contatos IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE contatos(id SERIAL, nome VARCHAR(255), telefone VARCHAR(255) )");

		jdbcTemplate.update("INSERT INTO contatos(nome,telefone) VALUES (?,?)", "Edson Angoti Júnior", "123");
		jdbcTemplate.update("INSERT INTO contatos(nome,telefone) VALUES (?,?)", "José Joaquim", "123");
		jdbcTemplate.update("INSERT INTO contatos(nome,telefone) VALUES (?,?)", "Maria Carolina", "123");

		List<Contato> contatos = jdbcTemplate.query("SELECT id, nome, telefone FROM contatos", (rs, rowNum) -> {
			return new Contato(rs.getLong("id"), rs.getString("nome"), rs.getString("telefone"));
		});
		System.out.println("Listando contatos");
		int i= 0;
		for (Contato contato : contatos) {

			i++;
			if (contato.getNome() == "Edson Angoti Júnior" ){
				telefone = contato.getTelefone();
			}
		}
		System.out.println(i);
		System.out.println(telefone);


	}

}
