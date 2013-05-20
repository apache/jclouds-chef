/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jclouds.privatechef;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.jclouds.Constants;
import org.jclouds.apis.ApiMetadata;
import org.jclouds.chef.ChefAsyncApi;
import org.jclouds.chef.filters.SignedHeaderAuth;
import org.jclouds.chef.filters.SignedHeaderAuthTest;
import org.jclouds.chef.functions.ParseKeySetFromJson;
import org.jclouds.date.TimeStamp;
import org.jclouds.http.HttpRequest;
import org.jclouds.http.functions.ParseJson;
import org.jclouds.http.functions.ReturnTrueIf2xx;
import org.jclouds.privatechef.config.PrivateChefRestClientModule;
import org.jclouds.privatechef.domain.Organization;
import org.jclouds.privatechef.domain.User;
import org.jclouds.rest.ConfiguresRestClient;
import org.jclouds.rest.functions.ReturnEmptySetOnNotFoundOr404;
import org.jclouds.rest.functions.ReturnFalseOnNotFoundOr404;
import org.jclouds.rest.functions.ReturnNullOnNotFoundOr404;
import org.jclouds.rest.internal.BaseAsyncApiTest;
import org.jclouds.rest.internal.GeneratedHttpRequest;
import org.jclouds.rest.internal.RestAnnotationProcessor;
import org.testng.annotations.Test;

import com.google.common.base.Supplier;
import com.google.common.collect.Iterables;
import com.google.inject.Module;
import com.google.inject.TypeLiteral;

/**
 * Tests annotation parsing of {@code PrivateChefAsyncApi}
 * 
 * @author Adrian Cole
 */
@Test(groups = "unit", testName = "PrivateChefAsyncApiTest")
public class PrivateChefAsyncApiTest extends BaseAsyncApiTest<PrivateChefAsyncApi> {

   public void testListUsers() throws SecurityException, NoSuchMethodException, IOException {
      Method method = PrivateChefAsyncApi.class.getMethod("listUsers");
      GeneratedHttpRequest httpRequest = processor.createRequest(method);

      assertRequestLineEquals(httpRequest, "GET https://api.opscode.com/users HTTP/1.1");
      assertNonPayloadHeadersEqual(httpRequest, "Accept: application/json\nX-Chef-Version: " + ChefAsyncApi.VERSION + "-test\n");
      assertPayloadEquals(httpRequest, null, null, false);

      assertResponseParserClassEquals(method, httpRequest, ParseKeySetFromJson.class);
      assertSaxResponseParserClassEquals(method, null);
      assertExceptionParserClassEquals(method, ReturnEmptySetOnNotFoundOr404.class);

      checkFilters(httpRequest);

   }

   public void testUserExists() throws SecurityException, NoSuchMethodException, IOException {
      Method method = PrivateChefAsyncApi.class.getMethod("userExists", String.class);
      GeneratedHttpRequest httpRequest = processor.createRequest(method, "user");
      assertRequestLineEquals(httpRequest, "HEAD https://api.opscode.com/users/user HTTP/1.1");
      assertNonPayloadHeadersEqual(httpRequest, "Accept: application/json\nX-Chef-Version: " + ChefAsyncApi.VERSION + "-test\n");
      assertPayloadEquals(httpRequest, null, null, false);

      assertResponseParserClassEquals(method, httpRequest, ReturnTrueIf2xx.class);
      assertSaxResponseParserClassEquals(method, null);
      assertExceptionParserClassEquals(method, ReturnFalseOnNotFoundOr404.class);

      checkFilters(httpRequest);

   }

   public void testOrganizationExists() throws SecurityException, NoSuchMethodException, IOException {
      Method method = PrivateChefAsyncApi.class.getMethod("organizationExists", String.class);
      GeneratedHttpRequest httpRequest = processor.createRequest(method, "organization");
      assertRequestLineEquals(httpRequest, "HEAD https://api.opscode.com/organizations/organization HTTP/1.1");
      assertNonPayloadHeadersEqual(httpRequest, "Accept: application/json\nX-Chef-Version: " + ChefAsyncApi.VERSION + "-test\n");
      assertPayloadEquals(httpRequest, null, null, false);

      assertResponseParserClassEquals(method, httpRequest, ReturnTrueIf2xx.class);
      assertSaxResponseParserClassEquals(method, null);
      assertExceptionParserClassEquals(method, ReturnFalseOnNotFoundOr404.class);

      checkFilters(httpRequest);

   }

