package org.sw4j.tool.har.reader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.sw4j.tool.har.model.Har;
import org.sw4j.tool.har.model.Log;

import java.io.Reader;

public class HarReader {

    private final Reader source;

    public HarReader(Reader reader) {
        this.source = reader;
    }

    public Log read() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.fromJson(source, Har.class).getLog();
    }

}
