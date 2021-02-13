package com.library.name;

/**
 * Path holder (jsp pages, controller commands).
 *
 *
 */
public final class Path {

    private Path(){
        //hello
    }


    // pages
    //Librarian
    public static final String PAGE_LIBRARIAN_MENU_PAGE = "/WEB-INF/jsp/librarian/librarianMenu.jsp";

    public static final String PAGE_USER_ABONEMENT = "/WEB-INF/jsp/librarian/userAbonement.jsp";
    public static final String PAGE_READERS = "/WEB-INF/jsp/librarian/readers.jsp";
    //Common
    public static final String PAGE_CATALOG = "catalog.jsp";
    public static final String PAGE_LOGIN = "login.jsp";
    public static final String PAGE_REGISTRATION = "registration.jsp";
    public static final String PAGE_ABOUT_BOOK = "aboutBook.jsp";
    //User
    public static final String PAGE_SETTINGS = "/WEB-INF/jsp/user/settings.jsp";
    public static final String PAGE_ORDER_BOOK = "/WEB-INF/jsp/user/orderBook.jsp";
    public static final String PAGE_USER_MENU = "/WEB-INF/jsp/user/user_menu.jsp";
    public static final String PAGE_USER_BOOKS = "/WEB-INF/jsp/user/books.jsp";

    //Admin
    public static final String PAGE_ADMIN_PAGE = "/WEB-INF/jsp/admin/admin_menu.jsp";
    public static final String PAGE_USERS_LIST = "/WEB-INF/jsp/admin/users.jsp";
    public static final String PAGE_ADD_BOOK = "/WEB-INF/jsp/admin/addBook.jsp";
    public static final String PAGE_EDIT_BOOK = "/WEB-INF/jsp/admin/editBook.jsp";
    public static final String PAGE_ADD_IMAGE = "/WEB-INF/jsp/admin/addImage.jsp";
    public static final String PAGE_EDIT_USER = "/WEB-INF/jsp/admin/editUser.jsp";


    public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";
    public static final String PAGE_SUCCESS_PAGE = "/WEB-INF/jsp/successPage.jsp";



    // commands
    //public static final String COMMAND__ADMIN_MENU = "src/main/webapp/admin_menu.jsp";
    //public static final String COMMAND__USER_MENU = "src/main/webapp/user_menu.jsp";


}