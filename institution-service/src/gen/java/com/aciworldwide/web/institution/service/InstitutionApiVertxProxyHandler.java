/*
* Copyright 2014 Red Hat, Inc.
*
* Red Hat licenses this file to you under the Apache License, version 2.0
* (the "License"); you may not use this file except in compliance with the
* License. You may obtain a copy of the License at:
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
* WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
* License for the specific language governing permissions and limitations
* under the License.
*/

package com.aciworldwide.web.institution.service;

import com.aciworldwide.web.institution.service.InstitutionApi;
import io.vertx.core.Vertx;
import io.vertx.core.Handler;
import io.vertx.core.AsyncResult;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.ReplyException;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.JsonArray;
import java.util.Collection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import io.vertx.serviceproxy.ProxyHandler;
import io.vertx.serviceproxy.ServiceException;
import io.vertx.serviceproxy.ServiceExceptionMessageCodec;
import io.vertx.serviceproxy.HelperUtils;
import io.vertx.serviceproxy.ServiceBinder;

import io.vertx.ext.web.api.service.ServiceResponse;
import io.vertx.ext.web.validation.RequestParameter;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.ext.web.api.service.ServiceRequest;
import java.util.Optional;
/*
  Generated Proxy code - DO NOT EDIT
  @author Roger the Robot
*/

@SuppressWarnings({"unchecked", "rawtypes"})
public class InstitutionApiVertxProxyHandler extends ProxyHandler {

  public static final long DEFAULT_CONNECTION_TIMEOUT = 5 * 60; // 5 minutes 
  private final Vertx vertx;
  private final InstitutionApi service;
  private final long timerID;
  private long lastAccessed;
  private final long timeoutSeconds;
  private final boolean includeDebugInfo;

  public InstitutionApiVertxProxyHandler(Vertx vertx, InstitutionApi service){
    this(vertx, service, DEFAULT_CONNECTION_TIMEOUT);
  }

  public InstitutionApiVertxProxyHandler(Vertx vertx, InstitutionApi service, long timeoutInSecond){
    this(vertx, service, true, timeoutInSecond);
  }

  public InstitutionApiVertxProxyHandler(Vertx vertx, InstitutionApi service, boolean topLevel, long timeoutInSecond){
    this(vertx, service, true, timeoutInSecond, false);
  }

  public InstitutionApiVertxProxyHandler(Vertx vertx, InstitutionApi service, boolean topLevel, long timeoutSeconds, boolean includeDebugInfo) {
      this.vertx = vertx;
      this.service = service;
      this.includeDebugInfo = includeDebugInfo;
      this.timeoutSeconds = timeoutSeconds;
      try {
        this.vertx.eventBus().registerDefaultCodec(ServiceException.class,
            new ServiceExceptionMessageCodec());
      } catch (IllegalStateException ex) {}
      if (timeoutSeconds != -1 && !topLevel) {
        long period = timeoutSeconds * 1000 / 2;
        if (period > 10000) {
          period = 10000;
        }
        this.timerID = vertx.setPeriodic(period, this::checkTimedOut);
      } else {
        this.timerID = -1;
      }
      accessed();
    }


  private void checkTimedOut(long id) {
    long now = System.nanoTime();
    if (now - lastAccessed > timeoutSeconds * 1000000000) {
      close();
    }
  }

    @Override
    public void close() {
      if (timerID != -1) {
        vertx.cancelTimer(timerID);
      }
      super.close();
    }

    private void accessed() {
      this.lastAccessed = System.nanoTime();
    }

