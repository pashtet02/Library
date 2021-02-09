package com.library.name.web.command;

import com.library.name.Path;
import com.library.name.dao.BookDao;
import com.library.name.entity.Book;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.library.name.dao.Dao.log;

public class AddFileCommand extends Command {
    private static String uploadPath = "E:\\upload";
    private static Map<String, String> map = new HashMap<String, String>();
    private static String fileName;
    /**
     * Execution method for command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getParameter("title") == null) {
            return "addBook.jsp";
        }

        //process only if its multipart content
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(request);

                for (FileItem item : multiparts) {
                    if (!item.isFormField()) {
                        fileName = new File(item.getName()).getName();
                        item.write(new File(uploadPath + File.separator + fileName));
                    }

                    // Process a regular form field
                    if (item.isFormField()) {
                        String name = item.getFieldName();
                        String value = item.getString();
                        map.put(name, value);
                    }
                }

                //File uploaded successfully
                request.setAttribute("fileMessage", "File Uploaded Successfully");
            } catch (Exception ex) {
                request.setAttribute("fileMessage", "File Upload Failed due to " + ex);
            }

        } else {
            request.setAttribute("fileMessage",
                    "Sorry this Servlet only handles file upload request");
        }

        System.out.println("FILE UPL COM MAP: " + map);
        BookDao bookDao = BookDao.getInstance();
        Book book = new Book();
        book.setTitle(map.get("title"));
        book.setAuthor(map.get("author"));
        book.setISBN(Long.parseLong(map.get("ISBN")));
        book.setPublisher(map.get("publisher"));
        book.setNumber(Integer.parseInt(map.get("number")));
        book.setImage(fileName);
        System.out.println("Command Add book: " + book);

        try {
            bookDao.save(book);
        } catch (SQLException throwables) {
            String errorMessage = throwables.getMessage();
            request.setAttribute("errorMessage", errorMessage);
            log.error("Set the request attribute: errorMessage --> " + errorMessage);
            return Path.PAGE__ERROR_PAGE;
        }

        log.debug("Command finished");
        String message = "Book " + book.getTitle() + "added!!!";
        request.setAttribute("massage", message);

        return Path.PAGE__SUCCESS_PAGE;
    }
}

