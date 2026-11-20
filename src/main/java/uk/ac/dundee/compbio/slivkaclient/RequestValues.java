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
import java.io.InputStream;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RequestValues
{
  protected ArrayList<Map.Entry<String, String>> data = new ArrayList<>();

  protected ArrayList<Map.Entry<String, File>> files = new ArrayList<>();

  protected ArrayList<Map.Entry<String, InputStream>> streams = new ArrayList<>();

  public void addData(String key, Object value)
  {
    data.add(new AbstractMap.SimpleEntry<>(key, value.toString()));
  }

  public void addFile(String key, File value)
  {
    files.add(new AbstractMap.SimpleEntry<>(key, value));
  }

  public void addFile(String key, InputStream value)
  {
    streams.add(new AbstractMap.SimpleEntry<>(key, value));
  }

  List<Map.Entry<String, String>> getData()
  {
    return data;
  }

  List<Map.Entry<String, File>> getFiles()
  {
    return files;
  }

  List<Map.Entry<String, InputStream>> getStreams()
  {
    return streams;
  }
}
