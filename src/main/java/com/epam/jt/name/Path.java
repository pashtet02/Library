package com.epam.jt.name;

/**
 * Path holder (jsp pages, controller commands).
 *
 * @author D.Kolesnikov
 *
 */
public final class Path {

    // pages
    public static final String PAGE__LOGIN = "/login.jsp";
    public static final String PAGE__ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";
    public static final String PAGE__LIST_MENU = "/WEB-INF/jsp/client/list_menu.jsp";
    public static final String PAGE__LIST_ORDERS = "/WEB-INF/jsp/admin/list_orders.jsp";
    public static final String PAGE__SETTINGS = "/WEB-INF/jsp/settings.jsp";

    // commands

    public static final String COMMAND__ADMIN_MENU = "src/main/webapp/admin_menu.jsp";
    public static final String COMMAND__USER_MENU = "src/main/webapp/user_menu.jsp";
    //public static final String COMMAND__LIST_ORDERS = "/controller?command=listOrders";
    //public static final String COMMAND__LIST_MENU = "/controller?command=listMenu";

}