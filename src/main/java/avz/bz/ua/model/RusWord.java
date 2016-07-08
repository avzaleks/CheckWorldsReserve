package avz.bz.ua.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "rus_word")
public class RusWord extends Word {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private long id;
	private String word;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "relation_eng_rus", joinColumns = { @JoinColumn(name = "rus_word_id", nullable = false, updatable = false) },
	           inverseJoinColumns = { @JoinColumn(name = "eng_word_id", nullable = false, updatable = false) })
	private Set<EngWord> translations = new HashSet<EngWord>();

	public Set<EngWord> getTranslations() {
		return translations;
	}

	public void setTranslations(Set<EngWord> translations) {
		this.translations = translations;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

}
