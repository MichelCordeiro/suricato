package br.usp.suricato.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.usp.suricato.models.Comentario;
import br.usp.suricato.models.PostIt;
import br.usp.suricato.models.Retrospectiva;

@Repository
public class RetrospectivaDao {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PostItDao postItDao;
	
	@Autowired
	private ComentarioDao comentarioDao;

	public void salva(Retrospectiva retrospectiva) {
		manager.persist(retrospectiva);
	}

	public void atualiza(Retrospectiva retrospectiva) {
		for (PostIt postIt : retrospectiva.getPostIts()) {
			postIt.setRetrospectiva(retrospectiva);
			postItDao.saveOrUpdate(postIt);
		}
		for (Comentario comentario : retrospectiva.getComentarios()) {
			comentario.setRetrospectiva(retrospectiva);
			comentarioDao.saveOrUpdate(comentario);
		}
		manager.merge(retrospectiva);
	}

	public List<Retrospectiva> listaRetrospectivasDoUsuario(String nome) {
		return manager.createQuery("select r from Retrospectiva r where r.criador.nome = :nome", Retrospectiva.class)
					.setParameter("nome", nome)
					.getResultList();
	}

	public Retrospectiva load(Integer id) {
		return manager.createQuery("select r from Retrospectiva r where r.id = :id", Retrospectiva.class)
				.setParameter("id", id)
				.getSingleResult();
	}
	
}
