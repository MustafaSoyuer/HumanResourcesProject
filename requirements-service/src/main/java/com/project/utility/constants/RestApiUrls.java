package com.project.utility.constants;

public class RestApiUrls {
    private static final String VERSION = "/v1";
    private static final String API = "/api";
    private static final String TEST = "/test";
    private static final String DEV = "/dev";

    private static final String ROOT = DEV + VERSION;
    public static final String BREAK = ROOT + "/break";
    public static final String EQUIPMENTS = ROOT + "/equipments";
    public static final String EXPENSES = ROOT + "/expenses";
    public static final String LEAVE = ROOT + "/leave";
    public static final String PAYMENT = ROOT + "/payment";
    public static final String SHIFT = ROOT + "/shift";

    public static final String ADD = "/add";
    public static final String UPDATE = "/update";
    public static final String DELETE = "/delete";
    public static final String GET_ALL = "/get-all";
    public static final String GET_BY_ID = "/get-by-id";
}
