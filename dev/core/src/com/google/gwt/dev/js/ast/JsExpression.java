/*
 * Copyright 2007 Google Inc.
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
package com.google.gwt.dev.js.ast;

/**
 * An abstract base class for all JavaScript expressions.
 */
public abstract class JsExpression extends JsNode {
  /**
   * Determines whether or not this expression is a leaf, such as a
   * {@link JsNameRef}, {@link JsBooleanLiteral}, and so on. Leaf expressions
   * never need to be parenthesized.
   */
  public boolean isLeaf() {
    // Conservatively say that it isn't a leaf.
    // Individual subclasses can speak for themselves if they are a leaf.
    //
    return false;
  }

  public JsExprStmt makeStmt() {
    return new JsExprStmt(this);
  }
}
