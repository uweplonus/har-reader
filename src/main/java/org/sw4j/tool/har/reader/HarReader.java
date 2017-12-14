package org.sw4j.tool.har.reader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.sw4j.tool.har.model.Har;
import org.sw4j.tool.har.model.Log;

import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

public class HarReader {

    private final Reader source;

    public HarReader(Reader reader) {
        this.source = reader;
    }

    public Log read(boolean check) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Har har = gson.fromJson(source, Har.class);
        if (check) {
            checkRequiredAttributes(har);
        }
        return har.getLog();
    }

    public static List<String[]> getMissingRequiredAttributes(Har har) {
        List<String[]> result = new LinkedList<>();
        if (har == null) {
            return result;
        }
        Log log = har.getLog();
        if (log == null) {
            result.add(new String[]{"har", "log"});
        }
        if (log != null && log.getVersion() == null) {
            result.add(new String[]{"har.log", "version"});
            throw new AttributeRequiredException("har.log", "version");
        }
        return result;
    }

    public static void checkRequiredAttributes(Har har) throws AttributeRequiredException {
        List<String[]> missingAttributes = getMissingRequiredAttributes(har);
        if (!missingAttributes.isEmpty()) {
            String[] firstEntry = missingAttributes.get(0);
            if (firstEntry.length != 2) {
                throw new IllegalArgumentException("Expected an entry in the missingAttributes with 2 elements.");
            }
            throw new AttributeRequiredException(firstEntry[0], firstEntry[1]);
        }
    }

}
