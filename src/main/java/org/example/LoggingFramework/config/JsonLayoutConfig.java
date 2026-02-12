package org.example.LoggingFramework.config;

import org.example.LoggingFramework.formatter.Layout;

import java.util.List;

public class JsonLayoutConfig extends LayoutConfig {
    private boolean prettyJson;

    public JsonLayoutConfig(Layout layout, String timestampFormat, List<String> includes, List<String> includeMDCKeys, boolean rootFirst, int depth, boolean prettyJson) {
        super(layout, timestampFormat, includes, includeMDCKeys, rootFirst, depth);
        this.prettyJson = prettyJson;
    }
}
