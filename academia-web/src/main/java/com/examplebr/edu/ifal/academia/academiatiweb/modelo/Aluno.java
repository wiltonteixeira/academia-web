package com.examplebr.edu.ifal.academia.academiatiweb.modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "aluno")
public class Aluno {
	@Id
	@GeneratedValue
	private Integer id;

	private String nome;

	@Column(unique = true, name = "matricula_aluno", nullable = false)
	private String matricula;

	@ManyToMany
	private Set<Disciplina> disciplinas = new HashSet<>();

	@Embedded
	private Endereco endereco = new Endereco();

	@ElementCollection
	@CollectionTable(name = "telefone_aluno", joinColumns = @JoinColumn(name = "id_aluno"))
	@Column(length = 12, name = "numero_aluno", nullable = false)
	private java.util.List<String> telefones = new ArrayList<>();

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_aluno")
	private TIPO_ALUNO tipoAluno = TIPO_ALUNO.BOLSISTA_ASSISTENCIA;

	public Aluno() {
		super();
	}

	@Override
	public String toString() {
		return "Aluno [Id=" + id + ", nome=" + nome + ", matricula=" + matricula + "," + "disciplina=" + disciplinas
				+ ", tipoAluno=" + tipoAluno + "]";
	}
	
	
	public Aluno(String nome, String matricula, Set<Disciplina> disciplinas, Endereco endereco, List<String> telefones,
			TIPO_ALUNO tipoAluno) {
		super();
		this.nome = nome;
		this.matricula = matricula;
		this.disciplinas = disciplinas;
		this.endereco = endereco;
		this.telefones = telefones;
		this.tipoAluno = tipoAluno;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Set<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(Set<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public java.util.List<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(java.util.List<String> telefones) {
		this.telefones = telefones;
	}

	public TIPO_ALUNO getTipoAluno() {
		return tipoAluno;
	}

	public void setTipoAluno(TIPO_ALUNO tipoAluno) {
		this.tipoAluno = tipoAluno;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		result = prime * result + ((disciplinas == null) ? 0 : disciplinas.hashCode());	
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((telefones == null) ? 0 : telefones.hashCode());
		result = prime * result + ((tipoAluno == null) ? 0 : tipoAluno.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		if (disciplinas == null) {
			if (other.disciplinas != null)
				return false;
		} else if (!disciplinas.equals(other.disciplinas))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (telefones == null) {
			if (other.telefones != null)
				return false;
		} else if (!telefones.equals(other.telefones))
			return false;
		if (tipoAluno == null) {
			if (other.tipoAluno != null)
				return false;
		} else if (!tipoAluno.equals(other.tipoAluno))
			return false;

		return true;
	}

}