  public void handle(Message<JsonObject> msg) {
    try{
      JsonObject json = msg.body();
      String action = msg.headers().get("action");
      if (action == null) throw new IllegalStateException("action not specified");
      accessed();
      switch (action) {
        case "createInstitution": {
          JsonObject contextSerialized = json.getJsonObject("context");
          if (contextSerialized == null)
            throw new IllegalStateException("Received action " + action + " without ServiceRequest \"context\"");
          ServiceRequest context = new ServiceRequest(contextSerialized);
          JsonObject params = context.getParams();
          try {
            service.createInstitution(
                io.vertx.ext.web.validation.RequestParameter.create(searchInJson(params, "body")),
                context,
                res -> {
                              if (res.failed()) {
                                if (res.cause() instanceof ServiceException) {
                                  msg.reply(res.cause());
                                } else {
                                  msg.reply(new ServiceException(-1, res.cause().getMessage()));
                                }
                              } else {
                                msg.reply(res.result() == null ? null : res.result().toJson());
                              }
                            }
              );
          } catch (Exception e) {
            HelperUtils.manageFailure(msg, e, includeDebugInfo);
          }
          break;
        }
        case "getInstitutionById": {
          JsonObject contextSerialized = json.getJsonObject("context");
          if (contextSerialized == null)
            throw new IllegalStateException("Received action " + action + " without ServiceRequest \"context\"");
          ServiceRequest context = new ServiceRequest(contextSerialized);
          JsonObject params = context.getParams();
          try {
            service.getInstitutionById(
                io.vertx.ext.web.validation.RequestParameter.create(searchInJson(params, "body")),
                context,
                res -> {
                              if (res.failed()) {
                                if (res.cause() instanceof ServiceException) {
                                  msg.reply(res.cause());
                                } else {
                                  msg.reply(new ServiceException(-1, res.cause().getMessage()));
                                }
                              } else {
                                msg.reply(res.result() == null ? null : res.result().toJson());
                              }
                            }
              );
          } catch (Exception e) {
            HelperUtils.manageFailure(msg, e, includeDebugInfo);
          }
          break;
        }
        case "getInstitutions": {
          JsonObject contextSerialized = json.getJsonObject("context");
          if (contextSerialized == null)
            throw new IllegalStateException("Received action " + action + " without ServiceRequest \"context\"");
          ServiceRequest context = new ServiceRequest(contextSerialized);
          JsonObject params = context.getParams();
          try {
            service.getInstitutions(
                io.vertx.ext.web.validation.RequestParameter.create(searchInJson(params, "body")),
                context,
                res -> {
                              if (res.failed()) {
                                if (res.cause() instanceof ServiceException) {
                                  msg.reply(res.cause());
                                } else {
                                  msg.reply(new ServiceException(-1, res.cause().getMessage()));
                                }
                              } else {
                                msg.reply(res.result() == null ? null : res.result().toJson());
                              }
                            }
              );
          } catch (Exception e) {
            HelperUtils.manageFailure(msg, e, includeDebugInfo);
          }
          break;
        }
        default: throw new IllegalStateException("Invalid action: " + action);
      }
    } catch (Throwable t) {
      if (includeDebugInfo) msg.reply(new ServiceException(500, t.getMessage(), HelperUtils.generateDebugInfo(t)));
      else msg.reply(new ServiceException(500, t.getMessage()));
      throw t;
    }
  }

    public static Object searchInJson(JsonObject obj, String key) {
      if ("body".equals(key)) return  obj.getValue("body");
      if (obj.getJsonObject("path").containsKey(key)) return  obj.getJsonObject("path").getValue(key);
      if (obj.getJsonObject("query").containsKey(key)) return  obj.getJsonObject("query").getValue(key);
      if (obj.getJsonObject("header").containsKey(key)) return  obj.getJsonObject("header").getValue(key);
      if (obj.getJsonObject("cookie").containsKey(key)) return  obj.getJsonObject("cookie").getValue(key);
      if (obj.getJsonObject("body").containsKey(key)) return  obj.getJsonObject("body").getValue(key);
      return null;
    }

    public static Optional<Object> searchOptionalInJson(JsonObject obj, String key) {
      return Optional.ofNullable(searchInJson(obj, key));
    }

    public static Integer searchIntegerInJson(JsonObject obj, String key) {
      if ("body".equals(key)) return obj.getInteger("body");
      if (obj.getJsonObject("path").containsKey(key)) return  obj.getJsonObject("path").getInteger(key);
      if (obj.getJsonObject("query").containsKey(key)) return  obj.getJsonObject("query").getInteger(key);
      if (obj.getJsonObject("header").containsKey(key)) return  obj.getJsonObject("header").getInteger(key);
      if (obj.getJsonObject("cookie").containsKey(key)) return  obj.getJsonObject("cookie").getInteger(key);
      if (obj.getJsonObject("body").containsKey(key)) return  obj.getJsonObject("body").getInteger(key);
      return null;
    }

    public static Optional<Integer> searchOptionalIntegerInJson(JsonObject obj, String key) {
      return Optional.ofNullable(searchIntegerInJson(obj, key));
    }

    public static Character searchCharInJson(JsonObject obj, String key) {
      if ("body".equals(key)) return (Character)obj.getValue("body");
      if (obj.getJsonObject("path").containsKey(key)) return (Character) obj.getJsonObject("path").getValue(key);
      if (obj.getJsonObject("query").containsKey(key)) return (Character) obj.getJsonObject("query").getValue(key);
      if (obj.getJsonObject("header").containsKey(key)) return (Character) obj.getJsonObject("header").getValue(key);
      if (obj.getJsonObject("cookie").containsKey(key)) return (Character) obj.getJsonObject("cookie").getValue(key);
      if (obj.getJsonObject("body").containsKey(key)) return (Character) obj.getJsonObject("body").getValue(key);
      return null;
    }

