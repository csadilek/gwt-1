/*
 * Copyright 2016 Google Inc.
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

package java.util.stream;

// package protected, as not part of jre
class TerminatableStream {
  private boolean terminated = false;
  private final TerminatableStream previous;

  public TerminatableStream(TerminatableStream previous) {
    this.previous = previous;
  }

  void throwIfTerminated() {
    if (terminated) {
      throw new IllegalStateException("Stream already terminated, can't be modified or used");
    }
  }
  // note that not all terminals directly call this, but they must use it indirectly
  void terminate() {
    // no terminals work if already terminated
    throwIfTerminated();
    terminated = true;
    if (previous != null) {
      previous.terminate();
    }
  }
}