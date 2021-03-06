/**
 * Copyright © 2016 Jeremy Custenborder (jcustenborder@gmail.com)
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
 */
package com.github.jcustenborder.kafka.connect.utils.config;

import org.apache.kafka.common.config.ConfigException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidPatternTest {

  @Test
  public void ensureValid() {
    ValidPattern validPattern = ValidPattern.of(".*\\.csv$");
    validPattern.ensureValid("file.name", "testing.csv");
  }

  @Test
  public void ensureValidInvalid() {
    assertThrows(ConfigException.class, () -> {
      ValidPattern validPattern = ValidPattern.of(".*\\.csv$");
      validPattern.ensureValid("file.name", "testing.txt");
    });
  }

  @Test
  public void ensureValidNullString() {
    assertThrows(ConfigException.class, () -> {
      ValidPattern validPattern = ValidPattern.of(".*\\.csv$");
      validPattern.ensureValid("file.name", null);
    });

  }

  @Test
  public void ensureValidNonString() {
    assertThrows(ConfigException.class, () -> {
      ValidPattern validPattern = ValidPattern.of(".*\\.csv$");
      validPattern.ensureValid("file.name", new Integer(1));
    });
  }

  @Test
  public void display() {
    final String expected = "Matches regex( .*\\.csv$ )";
    ValidPattern validPattern = ValidPattern.of(".*\\.csv$");
    assertEquals(expected, validPattern.toString());
  }
}
