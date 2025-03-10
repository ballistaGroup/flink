/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.table.client.cli.parser;

import org.apache.flink.table.api.SqlParserException;
import org.apache.flink.table.client.gateway.Executor;
import org.apache.flink.table.client.gateway.SqlExecutionException;
import org.apache.flink.table.operations.Operation;

import java.util.Optional;

/** SqlCommandParserImpl wrappers an {@link Executor} supports parse a statement to an Operation. */
public class SqlCommandParserImpl implements SqlCommandParser {
    private final Executor executor;

    public SqlCommandParserImpl(Executor executor) {
        this.executor = executor;
    }

    @Override
    public Optional<Operation> parseCommand(String stmt) throws SqlParserException {
        // normalize
        stmt = stmt.trim();
        // meet empty statement, e.g ";\n"
        if (stmt.isEmpty() || stmt.equals(";")) {
            return Optional.empty();
        }
        return Optional.ofNullable(executor.parseStatement(stmt));
    }

    /** A dumb implementation. */
    @Override
    public Optional<StatementType> parseStatement(String statement) throws SqlExecutionException {
        return Optional.empty();
    }
}
