package id.my.hendisantika.vertx_rest_api_sample2.repository;

import id.my.hendisantika.vertx_rest_api_sample2.entity.User;
import io.vertx.core.json.JsonObject;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : vertx-rest-api-sample2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/01/25
 * Time: 08.18
 * To change this template use File | Settings | File Templates.
 */
public class UserRepository {
  private static UserRepository instance;
  protected EntityManager entityManager;

  private UserRepository() {
    entityManager = getEntityManager();
  }

  public static UserRepository getInstance() {
    if (instance == null) {
      instance = new UserRepository();
    }

    return instance;
  }

  private EntityManager getEntityManager() {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("crudHibernatePU");
    if (entityManager == null) {
      entityManager = factory.createEntityManager();
    }

    return entityManager;
  }

  public User getById(String cpf) {
    Object result = entityManager.find(User.class, cpf);
    if (result != null) {
      return (User) result;
    } else {
      return null;
    }
  }

  @SuppressWarnings("unchecked")
  public List<User> findAll() {
    return entityManager.createQuery("FROM " + User.class.getName()).getResultList();
  }

  public List<User> getByFilter(JsonObject filter) {
    Query query = entityManager.createQuery(sqlFilter(filter));
    parametersFilter(filter, query);
    List<User> result = query.getResultList();

    return result;
  }

}
