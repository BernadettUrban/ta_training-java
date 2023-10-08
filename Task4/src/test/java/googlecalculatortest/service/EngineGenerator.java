package googlecalculatortest.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import googlecalculatortest.model.Engine;

import java.io.File;
import java.io.IOException;

public class EngineGenerator {

    public Engine readEngineFromConfig() throws IOException {
        Engine engine;
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        engine = mapper.readValue(new File(
                "src/test/resources/engineconfig.yaml"), Engine.class);
        return engine;

    }





}
