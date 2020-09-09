package fourzeta;

import java.util.List;

import javax.persistence.EntityManager;

/**
 * 
 * @author Lucas Garais
 * 
 * @param <e>
 * 
 * Para rodar o RMI, execute a main dessa classe. 
 * Caso dê erro, elimine o processo que está ocupando a porta 5099
 * 
 * Instruções para eliminar o processo da porta 5099:
 * Unix: sudo kill -9 $(sudo lsof -t -i:5099)
 * Windows: 
 * Passo 1 - for /f "tokens=5 delims= " %%g in ('netstat -a -n -o ^| findstr 
 * Passo 2 - :5099 ^| findstr LISTENING') do TaskKill.exe /F /PID %%g
 *   
 */
 

public class GenericDAO<e> implements PersistService{

	public IElement save(IElement element){
		EntityManager entityManager = new ConnectionFactory().getConnection();

		try {
			entityManager.getTransaction().begin();
			if (element.getId() == 0) {
				entityManager.persist(element);
			} else {
				entityManager.merge(element);
			}
			entityManager.getTransaction().commit();

		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}
		return element;

	}

	@Override
	public IElement findbyId(IElement obj, int id){
		EntityManager entityManager = new ConnectionFactory().getConnection();
		IElement element = null;

		try {
			entityManager.getTransaction().begin();
			element = entityManager.find(obj.getClass(), id);
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			entityManager.close();
		}
		return element;
	}

	@Override
	public List<IElement> findAll(String info){
		EntityManager entityManager = new ConnectionFactory().getConnection();
		List<IElement> elementos = null;

		try {
			entityManager.getTransaction().begin();
			System.out.println("RETORNO: " + info);
			elementos = entityManager.createQuery("from " + info).getResultList();
			

		} catch (Exception e) {
			System.err.println(e);
		} finally {
			entityManager.close();
		}
		return elementos;

	}

	@Override
	public IElement remove(IElement obj, int id){
		EntityManager entityManager = new ConnectionFactory().getConnection();
		IElement elemento = null;
		try {

			elemento = entityManager.find(obj.getClass(), id);

			entityManager.getTransaction().begin();
			entityManager.remove(elemento);
			entityManager.getTransaction().commit();

		} catch (Exception e) {
			System.err.println(e);
			entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}
		return elemento;

	}

}
