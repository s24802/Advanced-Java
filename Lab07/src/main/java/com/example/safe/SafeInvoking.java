package com.example.safe;

import java.util.function.Consumer;

/**
 * Obiekt tego interfejsu będzie wykonywał akcję, która może wyrzucić błąd,
 * jeśli błąd wystąpi to tworzy obiekt Repeatera, dla konkretnego błedu,
 * i powtarza wykonanie akcji aż do skutku,
 * albo aż maksymalna ilość wystąpienia błędu zostanie osiągnięta
 */
public interface SafeInvoking {
    InvokerResult SafeInvoke(NotSafeAction action);

    record InvokerResult(Exception exception, boolean isSuccess){
        public void onUnhandledException(Consumer<Exception> consumer){
            if(isSuccess)return;
            consumer.accept(exception);
        }
    }
}
