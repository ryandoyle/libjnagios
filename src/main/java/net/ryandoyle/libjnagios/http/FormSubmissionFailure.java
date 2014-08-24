package net.ryandoyle.libjnagios.http;

public class FormSubmissionFailure extends RuntimeException {
    public FormSubmissionFailure(String message, Throwable e){
        super(message, e);
    }

    public FormSubmissionFailure(String message) {
        super(message);
    }
}