    public static Optional<Character> searchOptionalCharacterInJson(JsonObject obj, String key) {
      return Optional.ofNullable(searchCharInJson(obj, key));
    }

    public static Long searchLongInJson(JsonObject obj, String key) {
      if ("body".equals(key)) return obj.getLong("body");
      if (obj.getJsonObject("path").containsKey(key)) return  obj.getJsonObject("path").getLong(key);
      if (obj.getJsonObject("query").containsKey(key)) return  obj.getJsonObject("query").getLong(key);
      if (obj.getJsonObject("header").containsKey(key)) return  obj.getJsonObject("header").getLong(key);
      if (obj.getJsonObject("cookie").containsKey(key)) return  obj.getJsonObject("cookie").getLong(key);
      if (obj.getJsonObject("body").containsKey(key)) return  obj.getJsonObject("body").getLong(key);
      return null;
    }

    public static Optional<Long> searchOptionalLongInJson(JsonObject obj, String key) {
      return Optional.ofNullable(searchLongInJson(obj, key));
    }

    public static Double searchDoubleInJson(JsonObject obj, String key) {
      if ("body".equals(key)) return obj.getDouble("body");
      if (obj.getJsonObject("path").containsKey(key)) return  obj.getJsonObject("path").getDouble(key);
      if (obj.getJsonObject("query").containsKey(key)) return  obj.getJsonObject("query").getDouble(key);
      if (obj.getJsonObject("header").containsKey(key)) return  obj.getJsonObject("header").getDouble(key);
      if (obj.getJsonObject("cookie").containsKey(key)) return  obj.getJsonObject("cookie").getDouble(key);
      if (obj.getJsonObject("body").containsKey(key)) return  obj.getJsonObject("body").getDouble(key);
      return null;
    }

    public static Optional<Double> searchOptionalDoubleInJson(JsonObject obj, String key) {
      return Optional.ofNullable(searchDoubleInJson(obj, key));
    }

    public static String searchStringInJson(JsonObject obj, String key) {
      if ("body".equals(key)) return obj.getString("body");
      if (obj.getJsonObject("path").containsKey(key)) return  obj.getJsonObject("path").getString(key);
      if (obj.getJsonObject("query").containsKey(key)) return  obj.getJsonObject("query").getString(key);
      if (obj.getJsonObject("header").containsKey(key)) return  obj.getJsonObject("header").getString(key);
      if (obj.getJsonObject("cookie").containsKey(key)) return  obj.getJsonObject("cookie").getString(key);
      if (obj.getJsonObject("body").containsKey(key)) return  obj.getJsonObject("body").getString(key);
      return null;
    }

    public static Optional<String> searchOptionalStringInJson(JsonObject obj, String key) {
      return Optional.ofNullable(searchStringInJson(obj, key));
    }

    public static JsonArray searchJsonArrayInJson(JsonObject obj, String key) {
      if ("body".equals(key)) return obj.getJsonArray("body");
      if (obj.getJsonObject("path").containsKey(key)) return  obj.getJsonObject("path").getJsonArray(key);
      if (obj.getJsonObject("query").containsKey(key)) return  obj.getJsonObject("query").getJsonArray(key);
      if (obj.getJsonObject("header").containsKey(key)) return  obj.getJsonObject("header").getJsonArray(key);
      if (obj.getJsonObject("cookie").containsKey(key)) return  obj.getJsonObject("cookie").getJsonArray(key);
      if (obj.getJsonObject("body").containsKey(key)) return  obj.getJsonObject("body").getJsonArray(key);
      return null;
    }

    public static Optional<JsonArray> searchOptionalJsonArrayInJson(JsonObject obj, String key) {
      return Optional.ofNullable(searchJsonArrayInJson(obj, key));
    }

    public static JsonObject searchJsonObjectInJson(JsonObject obj, String key) {
      if ("body".equals(key)) return obj.getJsonObject("body");
      if (obj.getJsonObject("path").containsKey(key)) return obj.getJsonObject("path").getJsonObject(key);
      if (obj.getJsonObject("query").containsKey(key)) return  obj.getJsonObject("query").getJsonObject(key);
      if (obj.getJsonObject("header").containsKey(key)) return  obj.getJsonObject("header").getJsonObject(key);
      if (obj.getJsonObject("cookie").containsKey(key)) return  obj.getJsonObject("cookie").getJsonObject(key);
      if (obj.getJsonObject("body").containsKey(key)) return  obj.getJsonObject("body").getJsonObject(key);
      return null;
    }

    public static Optional<JsonObject> searchOptionalJsonObjectInJson(JsonObject obj, String key) {
      return Optional.ofNullable(searchJsonObjectInJson(obj, key));
    }
}