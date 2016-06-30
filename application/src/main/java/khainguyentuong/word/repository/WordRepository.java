package khainguyentuong.word.repository;

import java.util.List;

import khainguyentuong.word.model.Word;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface WordRepository extends CrudRepository<Word, Long> {

	public Word findByText(String text);

	@Query(value = "Select w From Word w")
	public List<Word> all();
}
