/*
 *  Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.wso2.carbon.identity.oauth2;

public class Oauth2ScopeConstants {

    public static final String SERVER_ERROR = "Error occurred in the server while performing the task.";
    public static final String APPLICATION_JSON = "application/json";
    public static final String DEFAULT_RESPONSE_CONTENT_TYPE = APPLICATION_JSON;
    public static final String HEADER_CONTENT_TYPE = "Content-Type";

    //default error messages
    public static final String STATUS_FORBIDDEN_MESSAGE_DEFAULT = "Forbidden";
    public static final String STATUS_NOT_FOUND_MESSAGE_DEFAULT = "Not Found";
    public static final String STATUS_INTERNAL_SERVER_ERROR_MESSAGE_DEFAULT = "Internal server error";
    public static final String STATUS_METHOD_NOT_ALLOWED_MESSAGE_DEFAULT = "Method Not Allowed";
    public static final String STATUS_BAD_REQUEST_MESSAGE_DEFAULT = "Bad Request";
    public static final String STATUS_CONFLICT_MESSAGE_RESOURCE_ALREADY_EXISTS = "Resource Already Exists";
    public static final String STATUS_CONFLICT_MESSAGE_DEFAULT = "Conflict";

    public static final String STATUS_INTERNAL_SERVER_ERROR_DESCRIPTION_DEFAULT = "The server encountered "
            + "an internal error. Please contact administrator.";
    public static final String TENANT_NAME_FROM_CONTEXT = "TenantNameFromContext";

    public static final int MAX_FILTER_COUNT = 30;

    public enum ErrorMessages {

        ERROR_CODE_UNEXPECTED("18013", "Unexpected error"),

        ERROR_CODE_FAILED_TO_REGISTER_SCOPE("18001", "Error occurred while registering scopes. "),
        ERROR_CODE_BAD_REQUEST_SCOPE_ID("18002", "Specified Scope ID is invalid or not specified."),
        ERROR_CODE_BAD_REQUEST_SCOPE_NAME("18002", "Specified Scope Name is invalid or not specified."),
        ERROR_CODE_BAD_REQUEST_SCOPE_DESCRIPTION("18003", "Specified Scope Description is invalid or not specified."),
        ERROR_CODE_CONFLICT_REQUEST_EXISTING_SCOPE("18003",
                "Scope with the key %s already exists in the system. Please use a different scope key."),
        ERROR_CODE_FAILED_TO_GET_SCOPE_BY_KEY("18004", "Error occurred while retrieving scope by scope key."),
        ERROR_CODE_FAILED_TO_GET_ALL_SCOPES("18005", "Error occurred while retrieving all available scopes."),
        ERROR_CODE_FAILED_TO_GET_ALL_SCOPES_PAGINATION("18005", "Error occurred while retrieving scopes with pagination."),
        ERROR_CODE_FAILED_TO_GET_ALL_SCOPE_KEYS("18005", "Error occurred while retrieving all available scope keys."),
        ERROR_CODE_FAILED_TO_GET_SCOPES_BY_BINDINGS("18005", "Error occurred while retrieving scopes by bindings."),
        ERROR_CODE_FAILED_TO_GET_SCOPE_KEYS_BY_BINDINGS("18005", "Error occurred while retrieving scope keys by bindings."),
        ERROR_CODE_FAILED_TO_DELETE_SCOPES("18005", "Error occurred while deleting scopes."),
        ERROR_CODE_FAILED_TO_DELETE_SCOPE_BY_ID("18005", "Error occurred while deleting scope by ID '%s.'."),
        ERROR_CODE_FAILED_TO_UPDATE_SCOPE_BY_ID("18005", "Error occurred while updating scope by ID '%s.'."),
        ERROR_CODE_FAILED_TO_FILTER_INVALID_OPERATION("18005", "Given filter operation is not supported."),
        ERROR_CODE_FAILED_TO_FILTER_UNRECOGNIZE_OPERATION("18006", "Filter operation is not recognized"),
        ERROR_CODE_FAILED_TO_FILTER_UNRECOGNIZE_ATTRIBUTE("18006", "Filter attribute is not recognized"),
        ERROR_CODE_FAILED_TO_FILTER_INVALID_ATTRIBUTE("18007", "Given filter attribute is not supported."),
        ERROR_CODE_SORTING_NOT_SUPPORTED("18008","Sorting is not supported."),
        ERROR_CODE_BAD_REQUEST_NOT_SUPPORTED_LIST_PARAM("18008","Specified list querying param/s are not supported."),;

        private final String code;
        private final String message;

        ErrorMessages(String code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public String toString() {
            return code + " - " + message;
        }

    }

    /**
     * SQL Placeholders
     */
    public static final class SQLPlaceholders {
        public static final String CONNECTOR_ID = "connector_id";
        public static final String CONNECTOR_USER_ID = "connector_user_id";
        public static final String CONNECTOR_GROUP_ID = "connector_group_id";
        public static final String USER_ID = "user_id";
        public static final String GROUP_ID = "group_id";
        public static final String TENANT_ID = "tenant_id";
        public static final String ATTRIBUTE_NAME = "attr_name";
        public static final String ATTRIBUTE_VALUE = "attr_value";
        public static final String MAPPING_DOMAIN_ID = "mapping_domain_id";
        public static final String CONNECTOR_TYPE = "connector_type";
        public static final String STATE = "state";

        public static final String LIMIT = "limit";
        public static final String OFFSET = "offset";
    }

}
