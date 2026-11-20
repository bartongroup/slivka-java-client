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
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import javajs.http.HttpClientFactory;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.String.format;
import static org.testng.Assert.*;

public class SlivkaFileUploadTests
{

  SlivkaClient client = null;

  private final HttpClientStub httpClient = new HttpClientStub();

  private static final URI slivkaUrl = URI.create("http://example.org/");

  private HttpClientStub.HttpRequestMock req = null;

  private HttpClientStub.HttpResponseMock rep = null;

  private Path filePath = null;

  @BeforeClass
  public void setUpClass() throws IOException
  {
    HttpClientFactory.setDefaultClassName(HttpClientStub.class.getName());
    client = SlivkaClient.newInstance(slivkaUrl);
    client.setHttpClient(httpClient);
    rep = new HttpClientStub.HttpResponseMock();
    rep.setStatusCode(201);
    rep.setHeader("Content-Type", "application/json");
    rep.setContentLocation("/response/file-created-body.json");
    filePath = Files.createTempFile("", ".txt");
  }

  @BeforeMethod
  public void setUp()
  {
    req = new HttpClientStub.HttpRequestMock();
    req.setResponse(rep);
    httpClient.setRequests(List.of(req));
  }

  @Test
  public void testFileUploadRequest() throws IOException
  {
    client.uploadFile(filePath.toFile());
    assertEquals(req.getMethod(), "POST");
    var param = req.getFiles().get(0);
    assertEquals(param.getKey(), "file");
    assertTrue(param.getValue() instanceof InputStream
            | param.getValue() instanceof File);
  }

  @Test
  public void testFileUuid() throws IOException
  {
    var rf = client.uploadFile(filePath.toFile());
    assertEquals(rf.getId(), "au3Z9MYqgTw94Ny2");
  }

  @Test
  public void testFileLabel() throws IOException
  {
    var rf = client.uploadFile(filePath.toFile());
    assertEquals(rf.getLabel(), "filename.txt");
  }

  @Test
  public void testFileMediaType() throws IOException
  {
    var rf = client.uploadFile(filePath.toFile());
    assertEquals(rf.getMediaType(), "text/plain");
  }

  @Test
  public void testFileUrl() throws IOException
  {
    var rf = client.uploadFile(filePath.toFile());
    assertEquals(rf.getUrl(),
            slivkaUrl.resolve(format("api/files/%s", rf.getId())));
  }

  @Test
  public void testFileDownload() throws IOException
  {
    var rf = client.uploadFile(filePath.toFile());
    var req = new HttpClientStub.HttpRequestMock();
    var rep = new HttpClientStub.HttpResponseMock();
    rep.setStatusCode(200);
    rep.setContentLocation("/response/downloadable-file.txt");
    req.setResponse(rep);
    httpClient.setRequests(List.of(req));
    var content = new String(client.getFileStream(rf).readAllBytes());
    assertEquals(content, "example\n");
  }
}
