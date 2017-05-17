/*
 * Copyright 2015 Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yanzhenjie.nohttp.rest;

import com.yanzhenjie.nohttp.Priority;
import com.yanzhenjie.nohttp.RequestMethod;

/**
 * <p>
 * The realization method of the parameters.
 * </p>
 * <p>
 * Created by Yan Zhenjie on Oct 20, 2015.
 *
 * @param <Result> a generics, regulated the analytic results of the Request.It should be with the
 *                 {@link Response}, {@link OnResponseListener}.
 */
public abstract class RestRequest<Result, Child extends RestRequest>
        extends ProtocolRequest<Result, Child>
        implements Request<Result> {

    private int sequence;
    private Priority priority;

    /**
     * Create a request, RequestMethod is {@link RequestMethod#GET}.
     *
     * @param url request address, like: {@code http://www.yanzhenjie.com}.
     */
    public RestRequest(String url) {
        this(url, RequestMethod.GET);
    }

    /**
     * Create a request
     *
     * @param url           request address, like: {@code http://www.yanzhenjie.com}.
     * @param requestMethod request method, like {@link RequestMethod#GET}, {@link RequestMethod#POST}.
     */
    public RestRequest(String url, RequestMethod requestMethod) {
        super(url, requestMethod);
    }

    @Override
    public Request setPriority(Priority priority) {
        this.priority = priority;
        return this;
    }

    @Override
    public Request setSequence(int sequence) {
        this.sequence = sequence;
        return this;
    }

    @Override
    public Priority getPriority() {
        return this.priority;
    }

    @Override
    public int getSequence() {
        return this.sequence;
    }

    @Override
    public int compareTo(Request another) {
        final Priority me = getPriority();
        final Priority it = another.getPriority();
        return me == it ? getSequence() - another.getSequence() : it.ordinal() - me.ordinal();
    }
}