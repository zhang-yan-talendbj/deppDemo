//package model;
//
//import java.io.Serializable;
//import java.lang.reflect.ParameterizedType;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Iterator;
//
//import org.apache.openjpa.persistence.Extent;
//import org.apache.openjpa.persistence.OpenJPAEntityManager;
//
//public abstract class BaseRepositoryManager<Entity, UniqueKey>  implements RepositoryManager<Entity, UniqueKey>, Serializable {
//
//    static final long serialVersionUID = 6356058948823862078L;
//    
//    /** The class of entity we will be persisting. */
//    protected final Class<Entity> persistentClass;
//    
//    protected BaseRepositoryManagerUtils baseRepositoryManagerUtils;
//
//    @SuppressWarnings("unchecked")
//    public BaseRepositoryManager(BaseRepositoryManagerUtils baseRepositoryManagerUtils) {
//        ParameterizedType t = (ParameterizedType) getClass().getGenericSuperclass();
//        this.persistentClass = (Class<Entity>) t.getActualTypeArguments()[0];
//        this.baseRepositoryManagerUtils = baseRepositoryManagerUtils;
//    }
//    
//    @Override
//    public Entity create(Entity object) {
//        OpenJPAEntityManager openJPAEntityManager = baseRepositoryManagerUtils.retrieveOpenJPAEntityManager();
//        openJPAEntityManager.persist(object);
//        return object;    
//    }
//
//    @Override
//    public Entity find(UniqueKey id) {
//        OpenJPAEntityManager openJPAEntityManager = baseRepositoryManagerUtils.retrieveOpenJPAEntityManager();
//        Entity object = openJPAEntityManager.find(persistentClass, id);
//        return object;
//    }
//
//    @Override
//    public Entity update(Entity object) {
//        OpenJPAEntityManager openJPAEntityManager = baseRepositoryManagerUtils.retrieveOpenJPAEntityManager();
//        openJPAEntityManager.merge(object);
//        return object;    
//    }
//
//    @Override
//    public Collection<Entity> findAll() {
//        OpenJPAEntityManager openJPAEntityManager = baseRepositoryManagerUtils.retrieveOpenJPAEntityManager();
//        Extent daoExtent = openJPAEntityManager.createExtent(persistentClass, true);
//        Collection<Entity> entities = new ArrayList();
//        for (Iterator<Entity> i = daoExtent.iterator(); i.hasNext();) {
//            Entity entity =  i.next();
//            entities.add(entity);
//        }
//        daoExtent.closeAll();
//        return openJPAEntityManager.detachAll(entities);
//    }
//    
//}
