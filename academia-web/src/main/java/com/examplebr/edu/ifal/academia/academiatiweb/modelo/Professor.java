package com.examplebr.edu.ifal.academia.academiatiweb.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "professor")
public class Professor {

	@Id
	@GeneratedValue
	private Integer id;

	private String nome;

	@Column(length = 8, name = "matricula_professor", nullable = false)
	private String matricula;

	@Enumerated(EnumType.STRING)
	private Tipo_professor tipo_professor = Tipo_professor.efetivo;

	public Professor(String matricula, String nome, Tipo_professor tipo_professor) {
		super();
		this.matricula = matricula;
		this.nome = nome;
		this.tipo_professor = tipo_professor;
	}

	public Professor() {
		super();
	}

	@Override
	public String toString() {
		return "Professor [Id=" + id + ", matricula=" + matricula + ", nome=" + nome + ", tipo_professor="
				+ tipo_professor + "]";
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

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Tipo_professor getTipo_professor() {
		return tipo_professor;
	}

	public void setTipo_professor(Tipo_professor tipo_professor) {
		this.tipo_professor = tipo_professor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		result = prime * result + ((tipo_professor == null) ? 0 : tipo_professor.hashCode());
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
		Professor other = (Professor) obj;
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
		if (tipo_professor == null) {
			if (other.tipo_professor != null)
				return false;
		} else if (!tipo_professor.equals(other.tipo_professor))
			return false;

		return true;
	}
}
