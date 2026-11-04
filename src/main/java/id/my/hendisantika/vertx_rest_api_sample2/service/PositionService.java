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
  private PositionRepository repository() {
    return PositionRepository.getInstance();
  }

  public void list(Handler<AsyncResult<List<Position>>> handler) {
    try {
      handler.handle(Future.succeededFuture(repository().findAll()));
    } catch (Throwable ex) {
      handler.handle(Future.failedFuture(ex));
    }
  }
}
