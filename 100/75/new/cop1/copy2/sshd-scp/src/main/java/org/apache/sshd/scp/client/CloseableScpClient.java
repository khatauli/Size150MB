/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.sshd.scp.client;

import java.nio.channels.Channel;

import org.apache.sshd.common.util.closeable.NioChannelDelegateInvocationHandler;

/**
 * An {@link ScpClient} wrapper that also closes the underlying session when closed
 *
 * @author <a href="mailto:dev@mina.apache.org">Apache MINA SSHD Project</a>
 */
public interface CloseableScpClient extends ScpClient, Channel {
    /**
     * @param  client The (never {@code null}) {@link ScpClient} instance
     * @return        A {@link CloseableScpClient} wrapper that also closes the underlying {@link #getClientSession()}
     *                when closed
     */
    static CloseableScpClient singleSessionInstance(ScpClient client) {
        return NioChannelDelegateInvocationHandler.wrapDelegateChannel(
                client, CloseableScpClient.class, client.getClientSession());
    }
}