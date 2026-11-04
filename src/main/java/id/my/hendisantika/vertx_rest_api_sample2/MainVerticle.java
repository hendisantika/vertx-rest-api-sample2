package id.my.hendisantika.vertx_rest_api_sample2;

import id.my.hendisantika.vertx_rest_api_sample2.entity.User;
import id.my.hendisantika.vertx_rest_api_sample2.service.PositionService;
import id.my.hendisantika.vertx_rest_api_sample2.service.UserService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;

import java.util.Set;

public class MainVerticle extends AbstractVerticle {

  private final PositionService positionService = new PositionService();
  private final UserService userService = new UserService();

  @Override
  public void start(Promise<Void> startPromise) {
    Router router = Router.router(vertx);

    // CORS support
    Set<String> allowHeaders = Set.of(
      "x-requested-with", "Access-Control-Allow-Origin", "origin", "Content-Type", "accept");
    Set<HttpMethod> allowMethods = Set.of(HttpMethod.GET, HttpMethod.POST, HttpMethod.DELETE, HttpMethod.PUT);

    router.route().handler(CorsHandler.create()
      .addOrigin("*")
      .allowedHeaders(allowHeaders)
      .allowedMethods(allowMethods));
    router.route().handler(BodyHandler.create());

    // routes
    router.get("/position").handler(this::getPositions);
    router.get("/user").handler(this::getUsers);
    router.get("/user/:id").handler(this::getById);
    router.post("/user").handler(this::save);
    router.put("/user").handler(this::update);
    router.delete("/user/:id").handler(this::remove);
    router.post("/user/filter").handler(this::getUsersByFilter);

    vertx.createHttpServer()
      .requestHandler(router)
      .listen(8080, "0.0.0.0")
      .onSuccess(server -> startPromise.complete())
      .onFailure(startPromise::fail);
  }

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

  private void getUsersByFilter(RoutingContext context) {
    userService.getByFilter(context.body().asJsonObject(), ar -> {
      if (ar.succeeded()) {
        sendSuccess(Json.encodePrettily(ar.result()), context.response());
      } else {
        sendError(ar.cause().getMessage(), context.response());
      }
    });
  }

  private void getById(RoutingContext context) {
    userService.getById(context.pathParam("id"), ar -> {
      if (ar.succeeded()) {
        if (ar.result() != null) {
          sendSuccess(Json.encodePrettily(ar.result()), context.response());
        } else {
          sendSuccess(context.response());
        }
      } else {
        sendError(ar.cause().getMessage(), context.response());
      }
    });
  }

  private void save(RoutingContext context) {
    userService.save(Json.decodeValue(context.body().asString(), User.class), ar -> {
      if (ar.succeeded()) {
        sendSuccess(context.response());
      } else {
        sendError(ar.cause().getMessage(), context.response());
      }
    });
  }

  private void update(RoutingContext context) {
    userService.update(Json.decodeValue(context.body().asString(), User.class), ar -> {
      if (ar.succeeded()) {
        sendSuccess(context.response());
      } else {
        sendError(ar.cause().getMessage(), context.response());
      }
    });
  }

  private void remove(RoutingContext context) {
    userService.remove(context.pathParam("id"), ar -> {
      if (ar.succeeded()) {
        sendSuccess(context.response());
      } else {
        sendError(ar.cause().getMessage(), context.response());
      }
    });
  }

  private void sendError(String errorMessage, HttpServerResponse response) {
    JsonObject jo = new JsonObject();
    jo.put("errorMessage", errorMessage);

    response
      .setStatusCode(500)
      .putHeader("content-type", "application/json; charset=utf-8")
      .end(Json.encodePrettily(jo));
  }

  private void sendSuccess(HttpServerResponse response) {
    response
      .setStatusCode(200)
      .putHeader("content-type", "application/json; charset=utf-8")
      .end();
  }

  private void sendSuccess(String responseBody, HttpServerResponse response) {
    response
      .setStatusCode(200)
      .putHeader("content-type", "application/json; charset=utf-8")
      .end(responseBody);
  }
}
