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

import uk.ac.dundee.compbio.slivkaclient.http.impl.ApacheHttpClient;

/**
 * A class with just one method, which allows implementation in Java or
 * JavaScript. You should probably copy this factory to your code base and add
 * your own Java implementation class name there.
 *
 * Optionally, you can just use this one and call it with your desired class
 * name or set the static default to what you want generally.
 *
 *
 * @author hansonr
 */
public class HttpClientFactory
{

  private static String defaultClassName = "javajs.http.SimpleHttpClient";

  /** @j2sIgnore */
  static
  {
    defaultClassName = ApacheHttpClient.class.getName();
  }

  public static void setDefaultClassName(String className)
  {
    if (className != null)
      defaultClassName = className;
  }

  public static HttpClient getClient(String className)
  {
    if (className == null)
      className = defaultClassName;
    try
    {
      return (HttpClient) Class.forName(className).getDeclaredConstructor()
              .newInstance();
    } catch (Exception e)
    {
      e.printStackTrace();
      return null;
    }
  }

}
