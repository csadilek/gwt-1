/*
 * Copyright 2011 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.gwt.user.server.rpc;

import com.google.gwt.event.shared.UmbrellaException;
import com.google.gwt.user.client.rpc.ExceptionsTestService;
import com.google.gwt.user.client.rpc.TestSetFactory;
import com.google.gwt.user.client.rpc.TestSetValidator;

/**
 * Remote Service Implementation for Exception serialization tests.
 */
public class ExceptionsTestServiceImpl extends HybridServiceServlet implements
    ExceptionsTestService {

  public UmbrellaException echo(UmbrellaException exception)
      throws ExceptionsTestServiceException {
    UmbrellaException expected = TestSetFactory.createUmbrellaException();
    if (!TestSetValidator.isValid(expected, exception)) {
      throw new ExceptionsTestServiceException();
    }

    return exception;
  }

}
