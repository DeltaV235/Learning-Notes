/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.deltav;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author DeltaV
 */
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3, time = 5)
@Measurement(iterations = 5, time = 10)
@Threads(1)
@Fork(1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class MyBenchmark {

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(MyBenchmark.class.getSimpleName())
                .result("benchmark-result.json")
                .resultFormat(ResultFormatType.JSON)
                .build();
        new Runner(options).run();
    }

    /**
     * 3.5924825
     * sum = 155184200000
     */
    @OperationsPerInvocation(1)
    @Benchmark
    public void sumSortedArray() {
        // Generate data
        int arraySize = 32768;
        int[] data = new int[arraySize];

        Random rnd = new Random(0);
        for (int c = 0; c < arraySize; ++c) {
            data[c] = rnd.nextInt() % 256;
        }

        // !!! With this, the next loop runs faster
        Arrays.sort(data);

        // Test
//        long start = System.nanoTime();
        long sum = 0;

        for (int i = 0; i < 100000; ++i) {
            // Primary loop
            for (int c = 0; c < arraySize; ++c) {
                if (data[c] >= 128) {
                    sum += data[c];
                }
            }
        }

//        System.out.println((System.nanoTime() - start) / 1000000000.0);
//        System.out.println("sum = " + sum);
    }

    /**
     * 9.7659674
     * sum = 155184200000
     */
    @Benchmark
    public void sumUnsortedArray() {
        // Generate data
        int arraySize = 32768;
        int[] data = new int[arraySize];

        Random rnd = new Random(0);
        for (int c = 0; c < arraySize; ++c) {
            data[c] = rnd.nextInt() % 256;
        }

        // Test
//        long start = System.nanoTime();
        long sum = 0;

        for (int i = 0; i < 100000; ++i) {
            // Primary loop
            for (int c = 0; c < arraySize; ++c) {
                if (data[c] >= 128) {
                    sum += data[c];
                }
            }
        }

//        System.out.println((System.nanoTime() - start) / 1000000000.0);
//        System.out.println("sum = " + sum);
    }

}
