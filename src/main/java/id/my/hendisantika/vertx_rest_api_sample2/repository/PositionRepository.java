package id.my.hendisantika.vertx_rest_api_sample2.repository;

import javax.persistence.EntityManager;

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
}
