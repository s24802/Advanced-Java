package com.example.safe;


import com.example.safe.repeaters.IRepeater;

public class SafeInvoker implements SafeInvoking{
    private final IRepeater repeater;

    public SafeInvoker(IRepeater repeater) {
        this.repeater = repeater;
    }


    @Override
    public InvokerResult SafeInvoke(NotSafeAction action) {
        boolean sucess = true;
        Exception exception = null;
        try {
            action.execute();
            return new InvokerResult(null, true);
        } catch (Exception ex) {
            repeater.For(ex).waiting();
            sucess = false;
            exception = ex;
            while (repeater.shouldRetry()) {
                try {
                    action.execute();
                    return new InvokerResult(null, true);
                } catch (Exception ex2) {
                    repeater.For(ex2).waiting().retry();
                    if(!repeater.shouldRetry()){
                        exception = ex2;
                        sucess = false;
                    }
                }
            }
        }
        return new InvokerResult(exception, sucess);
    }
}
