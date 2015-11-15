package br.usp.suricato.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank(message="Usuário não pode ser vazio")
	@Column(unique = true)
	private String nome;

	@NotBlank(message="Senha não pode ser vazia")
	private String senha;
	
	@ManyToOne
	@NotNull
	private Permissao permissao;
	
	private boolean ativo = true;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="Time_Usuario", joinColumns=@JoinColumn(name="usuario_id"), inverseJoinColumns=@JoinColumn(name="time_id"))
	private List<Time> times = new ArrayList<>();
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="criador")
	private List<Retrospectiva> retrospectivas;

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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Permissao getPermissao() {
		return permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public List<Time> getTimes() {
		return times;
	}

	public void setTimes(List<Time> times) {
		this.times = times;
	}

	public List<Retrospectiva> getRetrospectivas() {
		return retrospectivas;
	}

	public void setRestrospectivas(List<Retrospectiva> restrospectivas) {
		this.retrospectivas = restrospectivas;
	}
	
	public List<Retrospectiva> getRetrospectivasAbertas() {
		return this.retrospectivas.stream().filter(retro -> retro.isAberta()).collect(Collectors.toList());
	}
	
	public List<Retrospectiva> getRetrospectivasEncerradas() {
		return this.retrospectivas.stream().filter(retro -> !retro.isAberta()).collect(Collectors.toList());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
