package org.example.tools.abstractions;

public interface IParse<TResult> {
    TResult parse(String line);
}
