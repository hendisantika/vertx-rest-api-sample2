package id.my.hendisantika.vertx_rest_api_sample2.repository;

import javax.persistence.EntityManager;

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

}
