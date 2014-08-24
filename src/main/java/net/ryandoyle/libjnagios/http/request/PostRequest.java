package net.ryandoyle.libjnagios.http.request;

import net.ryandoyle.libjnagios.http.domain.Form;
import net.ryandoyle.libjnagios.http.FormSubmissionFailure;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;

public class PostRequest extends Request {

    private final Form form;

    public PostRequest(HttpURLConnection connection, Form form) throws IOException {
        super(connection);
        this.form = form;
        initialiseConnectionForWriting();
    }


    public Request postForm() throws IOException {
        writeRequest(form.toString());
        return this;
    }

    private void initialiseConnectionForWriting() {
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    }

    private void writeRequest(String output) throws FormSubmissionFailure {
        DataOutputStream outputStream;
        try {
            outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(output);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new FormSubmissionFailure("Could not get output stream", e);
        }
    }
}
