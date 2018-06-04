package com.examplebr.edu.ifal.academia.academiatiweb.modelo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "nota")
public class Nota {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	private Aluno aluno;
	
	@ManyToOne
	private Disciplina disciplina;
	
	private double valor;
	
	@ElementCollection
	private List<Double> notas = new ArrayList<>();

	public Nota(double valor) {
		super();
	}
	
	@Override
	public String toString() {
	  return "Nota [Id=" + id + ", Aluno=" + aluno + ", Disciplina="+ disciplina+", Valor=" + valor + ",]";
	}
	
	public Nota(Aluno aluno, Disciplina disciplina, double valor) {
		super();
		this.aluno = aluno;
		this.disciplina = disciplina;
		this.valor = valor;
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Double> getNotas() {
		return notas;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	 @ElementCollection
		@CollectionTable(name = "nota_alunos")
		@Column(name = "valor_notas", length = 4, nullable = false)
		public List<Double> getNots() {
		return notas;
		}
		public void setNotas(List<Double> notas) {
		this.notas = notas;
 }

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((aluno == null) ? 0 : aluno.hashCode());
			result = prime * result + ((disciplina == null) ? 0 : disciplina.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((notas == null) ? 0 : notas.hashCode());
			long temp;
			temp = Double.doubleToLongBits(valor);
			result = prime * result + (int) (temp ^ (temp >>> 32));
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
			Nota other = (Nota) obj;
			if (aluno == null) {
				if (other.aluno != null)
					return false;
			} else if (!aluno.equals(other.aluno))
				return false;
			if (disciplina == null) {
				if (other.disciplina != null)
					return false;
			} else if (!disciplina.equals(other.disciplina))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (notas == null) {
				if (other.notas != null)
					return false;
			} else if (!notas.equals(other.notas))
				return false;
			if (Double.doubleToLongBits(valor) != Double.doubleToLongBits(other.valor))
				return false;
			return true;
		}
		
}
