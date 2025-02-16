/**
 * Copyright (c) 2013-2021 Nikita Koksharov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.redisson.api;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

/**
 * Represents the result of an asynchronous computation
 * 
 * @author Nikita Koksharov
 *
 * @param <V> type of value
 */
public interface RFuture<V> extends java.util.concurrent.Future<V>, CompletionStage<V> {

    /**
     * Returns {@code true} if and only if the I/O operation was completed
     * successfully.
     * 
     * @return {@code true} if future was completed successfully
     */
    boolean isSuccess();

    /**
     * Returns the cause of the failed I/O operation if the I/O operation has
     * failed.
     *
     * @return the cause of the failure.
     *         {@code null} if succeeded or this future is not
     *         completed yet.
     */
    Throwable cause();
    
    /**
     * Return the result without blocking. If the future is not done yet this will return {@code null}.
     *
     * As it is possible that a {@code null} value is used to mark the future as successful you also need to check
     * if the future is really done with {@link #isDone()} and not relay on the returned {@code null} value.
     * 
     * @return object
     */
    V getNow();
    
    /**
     * Use toCompletableFuture().join() method instead
     *
     * @return the result value
     */
    @Deprecated
    V join();
    
    /**
     * Use snippet below instead.
     *
     * <pre>
     *                 try {
     *                     toCompletableFuture().get();
     *                 } catch (Exception e) {
     *                     // skip
     *                 }
     * </pre>
     *
     * @param timeout - wait timeout
     * @param unit - time unit
     * @return {@code true} if and only if the future was completed within
     *         the specified time limit
     *
     * @throws InterruptedException
     *         if the current thread was interrupted
     */
    @Deprecated
    boolean await(long timeout, TimeUnit unit) throws InterruptedException;

    /**
     * Use snippet below instead.
     *
     * <pre>
     *                 try {
     *                     toCompletableFuture().get();
     *                 } catch (Exception e) {
     *                     // skip
     *                 }
     * </pre>
     *
     * @param timeoutMillis - timeout value
     * @return {@code true} if and only if the future was completed within
     *         the specified time limit
     *
     * @throws InterruptedException
     *         if the current thread was interrupted
     */
    @Deprecated
    boolean await(long timeoutMillis) throws InterruptedException;
    
    /**
     * Use toCompletableFuture().get() method instead
     *
     * @throws InterruptedException
     *         if the current thread was interrupted
     * @return Future object
     */
    @Deprecated
    RFuture<V> sync() throws InterruptedException;

    /**
     * Use toCompletableFuture().join() method instead
     *
     * @return Future object
     */
    @Deprecated
    RFuture<V> syncUninterruptibly();

    /**
     * Use snippet below instead.
     *
     * <pre>
     *                 try {
     *                     toCompletableFuture().get();
     *                 } catch (Exception e) {
     *                     // skip
     *                 }
     * </pre>
     *
     * @throws InterruptedException
     *         if the current thread was interrupted
     * @return Future object
     */
    @Deprecated
    RFuture<V> await() throws InterruptedException;

    /**
     * Use snippet below instead.
     *
     * <pre>
     *             try {
     *                 rFuture.toCompletableFuture().join();
     *             } catch (Exception e) {
     *                 // skip
     *             }
     * </pre>
     *
     * @return Future object
     */
    @Deprecated
    RFuture<V> awaitUninterruptibly();

    /**
     * Use snippet below instead.
     *
     * <pre>
     *                 try {
     *                     toCompletableFuture().get();
     *                 } catch (Exception e) {
     *                     // skip
     *                 }
     * </pre>
     *
     * @param timeout - timeout value
     * @param unit - timeout unit value
     * @return {@code true} if and only if the future was completed within
     *         the specified time limit
     */
    @Deprecated
    boolean awaitUninterruptibly(long timeout, TimeUnit unit);

    /**
     * Use snippet below instead.
     *
     * <pre>
     *                 try {
     *                     toCompletableFuture().get();
     *                 } catch (Exception e) {
     *                     // skip
     *                 }
     * </pre>
     *
     * @param timeoutMillis - timeout value
     * @return {@code true} if and only if the future was completed within
     *         the specified time limit
     */
    @Deprecated
    boolean awaitUninterruptibly(long timeoutMillis);

    void onComplete(BiConsumer<? super V, ? super Throwable> action);
    
}
