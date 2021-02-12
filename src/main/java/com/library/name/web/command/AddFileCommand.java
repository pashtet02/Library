package com.library.name.web.command;

import com.library.name.Path;
import com.library.name.dao.BookDao;
import com.library.name.entity.Book;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class AddFileCommand extends Command {
    private static String uploadPath = "src/main/webapp/view";
    private static Map<String, String> map = new HashMap<String, String>();
    private static String fileName;
    private static final Logger log = Logger.getLogger(AddFileCommand.class);

    /**
     * Execution method for command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //process only if its multipart content
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(request);

                for (FileItem item : multiparts) {
                    if (!item.isFormField()) {
                        fileName = new File(item.getName()).getName();
                        log.info("FILENAME: " + fileName);

                        item.write(new File(uploadPath + File.separator + fileName));
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
        BookDao bookDao = BookDao.getInstance();
        HttpSession session = request.getSession();
        System.out.println("BOOK TITLE" + request.getParameter("bookTitle"));
        Book book = bookDao.getByTitle((String) session.getAttribute("bookTitle"));
        System.out.println("Book: " + book);
        book.setImage(fileName);
        System.out.println("IMAGE: " + book.getImage());

            bookDao.update(book);

        log.debug("Command finished");
        String message = "Book " + book.getTitle() + "added!!!";
        request.setAttribute("massage", message);

        request.setAttribute("commandName", "catalog");
        return Path.PAGE__SUCCESS_PAGE;
    }
}

