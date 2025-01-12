package id.my.hendisantika.vertx_rest_api_sample2;

import id.my.hendisantika.vertx_rest_api_sample2.service.PositionService;
import id.my.hendisantika.vertx_rest_api_sample2.service.UserService;
import io.netty.handler.codec.http.HttpMethod;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.Json;

import java.util.HashSet;
import java.util.Set;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Future<Void> fut) {
    Router router = Router.router(vertx); // <1>
    // CORS support
    Set<String> allowHeaders = new HashSet<>();
    allowHeaders.add("x-requested-with");
    allowHeaders.add("Access-Control-Allow-Origin");
    allowHeaders.add("origin");
    allowHeaders.add("Content-Type");
    allowHeaders.add("accept");
    Set<HttpMethod> allowMethods = new HashSet<>();
    allowMethods.add(HttpMethod.GET);
    allowMethods.add(HttpMethod.POST);
    allowMethods.add(HttpMethod.DELETE);
    allowMethods.add(HttpMethod.PUT);

    router.route().handler(CorsHandler.create("*") // <2>
      .allowedHeaders(allowHeaders)
      .allowedMethods(allowMethods));
    router.route().handler(BodyHandler.create()); // <3>

    // routes
    router.get("/position").handler(this::getPositions);
    router.get("/user").handler(this::getUsers);
    router.get("/user/:id").handler(this::getById);
    router.post("/user").handler(this::save);
    router.put("/user").handler(this::update);
    router.delete("/user/:id").handler(this::remove);
    router.post("/user/filter").handler(this::getUsersByFilter);

    vertx.createHttpServer() // <4>
      .requestHandler(router::accept)
      .listen(8080, "0.0.0.0", result -> {
        if (result.succeeded())
          fut.complete();
        else
          fut.fail(result.cause());
      });
  }

  PositionService positionService = new PositionService();
  UserService userService = new UserService();

  private void getPositions(RoutingContext context) {
    positionService.list(ar -> {
      if (ar.succeeded()) {
        sendSuccess(Json.encodePrettily(ar.result()), context.response());
      } else {
        sendError(ar.cause().getMessage(), context.response());
      }
    });
  }

  private void getUsers(RoutingContext context) {
    userService.list(ar -> {
      if (ar.succeeded()) {
        sendSuccess(Json.encodePrettily(ar.result()), context.response());
      } else {
        sendError(ar.cause().getMessage(), context.response());
      }
    });
  }

}
