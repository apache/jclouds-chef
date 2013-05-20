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
package org.jclouds.privatechef.config;

import java.util.Map;

import org.jclouds.chef.config.BaseChefRestClientModule;
import org.jclouds.privatechef.PatchedChefAsyncApi;
import org.jclouds.privatechef.PatchedChefApi;
import org.jclouds.privatechef.PrivateChefAsyncApi;
import org.jclouds.privatechef.PrivateChefApi;
import org.jclouds.rest.ConfiguresRestClient;

import com.google.common.collect.ImmutableMap;
import com.google.common.reflect.TypeToken;

/**
 * Configures the Private Chef connection.
 * 
 * @author Adrian Cole
 */
@ConfiguresRestClient
public class PrivateChefRestClientModule extends
      BaseChefRestClientModule<PrivateChefApi, PrivateChefAsyncApi> {
   public static final Map<Class<?>, Class<?>> DELEGATE_MAP = ImmutableMap.<Class<?>, Class<?>> builder()//
         .put(PatchedChefApi.class, PatchedChefAsyncApi.class)//
         .build();

   public PrivateChefRestClientModule() {
      super(TypeToken.of(PrivateChefApi.class), TypeToken.of(PrivateChefAsyncApi.class), DELEGATE_MAP);
   }

}
