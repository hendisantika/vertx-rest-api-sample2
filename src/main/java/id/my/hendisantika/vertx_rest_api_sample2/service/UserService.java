package id.my.hendisantika.vertx_rest_api_sample2.service;

import id.my.hendisantika.vertx_rest_api_sample2.entity.User;
import id.my.hendisantika.vertx_rest_api_sample2.repository.UserRepository;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : vertx-rest-api-sample2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/01/25
 * Time: 08.24
 * To change this template use File | Settings | File Templates.
 */
public class UserService {

  /**
   * Resolved lazily so that deploying the verticle does not require a database connection.
   */
  private UserRepository repository() {
    return UserRepository.getInstance();
  }

  public void list(Handler<AsyncResult<List<User>>> handler) {
    try {
      handler.handle(Future.succeededFuture(repository().findAll()));
    } catch (Throwable ex) {
      handler.handle(Future.failedFuture(ex));
    }
  }

  public void getByFilter(JsonObject filter, Handler<AsyncResult<List<User>>> handler) {
    try {
      handler.handle(Future.succeededFuture(repository().getByFilter(filter)));
    } catch (Throwable ex) {
      handler.handle(Future.failedFuture(ex));
    }
  }

  public void getById(String cpf, Handler<AsyncResult<User>> handler) {
    try {
      handler.handle(Future.succeededFuture(repository().getById(cpf)));
    } catch (Throwable ex) {
      handler.handle(Future.failedFuture(ex));
    }
  }

  public void save(User newUser, Handler<AsyncResult<User>> handler) {
    try {
      User user = repository().getById(newUser.getCpf());

      if (user != null) {
        handler.handle(Future.failedFuture("Usuário já incluído."));
        return;
      }
      newUser.setStatus("A");
      repository().persist(newUser);
      handler.handle(Future.succeededFuture());
    } catch (Throwable ex) {
      handler.handle(Future.failedFuture(ex));
    }
  }

  public void update(User user, Handler<AsyncResult<User>> handler) {
    try {
      repository().merge(user);
      handler.handle(Future.succeededFuture());
    } catch (Throwable ex) {
      handler.handle(Future.failedFuture(ex));
    }
  }

  public void remove(String cpf, Handler<AsyncResult<User>> handler) {
    try {
      repository().removeById(cpf);
      handler.handle(Future.succeededFuture());
    } catch (Throwable ex) {
      handler.handle(Future.failedFuture(ex));
    }
  }

}
