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

import org.json.JSONObject;
import org.testng.annotations.Test;

import uk.ac.dundee.compbio.slivkaclient.Parameter.ChoiceParameter;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SlivkaChoiceParameterTest
{
  @Test
  public void testChoiceOfTrueAndFalse()
  {
    String yaml = "{\n\"array\": false,\n\"choices\": [\ntrue,\nfalse\n],\n\"default\": null,\n\"description\": \"\",\n\"id\": \"sprotein-sequence\",\n\"name\": \"sprotein_sequence\",\n\"required\": false,\n\"type\": \"choice\"\n}";
    ChoiceParameter choice = new ChoiceParameter();
    choice.readJSON(new JSONObject(yaml));
    assertEquals(choice.getChoices().size(), 2);
    assertTrue(choice.getChoices().contains("true"));
    assertTrue(choice.getChoices().contains("false"));

  }
}