   public void testListOrganizations() throws SecurityException, NoSuchMethodException, IOException {
      Method method = PrivateChefAsyncApi.class.getMethod("listOrganizations");
      GeneratedHttpRequest httpRequest = processor.createRequest(method);

      assertRequestLineEquals(httpRequest, "GET https://api.opscode.com/organizations HTTP/1.1");
      assertNonPayloadHeadersEqual(httpRequest, "Accept: application/json\nX-Chef-Version: " + ChefAsyncApi.VERSION + "-test\n");
      assertPayloadEquals(httpRequest, null, null, false);

      assertResponseParserClassEquals(method, httpRequest, ParseKeySetFromJson.class);
      assertSaxResponseParserClassEquals(method, null);
      assertExceptionParserClassEquals(method, ReturnEmptySetOnNotFoundOr404.class);

      checkFilters(httpRequest);

   }

   public void testCreateUser() throws SecurityException, NoSuchMethodException, IOException {
      Method method = PrivateChefAsyncApi.class.getMethod("createUser", User.class);
      GeneratedHttpRequest httpRequest = processor
            .createRequest(method, new User("myuser"));

      assertRequestLineEquals(httpRequest, "POST https://api.opscode.com/users HTTP/1.1");
      assertNonPayloadHeadersEqual(httpRequest, "Accept: application/json\nX-Chef-Version: " + ChefAsyncApi.VERSION + "-test\n");
      assertPayloadEquals(httpRequest, "{\"username\":\"myuser\"}", "application/json", false);

      // now make sure request filters apply by replaying
      HttpRequest filteredRequest = Iterables.getOnlyElement(httpRequest.getFilters()).filter(httpRequest);

      assertRequestLineEquals(filteredRequest, "POST https://api.opscode.com/users HTTP/1.1");
      assertNonPayloadHeadersEqual(
            filteredRequest,
            new StringBuilder("Accept: application/json").append("\n").append("X-Chef-Version: " + ChefAsyncApi.VERSION + "-test").append("\n")
                  .append("X-Ops-Authorization-1: kfrkDpfgNU26k70R1vl1bEWk0Q0f9Fs/3kxOX7gHd7iNoJq03u7RrcrAOSgL")
                  .append("\n")
                  .append("X-Ops-Authorization-2: ETj5JNeCk18BmFkHMAbCA9hXVo1T4rlHCpbuzAzFlFxUGAT4wj8UoO7V886X")
                  .append("\n")
                  .append("X-Ops-Authorization-3: Kf8DvihP6ElthCNuu1xuhN0B4GEmWC9+ut7UMLe0L2T34VzkbCtuInGbf42/")
                  .append("\n")
                  .append("X-Ops-Authorization-4: G7iu94/xFOT1gN9cex4pNyTnRCHzob4JVU1usxt/2g5grN2SyYwRS5+4MNLN")
                  .append("\n")
                  .append("X-Ops-Authorization-5: WY/iLUPb/9dwtiIQsnUOXqDrs28zNswZulQW4AzYRd7MczJVKU4y4+4XRcB4")
                  .append("\n").append("X-Ops-Authorization-6: 2+BFLT5o6P6G0D+eCu3zSuaqEJRucPJPaDGWdKIMag==")
                  .append("\n").append("X-Ops-Content-Hash: yLHOxvgIEtNw5UrZDxslOeMw1gw=").append("\n")
                  .append("X-Ops-Sign: version=1.0").append("\n").append("X-Ops-Timestamp: timestamp").append("\n")
                  .append("X-Ops-Userid: user").append("\n").toString());
      assertPayloadEquals(filteredRequest, "{\"username\":\"myuser\"}", "application/json", false);

      assertResponseParserClassEquals(method, filteredRequest, ParseJson.class);
      assertSaxResponseParserClassEquals(method, null);
      assertExceptionParserClassEquals(method, null);

      checkFilters(filteredRequest);

   }

