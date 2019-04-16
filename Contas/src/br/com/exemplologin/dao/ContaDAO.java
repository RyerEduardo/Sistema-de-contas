/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.exemplologin.dao;

import br.com.exemplologin.controller.UsuarioController;
import br.com.exemplologin.dao.exceptions.NonexistentEntityException;
import br.com.exemplologin.model.Conta;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Eduardo
 */
public class ContaDAO implements Serializable {

    public ContaDAO() {
        this.emf = Persistence.createEntityManagerFactory("ExemploLoginPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Conta conta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(conta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Conta conta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            conta = em.merge(conta);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = conta.getId();
                if (findConta(id) == null) {
                    throw new NonexistentEntityException("The conta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Conta conta;
            try {
                conta = em.getReference(Conta.class, id);
                conta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The conta with id " + id + " no longer exists.", enfe);
            }
            em.remove(conta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Conta> findContaEntities() {
        return findContaEntities(true, -1, -1);
    }

    public List<Conta> findContaEntities(int maxResults, int firstResult) {
        return findContaEntities(false, maxResults, firstResult);
    }

    private List<Conta> findContaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Conta.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Conta> findConta(int mes) {
        UsuarioController uc = new UsuarioController();
        
        try {
            EntityManager em = getEntityManager();
            Query q = em.createNamedQuery("conta.listarMes");
            q.setParameter("mes", mes);
            q.setParameter("id", uc.getId());
            return q.getResultList();
        } catch(Exception e) {
            return null;
        }
    }
    public List<Conta> listar() {
        UsuarioController uc = new UsuarioController();
        
        try {
            EntityManager em = getEntityManager();
            Query q = em.createNamedQuery("conta.listar");
            q.setParameter("id", uc.getId());
            return q.getResultList();
        } catch(Exception e) {
            return null;
        }
    }
    /*
    public Conta findConta(Long id) {            original
        EntityManager em = getEntityManager();
        try {
            return em.find(Conta.class, id);
        } finally {
            em.close();
        }
    }
    */
    
    public int getContaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Conta> rt = cq.from(Conta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
