package com.project.utility.constants;

public class RestApiUrls {
    //ROOTS
    private static final String VERSION = "/v1";
    private static final String DEV = "/dev";

    public static final String ROOT = DEV + VERSION;
    public static final String REQUIREMENTS = ROOT + "/requirements";

    public static final String MANAGER = REQUIREMENTS + "/manager";
    public static final String EMPLOYEE = REQUIREMENTS + "/employee";

    //MANAGER
    public static final String ADD_LEAVE = "/add-leave";
    public static final String APPROVE_LEAVE = "/approve-leave";
    public static final String REJECT_LEAVE = "/reject-leave";
    public static final String GET_ALL_LEAVES_OF_EMPLOYEE = "/get-all-leaves-of-employee";
    public static final String GET_ALL_PENDING_LEAVES_OF_EMPLOYEES = "/get-all-pending-leaves-of-employees";

    //EMPLOYEE
    public static final String FIND_ALL_MY_LEAVES = "/find-all-my-leaves";
    public static final String REQUEST_LEAVE = "/request-leave";
    public static final String FIND_EMPLOYEE_BY_TOKEN = "/find-employee-by-token";
    public static final String GET_ALL_EQUIPMENT_OF_EMPLOYEE = "/get-all-equipment-of-employee";
    public static final String ADD_EQUIPMENT = "/add-equipment";
    public static final String RETURN_EQUIPMENT = "/return-equipment";
    public static final String REQUEST_EQUIPMENT = "/request-equipment";
}
