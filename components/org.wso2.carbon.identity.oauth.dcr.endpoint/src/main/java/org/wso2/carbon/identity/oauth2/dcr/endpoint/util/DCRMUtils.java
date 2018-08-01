/*
 * Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.oauth2.dcr.endpoint.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.context.PrivilegedCarbonContext;
import org.wso2.carbon.identity.core.util.IdentityUtil;
import org.wso2.carbon.identity.oauth.dcr.DCRMConstants;
import org.wso2.carbon.identity.oauth.dcr.bean.ApplicationRegistrationRequest;
import org.wso2.carbon.identity.oauth.dcr.bean.ApplicationUpdateRequest;
import org.wso2.carbon.identity.oauth.dcr.bean.CustomMetadata;
import org.wso2.carbon.identity.oauth.dcr.exception.DCRMException;
import org.wso2.carbon.identity.oauth.dcr.service.DCRMService;
import org.wso2.carbon.identity.oauth2.dcr.endpoint.Exceptions.DCRMEndpointException;
import org.wso2.carbon.identity.oauth2.dcr.endpoint.dto.CustomMetadataDTO;
import org.wso2.carbon.identity.oauth2.dcr.endpoint.dto.ErrorDTO;
import org.wso2.carbon.identity.oauth2.dcr.endpoint.dto.RegistrationRequestDTO;
import org.wso2.carbon.identity.oauth2.dcr.endpoint.dto.UpdateRequestDTO;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Response;

/**
 * This class holds Utils for DCRM endpoint component
 */
public class DCRMUtils {

    private static final Log LOG = LogFactory.getLog(DCRMUtils.class);

    private static final String CONFLICT_STATUS = "CONFLICT_";
    private static final String BAD_REQUEST_STATUS = "BAD_REQUEST_";
    private static final String NOT_FOUND_STATUS = "NOT_FOUND_";
    private static final String DCRM_SERVICE_ClASS= "OAuth.DCRM.DCRMService";

    /**
     * Get the OSGI service for DCRM functionality.
     *
     * @return DCRMService instance
     * @throws DCRMEndpointException
     */
    public static DCRMService getOAuth2DCRMService() throws DCRMEndpointException {

        String serviceClassName = IdentityUtil.getProperty(DCRM_SERVICE_ClASS);
        DCRMService dcrmService = null;
        if (serviceClassName != null) {
            try {
                dcrmService = (DCRMService) PrivilegedCarbonContext.getThreadLocalCarbonContext()
                        .getOSGiService(Class.forName(serviceClassName), null);
            } catch (ClassNotFoundException e) {
                DCRMUtils.handleErrorResponse(new DCRMException(String.format("Error in getting configured " +
                        "DCRMService OSGI service: %s", dcrmService), e), LOG);
            }
        } else {
            dcrmService = (DCRMService) PrivilegedCarbonContext.getThreadLocalCarbonContext()
                    .getOSGiService(DCRMService.class, null);
        }
        return dcrmService;
    }

    public static ApplicationRegistrationRequest getApplicationRegistrationRequest(RegistrationRequestDTO registrationRequestDTO) {

        ApplicationRegistrationRequest appRegistrationRequest = new ApplicationRegistrationRequest();
        appRegistrationRequest.setClientName(registrationRequestDTO.getClientName());
        appRegistrationRequest.setRedirectUris(registrationRequestDTO.getRedirectUris());
        appRegistrationRequest.setGrantTypes(registrationRequestDTO.getGrantTypes());
        appRegistrationRequest.setCustomMetadata(convertCustomMetadata(registrationRequestDTO.getCustomMetadata()));
        return appRegistrationRequest;

    }

    public static ApplicationUpdateRequest getApplicationUpdateRequest(UpdateRequestDTO updateRequestDTO) {

        ApplicationUpdateRequest applicationUpdateRequest = new ApplicationUpdateRequest();
        applicationUpdateRequest.setClientName(updateRequestDTO.getClientName());
        applicationUpdateRequest.setRedirectUris(updateRequestDTO.getRedirectUris());
        applicationUpdateRequest.setGrantTypes(updateRequestDTO.getGrantTypes());
        applicationUpdateRequest.setCustomMetadata(convertCustomMetadata(updateRequestDTO.getCustomMetadata()));
        return applicationUpdateRequest;

    }

    public static void handleErrorResponse(DCRMException dcrmException, Log log) throws DCRMEndpointException {
        String errorCode = dcrmException.getErrorCode();
        Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;
        boolean isStatusOnly = true;
        if (errorCode != null) {
            if (errorCode.startsWith(CONFLICT_STATUS)) {
                status = Response.Status.BAD_REQUEST;
                isStatusOnly = false;
            } else if (errorCode.startsWith(BAD_REQUEST_STATUS)) {
                status = Response.Status.BAD_REQUEST;
                isStatusOnly = false;
            } else if (errorCode.startsWith(NOT_FOUND_STATUS)) {
                status = Response.Status.UNAUTHORIZED;
            }
        }
        throw buildDCRMEndpointException(status, errorCode, dcrmException.getMessage(), isStatusOnly);
    }

    /**
     * Logs the error, builds a DCRMEndpointException with specified details and throws it
     *
     * @param status    response status
     * @param throwable throwable
     * @throws DCRMEndpointException
     */
    public static void handleErrorResponse(Response.Status status, Throwable throwable,
                                           boolean isServerException, Log log)
            throws DCRMEndpointException {

        String errorCode;
        if (throwable instanceof DCRMException) {
            errorCode = ((DCRMException) throwable).getErrorCode();
        } else {
            errorCode = DCRMConstants.ErrorMessages.ERROR_CODE_UNEXPECTED.toString();
        }

        if (isServerException) {
            if (throwable == null) {
                log.error(status.getReasonPhrase());
            } else {
                log.error(status.getReasonPhrase(), throwable);
            }
        }
        throw buildDCRMEndpointException(status, errorCode, throwable == null ? "" : throwable.getMessage(),
                isServerException);
    }

    private static DCRMEndpointException buildDCRMEndpointException(Response.Status status,
                                                                    String code, String description,
                                                                    boolean isStatusOnly) {

        if (isStatusOnly) {
            return new DCRMEndpointException(status);
        } else {
            String error = DCRMConstants.ErrorCodes.INVALID_CLIENT_METADATA;
            if (code.equals(DCRMConstants.ErrorMessages.BAD_REQUEST_INVALID_REDIRECT_URI.toString())) {
                error = DCRMConstants.ErrorCodes.INVALID_REDIRECT_URI;
            }

            ErrorDTO errorDTO = new ErrorDTO();
            errorDTO.setError(error);
            errorDTO.setErrorDescription(description);
            return new DCRMEndpointException(status, errorDTO);
        }
    }

    private static List<CustomMetadata> convertCustomMetadata(List<CustomMetadataDTO> customMetadataDTOList) {

        List<CustomMetadata> customMetadataList = new ArrayList<>();
        for (CustomMetadataDTO customMetadataDTO : customMetadataDTOList) {
            CustomMetadata customMetadata = new CustomMetadata();
            customMetadata.setName(customMetadataDTO.getName());
            customMetadata.setValue(customMetadataDTO.getValue());
            customMetadataList.add(customMetadata);
        }
        return customMetadataList;
    }

}
