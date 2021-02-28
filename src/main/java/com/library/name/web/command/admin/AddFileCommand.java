package com.library.name.web.command.admin;

import com.library.name.Path;
import com.library.name.dao.BookDao;
import com.library.name.entity.Book;
import com.library.name.web.command.Command;
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
import java.util.List;

public class AddFileCommand extends Command {
    private String fileName;
    private static final Logger log = Logger.getLogger(AddFileCommand.class);

    /**
     * Execution method for command.
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return Address to go once the command is executed.
     */
    @SuppressWarnings("unchecked")
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

                        item.write(new File("src/main/webapp/view" + File.separator + fileName));
                    }
                }

                //File uploaded successfully
                request.setAttribute("fileMessage", "File Uploaded Successfully");
            } catch (Exception ex) {
                request.setAttribute("fileMessage", "File Upload Failed due to " + ex);
            }

        } else return Path.PAGE_ADD_IMAGE;

        BookDao bookDao = BookDao.getInstance();
        HttpSession session = request.getSession();
        log.debug("BOOK TITLE" + session.getAttribute("bookTitle"));
        Book book = bookDao.getByTitle((String) session.getAttribute("bookTitle"));
        log.debug("Book: " + book);
        String message;
        if (fileName != null && !fileName.isEmpty()){
            book.setImage(fileName);
        } else if (book.getImage() == null || book.getImage().isEmpty()){
            book.setImage("testicon.png");
        }
        log.debug("IMAGE: " + book.getImage());

        bookDao.update(book);

        log.debug("Command finished");
        message = "Book " + book.getTitle() + "added!!!";
        request.setAttribute("massage", message);

        String path = "/library/controller?command=catalog&bookid=" + book.getId();
        response.sendRedirect(path);
        return null;
    }
}

