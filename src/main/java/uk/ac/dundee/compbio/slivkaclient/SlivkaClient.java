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

package uk.ac.dundee.compbio.slivkaclient;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import javajs.http.HttpClient;

public interface SlivkaClient
{

  public static class Version
  {
    public final String server, API;

    public Version(String server, String API)
    {
      this.server = server;
      this.API = API;
    }

    @Override
    public String toString()
    {
      return String.format("Version(server=%s, API=%s)", server, API);
    }
  }

  public static SlivkaClient newInstance(HttpClient httpClient, URI url)
  {
    return new SlivkaClientImpl(httpClient, url);
  }

  public static SlivkaClient newInstance(URI address)
  {
    return new SlivkaClientImpl(address);
  }

  public static SlivkaClient newInstance(String address)
  {
    return new SlivkaClientImpl(address);
  }

  HttpClient getHttpClient();

  void setHttpClient(HttpClient httpClient);

  URI getUrl();

  URI urlFor(String path);

  Version getVersion() throws IOException;

  List<SlivkaService> getServices() throws IOException;

  SlivkaService getService(String id) throws IOException;

  RemoteFile uploadFile(File file) throws IOException;

  RemoteFile uploadFile(InputStream stream) throws IOException;

  String submitJob(SlivkaService service, RequestValues parameters)
          throws IOException;

  @Deprecated
  default Job getJob(String id) throws IOException
  {
    return fetchJobInfo(id);
  }

  Job fetchJobInfo(String id) throws IOException;

  Job.Status fetchJobStatus(String id) throws IOException;

  Collection<RemoteFile> fetchFilesList(String id) throws IOException;

  void writeFileTo(RemoteFile file, OutputStream out) throws IOException;

  InputStream getFileStream(RemoteFile file) throws IOException;
}
