package org.example.LoggingFramework.config;

import org.example.LoggingFramework.formatter.Layout;

import java.util.List;

public class LayoutConfig {
    protected Layout layout;
    protected String timestampFormat;
    protected List<String> includes;
    protected List<String> includeMDCKeys;
    protected boolean rootFirst;
    protected int depth;

    public LayoutConfig(Layout layout, String timestampFormat, List<String> includes, List<String> includeMDCKeys, boolean rootFirst, int depth) {
        this.layout = layout;
        this.timestampFormat = timestampFormat;
        this.includes = includes;
        this.includeMDCKeys = includeMDCKeys;
        this.rootFirst = rootFirst;
        this.depth = depth;
    }

    public Layout getLayout() {
        return layout;
    }

    public String getTimestampFormat() {
        return timestampFormat;
    }

    public List<String> getIncludes() {
        return includes;
    }

    public List<String> getIncludeMDCKeys() {
        return includeMDCKeys;
    }



    public boolean isRootFirst() {
        return rootFirst;
    }

    public int getDepth() {
        return depth;
    }
}
