package com.library.name;

/**
 * Path holder (jsp pages, controller commands).
 *
 *
 */
public final class Path {

    // pages
    //Librarian
    public static final String PAGE__LIBRARIAN_MENU_PAGE = "/WEB-INF/jsp/librarian/librarianMenu.jsp";

    public static final String PAGE__USER_ABONEMENT = "/WEB-INF/jsp/librarian/userAbonement.jsp";
    public static final String PAGE__READERS = "/WEB-INF/jsp/librarian/readers.jsp";
    //Common

    //User
    public static final String PAGE__SETTINGS = "/WEB-INF/jsp/user/settings.jsp";
    public static final String PAGE__ORDER_BOOK = "/WEB-INF/jsp/user/orderBook.jsp";
    public static final String PAGE__USER_MENU = "/WEB-INF/jsp/user/user_menu.jsp";
    public static final String PAGE__USER_BOOKS = "/WEB-INF/jsp/user/books.jsp";

    //Admin
    public static final String PAGE__ADMIN_PAGE = "/WEB-INF/jsp/admin/admin_menu.jsp";
    public static final String PAGE__USERS_LIST = "/WEB-INF/jsp/admin/users.jsp";
    public static final String PAGE__ADD_BOOK = "/WEB-INF/jsp/admin/addBook.jsp";
    public static final String PAGE__ADD_IMAGE = "/WEB-INF/jsp/admin/addImage.jsp";
    public static final String PAGE__EDIT_USER = "/WEB-INF/jsp/admin/editUser.jsp";


    public static final String PAGE__ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";
    public static final String PAGE__SUCCESS_PAGE = "/WEB-INF/jsp/successPage.jsp";



    // commands

    public static final String COMMAND__ADMIN_MENU = "src/main/webapp/admin_menu.jsp";
    public static final String COMMAND__USER_MENU = "src/main/webapp/user_menu.jsp";


}