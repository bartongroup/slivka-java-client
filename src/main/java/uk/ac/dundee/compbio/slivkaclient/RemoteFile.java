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

import java.net.URI;

import org.json.JSONObject;

public class RemoteFile
{
  private final URI url;

  private final URI contentUrl;

  private final String id;

  private final String jobId;

  private final String path;

  private final String label;

  private final String mediaType;

  public URI getUrl()
  {
    return url;
  }

  public URI getContentUrl()
  {
    return contentUrl;
  }

  public String getId()
  {
    return id;
  }

  public String getJobId()
  {
    return jobId;
  }

  public String getPath()
  {
    return path;
  }

  public String getLabel()
  {
    return label;
  }

  public String getMediaType()
  {
    return mediaType;
  }

  public RemoteFile(URI url, URI contentUrl, String id, String jobId,
          String path, String label, String mediaType)
  {
    this.url = url;
    this.contentUrl = contentUrl;
    this.id = id;
    this.jobId = jobId;
    this.path = path;
    this.label = label;
    this.mediaType = mediaType;
  }

  static RemoteFile fromJSON(URI baseURL, JSONObject obj)
  {
    return new RemoteFile(baseURL.resolve(obj.getString("@url")),
            baseURL.resolve(obj.getString("@content")), obj.getString("id"),
            obj.optString("jobId"), obj.optString("path"),
            obj.optString("label"), obj.optString("mediaType"));
  }

  @Override
  public String toString()
  {
    return id;
  }
}
