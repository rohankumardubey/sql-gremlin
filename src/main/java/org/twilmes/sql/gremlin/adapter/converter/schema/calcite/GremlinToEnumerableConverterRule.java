/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.twilmes.sql.gremlin.adapter.converter.schema.calcite;

/**
 * Created by twilmes on 9/25/15.
 * Modified by lyndonb-bq on 05/17/21.
 */

import org.apache.calcite.adapter.enumerable.EnumerableConvention;
import org.apache.calcite.plan.RelTraitSet;
import org.apache.calcite.rel.RelNode;
import org.apache.calcite.rel.convert.ConverterRule;

/**
 * Rule to convert a relational expression from
 * {@link GremlinRel#CONVENTION} to {@link org.apache.calcite.adapter.enumerable.EnumerableConvention}.
 */
public final class GremlinToEnumerableConverterRule extends ConverterRule {
    public static final ConverterRule INSTANCE =
            new GremlinToEnumerableConverterRule();

    private GremlinToEnumerableConverterRule() {
        super(RelNode.class, GremlinRel.CONVENTION, EnumerableConvention.INSTANCE,"GremlinToEnumerableConverterRule");
    }

    @Override
    public RelNode convert(final RelNode rel) {
        final RelTraitSet newTraitSet = rel.getTraitSet().replace(getOutConvention());
        return new GremlinToEnumerableConverter(rel.getCluster(), newTraitSet, rel);
    }
}
