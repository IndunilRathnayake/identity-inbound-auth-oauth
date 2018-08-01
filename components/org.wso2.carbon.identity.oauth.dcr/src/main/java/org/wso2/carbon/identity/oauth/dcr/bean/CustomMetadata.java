package org.wso2.carbon.identity.oauth.dcr.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * This holds the custom client metadata object.
 */
public class CustomMetadata {

    private String name = null;

    private List<String> value = new ArrayList<String>();

    /**
     * Get name of the client metadata.
     *
     * @return name of the client metadata
     */
    public String getName() {

        return name;
    }

    /**
     * Set name of the client metadata.
     *
     * @param name name of the client metadata
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * Get value of the client metadata.
     *
     * @return value of the client metadata
     */
    public List<String> getValue() {

        return value;
    }

    /**
     * Set value of the client metadata.
     *
     * @param value value of the client metadata
     */
    public void setValue(List<String> value) {

        this.value = value;
    }
}
