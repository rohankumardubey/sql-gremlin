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

import com.google.common.collect.ImmutableMap;
import lombok.AllArgsConstructor;
import org.apache.calcite.schema.Table;
import org.apache.calcite.schema.impl.AbstractSchema;
import org.twilmes.sql.gremlin.adapter.converter.schema.gremlin.GremlinEdgeTable;
import org.twilmes.sql.gremlin.adapter.converter.schema.gremlin.GremlinTableBase;
import org.twilmes.sql.gremlin.adapter.converter.schema.gremlin.GremlinVertexTable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by twilmes on 9/22/15.
 * Modified by lyndonb-bq on 05/17/21.
 */
@AllArgsConstructor
public class GremlinSchema extends AbstractSchema {
    private final List<GremlinVertexTable> vertices;
    private final List<GremlinEdgeTable> edges;

    @Override
    protected Map<String, Table> getTableMap() {
        final ImmutableMap.Builder<String, Table> builder = ImmutableMap.builder();
        builder.putAll(vertices.stream().collect(Collectors.toMap(GremlinTableBase::getLabel, t -> t)));
        builder.putAll(edges.stream().collect(Collectors.toMap(GremlinTableBase::getLabel, t -> t)));
        final Map<String, Table> tableMap = builder.build();
        return tableMap;
    }

    public List<GremlinVertexTable> getVertices() {
        return new ArrayList<>(vertices);
    }

    public List<GremlinEdgeTable> getEdges() {
        return new ArrayList<>(edges);
    }

    public List<GremlinTableBase> getAllTables() {
        final List<GremlinTableBase> gremlinTableBases = new ArrayList<>();
        gremlinTableBases.addAll(vertices);
        gremlinTableBases.addAll(edges);
        return gremlinTableBases;
    }
}
