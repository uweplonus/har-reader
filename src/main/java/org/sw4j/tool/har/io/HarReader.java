package org.sw4j.tool.har.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Reader;
import org.sw4j.tool.har.model.Har;
import org.sw4j.tool.har.model.Log;

public class HarReader {

    private final Reader source;

    public HarReader(Reader reader) {
        this.source = reader;
    }

    public Log read(boolean check) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Har har = gson.fromJson(source, Har.class);
        if (check) {
            HarValidator.checkRequiredAttributes(har);
        }
        return har.getLog();
    }

}
