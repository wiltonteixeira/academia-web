package com.examplebr.edu.ifal.academia.academiatiweb.modelo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
@Table(name = "disciplina")
public class Disciplina {

	@Id
	@GeneratedValue
	private Integer id;

	private String nome;

	@OneToMany
	private List<Aluno> alunos = new ArrayList<Aluno>();

	@OneToMany
	private List<Assunto> assuntos = new ArrayList<Assunto>();

	@ManyToMany
	private List<Professor> professores;

	
	public Disciplina() {
		super();
	}
	
	@Override
	public String toString() {
		return "Disciplina[Id=" + id + ", aluno=" + alunos + ", assunto=" + assuntos + ", professores="
				+ professores.toString() + "]";
	}
	
	
	public Disciplina(String nome) {
		super();
		this.nome = nome;
		
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public List<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

	public List<Assunto> getAssuntos() {
		return assuntos;
	}

	public void setAssuntos(List<Assunto> assuntos) {
		this.assuntos = assuntos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((alunos == null) ? 0 : alunos.hashCode());
		result = prime * result + ((professores == null) ? 0 : professores.hashCode());
		result = prime * result + ((assuntos == null) ? 0 : assuntos.hashCode());
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
		Disciplina other = (Disciplina) obj;
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
		if (alunos == null) {
			if (other.alunos != null)
				return false;
		} else if (!alunos.equals(other.alunos))
			return false;
		if (professores == null) {
			if (other.professores != null)
				return false;
		} else if (!professores.equals(other.professores))
			return false;
		if (assuntos == null) {
			if (other.assuntos != null)
				return false;
		} else if (!assuntos.equals(other.assuntos))
			return false;
		return true;
	}
}