   public void testUpdateUser() throws SecurityException, NoSuchMethodException, IOException {
      Method method = PrivateChefAsyncApi.class.getMethod("updateUser", User.class);
      GeneratedHttpRequest httpRequest = processor
            .createRequest(method, new User("myuser"));

      assertRequestLineEquals(httpRequest, "PUT https://api.opscode.com/users/myuser HTTP/1.1");
      assertNonPayloadHeadersEqual(httpRequest, "Accept: application/json\nX-Chef-Version: " + ChefAsyncApi.VERSION + "-test\n");
      assertPayloadEquals(httpRequest, "{\"username\":\"myuser\"}", "application/json", false);

      assertResponseParserClassEquals(method, httpRequest, ParseJson.class);
      assertSaxResponseParserClassEquals(method, null);
      assertExceptionParserClassEquals(method, null);

      checkFilters(httpRequest);

   }

   public void testGetUser() throws SecurityException, NoSuchMethodException, IOException {
      Method method = PrivateChefAsyncApi.class.getMethod("getUser", String.class);
      GeneratedHttpRequest httpRequest = processor.createRequest(method, "myuser");

      assertRequestLineEquals(httpRequest, "GET https://api.opscode.com/users/myuser HTTP/1.1");
      assertNonPayloadHeadersEqual(httpRequest, "Accept: application/json\nX-Chef-Version: " + ChefAsyncApi.VERSION + "-test\n");
      assertPayloadEquals(httpRequest, null, null, false);

      assertResponseParserClassEquals(method, httpRequest, ParseJson.class);
      assertSaxResponseParserClassEquals(method, null);
      assertExceptionParserClassEquals(method, ReturnNullOnNotFoundOr404.class);

      checkFilters(httpRequest);

   }

   public void testDeleteUser() throws SecurityException, NoSuchMethodException, IOException {
      Method method = PrivateChefAsyncApi.class.getMethod("deleteUser", String.class);
      GeneratedHttpRequest httpRequest = processor.createRequest(method, "myuser");

      assertRequestLineEquals(httpRequest, "DELETE https://api.opscode.com/users/myuser HTTP/1.1");
      assertNonPayloadHeadersEqual(httpRequest, "Accept: application/json\nX-Chef-Version: " + ChefAsyncApi.VERSION + "-test\n");
      assertPayloadEquals(httpRequest, null, null, false);

      assertResponseParserClassEquals(method, httpRequest, ParseJson.class);
      assertSaxResponseParserClassEquals(method, null);
      assertExceptionParserClassEquals(method, ReturnNullOnNotFoundOr404.class);

      checkFilters(httpRequest);

   }

   public void testCreateOrg() throws SecurityException, NoSuchMethodException, IOException {
      Method method = PrivateChefAsyncApi.class.getMethod("createOrganization", Organization.class);
      GeneratedHttpRequest httpRequest = processor.createRequest(method, new Organization(
            "myorganization", "myorganization", "myorganization-validator", Organization.Type.BUSINESS));

      assertRequestLineEquals(httpRequest, "POST https://api.opscode.com/organizations HTTP/1.1");
      assertNonPayloadHeadersEqual(httpRequest, "Accept: application/json\nX-Chef-Version: " + ChefAsyncApi.VERSION + "-test\n");
      assertPayloadEquals(
            httpRequest,
            "{\"name\":\"myorganization\",\"full_name\":\"myorganization\",\"clientname\":\"myorganization-validator\",\"org_type\":\"Business\"}",
            "application/json", false);

      assertResponseParserClassEquals(method, httpRequest, ParseJson.class);
      assertSaxResponseParserClassEquals(method, null);
      assertExceptionParserClassEquals(method, null);

      checkFilters(httpRequest);

   }

