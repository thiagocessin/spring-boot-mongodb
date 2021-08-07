package com.tcessin.workshopmongo.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.tcessin.workshopmongo.domain.Post;
import com.tcessin.workshopmongo.domain.User;
import com.tcessin.workshopmongo.dto.AuthorDTO;
import com.tcessin.workshopmongo.repository.PostRepository;
import com.tcessin.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {

		cargaInicial();
	}

	private void cargaInicial() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		userRepository.deleteAll();
		postRepository.deleteAll();

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		userRepository.saveAll(Arrays.asList(maria, alex, bob));

		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!",
				new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("10/03/2020"), "Bom dia ", "Tenha um bom dia", new AuthorDTO(maria));

		postRepository.saveAll(Arrays.asList(post1, post2));

		maria.getPosts().addAll(Arrays.asList(post1, post2));

		userRepository.save(maria);

	}

}
