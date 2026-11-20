/*
 * Copyright 2026 The Slivka Authors (see CITATION.cff)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package javajs.http;

public class HttpResponseException extends ClientProtocolException
{

  private static final long serialVersionUID = -8481868921105838666L;

  private final int statusCode;

  private final String reasonPhrase;

  public HttpResponseException(int statusCode, String reasonPhrase,
          String message)
  {
    super(message);
    this.statusCode = statusCode;
    this.reasonPhrase = reasonPhrase;
  }

  public HttpResponseException(int statusCode, String reasonPhrase)
  {
    this(statusCode, reasonPhrase,
            String.format("%d %s", statusCode, reasonPhrase));
  }

  public int getStatusCode()
  {
    return statusCode;
  }

  public String getReasonPhrase()
  {
    return reasonPhrase;
  }

}