   public void testUpdateOrg() throws SecurityException, NoSuchMethodException, IOException {
      Method method = PrivateChefAsyncApi.class.getMethod("updateOrganization", Organization.class);
      GeneratedHttpRequest httpRequest = processor.createRequest(method, new Organization(
            "myorganization", "myorganization", "myorganization-validator", Organization.Type.BUSINESS));

      assertRequestLineEquals(httpRequest, "PUT https://api.opscode.com/organizations/myorganization HTTP/1.1");
      assertNonPayloadHeadersEqual(httpRequest, "Accept: application/json\nX-Chef-Version: " + ChefAsyncApi.VERSION + "-test\n");
      assertPayloadEquals(
            httpRequest,
            "{\"name\":\"myorganization\",\"full_name\":\"myorganization\",\"clientname\":\"myorganization-validator\",\"org_type\":\"Business\"}",
            "application/json", false);

      assertResponseParserClassEquals(method, httpRequest, ParseJson.class);
      assertSaxResponseParserClassEquals(method, null);
      assertExceptionParserClassEquals(method, null);

      checkFilters(httpRequest);

   }

   public void testGetOrg() throws SecurityException, NoSuchMethodException, IOException {
      Method method = PrivateChefAsyncApi.class.getMethod("getOrganization", String.class);
      GeneratedHttpRequest httpRequest = processor.createRequest(method, "myorganization");

      assertRequestLineEquals(httpRequest, "GET https://api.opscode.com/organizations/myorganization HTTP/1.1");
      assertNonPayloadHeadersEqual(httpRequest, "Accept: application/json\nX-Chef-Version: " + ChefAsyncApi.VERSION + "-test\n");
      assertPayloadEquals(httpRequest, null, null, false);

      assertResponseParserClassEquals(method, httpRequest, ParseJson.class);
      assertSaxResponseParserClassEquals(method, null);
      assertExceptionParserClassEquals(method, ReturnNullOnNotFoundOr404.class);

      checkFilters(httpRequest);

   }

   public void testDeleteOrg() throws SecurityException, NoSuchMethodException, IOException {
      Method method = PrivateChefAsyncApi.class.getMethod("deleteOrganization", String.class);
      GeneratedHttpRequest httpRequest = processor.createRequest(method, "myorganization");

      assertRequestLineEquals(httpRequest, "DELETE https://api.opscode.com/organizations/myorganization HTTP/1.1");
      assertNonPayloadHeadersEqual(httpRequest, "Accept: application/json\nX-Chef-Version: " + ChefAsyncApi.VERSION + "-test\n");
      assertPayloadEquals(httpRequest, null, null, false);

      assertResponseParserClassEquals(method, httpRequest, ParseJson.class);
      assertSaxResponseParserClassEquals(method, null);
      assertExceptionParserClassEquals(method, ReturnNullOnNotFoundOr404.class);

      checkFilters(httpRequest);

   }

   @Override
   protected void checkFilters(HttpRequest request) {
      assertEquals(request.getFilters().size(), 1);
      assertEquals(request.getFilters().get(0).getClass(), SignedHeaderAuth.class);
   }

   @Override
   protected TypeLiteral<RestAnnotationProcessor<PrivateChefAsyncApi>> createTypeLiteral() {
      return new TypeLiteral<RestAnnotationProcessor<PrivateChefAsyncApi>>() {
      };
   }

   @Override
   protected Module createModule() {
      return new TestPrivateChefRestClientModule();
   }
   
   @Override
   protected Properties setupProperties() {
       Properties props =  super.setupProperties();
       props.put(Constants.PROPERTY_API_VERSION, ChefAsyncApi.VERSION + "-test");
       return props;
   }

   @ConfiguresRestClient
   static class TestPrivateChefRestClientModule extends PrivateChefRestClientModule {
      @Override
      protected String provideTimeStamp(@TimeStamp Supplier<String> cache) {
         return "timestamp";
      }

   }
   @Override
   public ApiMetadata createApiMetadata() {
      identity = "user";
      credential = SignedHeaderAuthTest.PRIVATE_KEY;
      return new PrivateChefApiMetadata();
   }
}
