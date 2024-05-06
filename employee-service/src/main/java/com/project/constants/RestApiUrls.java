package com.project.constants;

public class RestApiUrls {
    private static final String VERSION = "/v1";
    private static final String DEV = "/dev";

    private static final String ROOT = DEV + VERSION;

    public static final String EMPLOYEE = ROOT + "/employee";

    public static final String SAVE_EMPLOYEE = EMPLOYEE + "/save-employee";

    public static final String UPDATE_EMPLOYEE = EMPLOYEE + "/update-employee";

    public static final String MANAGER_UPDATE_EMPLOYEE = EMPLOYEE + "/manager-update-employee";



}
