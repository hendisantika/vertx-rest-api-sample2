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
  private final UserRepository userRepository = UserRepository.getInstance();

  public void list(Handler<AsyncResult<List<User>>> handler) {
    Future<List<User>> future = Future.future();
    future.setHandler(handler);

    try {
      List<User> result = userDao.findAll();
      future.complete(result);
    } catch (Throwable ex) {
      future.fail(ex);
    }
  }

  public void getByFilter(JsonObject filter, Handler<AsyncResult<List<User>>> handler) {
    Future<List<User>> future = Future.future();
    future.setHandler(handler);

    try {
      List<User> result = userDao.getByFilter(filter);
      future.complete(result);
    } catch (Exception ex) {
      future.fail(ex);
    }
  }

  public void getById(String cpf, Handler<AsyncResult<User>> handler) {
    Future<User> future = Future.future();
    future.setHandler(handler);

    try {
      User result = userDao.getById(cpf);
      future.complete(result);
    } catch (Throwable ex) {
      future.fail(ex);
    }
  }

  public void save(User newUser, Handler<AsyncResult<User>> handler) {
    Future<User> future = Future.future();
    future.setHandler(handler);

    try {
      User user = userDao.getById(newUser.getCpf());

      if (user != null) {
        future.fail("Usuário já incluído.");
        return;
      }
      newUser.setStatus("A");
      userDao.persist(newUser);
      future.complete();
    } catch (Throwable ex) {
      future.fail(ex);
    }
  }

  public void update(User user, Handler<AsyncResult<User>> handler) {
    Future<User> future = Future.future();
    future.setHandler(handler);

    try {
      userDao.merge(user);
      future.complete();
    } catch (Throwable ex) {
      future.fail(ex);
    }
  }

  public void remove(String cpf, Handler<AsyncResult<User>> handler) {
    Future<User> future = Future.future();
    future.setHandler(handler);

    try {
      userDao.removeById(cpf);
      future.complete();
    } catch (Throwable ex) {
      future.fail(ex);
    }
  }

}
