import java.io.Serializable;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bsnpbag
 * 
 * @param <E>
 * @param <P>
 */
public class BaseDao<E, P extends Serializable> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected Class<E> entityClass;

	public BaseDao() {

	}

	public P save(E entity) {
		P p = null;
		return p;
	}

	public E update(E entity) {

		return entity;

	}

	public E delete(E entity) {

		return entity;

	}

	public E find(P pk) {

		E entity = null;
		return entity;

	}

	public Collection<E> findAll() {
		return null;
	}
}
