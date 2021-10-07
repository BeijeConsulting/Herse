package it.beije.herse.db.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

import it.beije.herse.file.Contatto;

public class CriteriaTest {

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("herse-rubrica");
	private static EntityManager entityManager;
	
	public static void main(String[] args) {
		
		entityManager = entityManagerFactory.createEntityManager();
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Contatto> cr = cb.createQuery(Contatto.class);
		Root<Contatto> root = cr.from(Contatto.class);
//		EntityType<Contatto> entity = (EntityType<Contatto>) new Contatto();
//		Root<Contatto> root = cr.from(entity);
		cr.select(root).groupBy(root.get("id")).having(cb.gt(root.get("id"), 0));
		cr.orderBy(cb.asc(root.get("nome")));
		
//		CriteriaQuery<Tuple> tq = cb.createQuery(Tuple.class);
//		Root<Contatto> tqRoot = tq.from(Contatto.class);
//		tq.select(cb.tuple(tqRoot.get("id"), tqRoot.get("nome")));

		Query query = entityManager.createQuery(cr);
		List<Contatto> rubrica = query.getResultList();
//		List<Tuple> rubrica = query.getResultList();
		
		rubrica = query.getResultList(); 
		entityManager.close();
		
		for(Contatto c : rubrica) System.out.println(c);
//		for(Tuple c : rubrica) System.out.println("id: "+c.get(0)+", nome: "+c.get(1));
	}

}
