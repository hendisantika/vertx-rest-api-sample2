package id.my.hendisantika.vertx_rest_api_sample2.service;

import id.my.hendisantika.vertx_rest_api_sample2.entity.Position;
import id.my.hendisantika.vertx_rest_api_sample2.repository.PositionRepository;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : vertx-rest-api-sample2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 12/01/25
 * Time: 08.23
 * To change this template use File | Settings | File Templates.
 */
public class PositionService {
  private final PositionRepository positionDao = PositionRepository.getInstance();

  public void list(Handler<AsyncResult<List<Position>>> handler) {
    Future<List<Position>> future = Future.future();
    future.setHandler(handler);

    try {
      List<Position> result = positionDao.findAll();
      future.complete(result);
    } catch (Throwable ex) {
      future.fail(ex);
    }
  }
}
