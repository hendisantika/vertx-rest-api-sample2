package id.my.hendisantika.vertx_rest_api_sample2.repository;

import id.my.hendisantika.vertx_rest_api_sample2.entity.Position;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by IntelliJ IDEA.
 * Project : vertx-rest-api-sample2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/01/25
 * Time: 08.17
 * To change this template use File | Settings | File Templates.
 */
public class PositionRepository {
  private static PositionRepository instance;
  protected EntityManager entityManager;

  private PositionRepository() {
    entityManager = getEntityManager();
  }

  public static PositionRepository getInstance() {
    if (instance == null) {
      instance = new PositionRepository();
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

  public Position getById(final int id) {
    return entityManager.find(Position.class, id);
  }
}
