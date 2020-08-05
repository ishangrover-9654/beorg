/*
 * DISCLAIMER
 *
 * Copyright 2016 ArangoDB GmbH, Cologne, Germany
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
 * Copyright holder is ArangoDB GmbH, Cologne, Germany
 */

package com.ishan.rd.beorg.polymorphic.template;

import com.arangodb.ArangoCursor;

import com.ishan.rd.beorg.AbstractArangoTest;
import com.ishan.rd.beorg.polymorphic.entity.Animal;
import com.ishan.rd.beorg.polymorphic.entity.Dog;
import com.ishan.rd.beorg.polymorphic.entity.Eagle;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * @author Michele Rastelli
 */
public class PolymorphicTemplate extends AbstractArangoTest {

	@Test
	public void query() {
		Dog dog = new Dog();
		dog.setId("1");
		dog.setName("dog");
		dog.setTeeths(11);

		Eagle eagle = new Eagle();
		dog.setId("2");
		eagle.setName("eagle");
		eagle.setWingspan(2.5);

		template.insert(dog);
		template.insert(eagle);

		final ArangoCursor<Animal> cursor = template.query("FOR a IN animals RETURN a", Animal.class);
		Assert.assertThat(cursor, is(notNullValue()));
		final List<Animal> animals = cursor.asListRemaining();
		Assert.assertThat(animals.size(), is(2));
		Assert.assertThat(animals.stream().anyMatch(it -> it.equals(eagle)), is(true));
		Assert.assertThat(animals.stream().anyMatch(it -> it.equals(dog)), is(true));
	}

}
