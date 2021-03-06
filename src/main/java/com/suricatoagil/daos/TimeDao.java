package com.suricatoagil.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.suricatoagil.models.Time;
import com.suricatoagil.models.Usuario;

@Repository
public class TimeDao {

	@PersistenceContext
	private EntityManager manager;

	public void save(Time time) {
		manager.persist(time);
	}

	public Time load(Integer id) {
		return (Time) manager.createQuery("select t from Time t left join fetch t.integrantes  where t.id = :id")
				.setParameter("id", id)
				.getSingleResult();
	}

	public void atualiza(Time timeLoaded) {
		manager.merge(timeLoaded);
	}

	public void adicionaUsuarioNoTime(Usuario usuario, Time time) {
		manager.createNativeQuery("insert into time_usuario (time_id, usuario_id) values (:time, :usuario)")
			.setParameter("time", time.getId()).setParameter("usuario", usuario.getId()).executeUpdate();
	}
	
	
}
