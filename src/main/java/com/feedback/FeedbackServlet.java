package com.feedback;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Student Feedback App
 *  - doPost(): saves a new feedback (changes data on the server)
 *  - doGet() : shows / searches feedback (only reads data)
 */
@WebServlet("/feedback")
public class FeedbackServlet extends HttpServlet {

    // Simple in-memory storage (data is lost when Tomcat restarts)
    private final List<String[]> feedbackList =
            Collections.synchronizedList(new ArrayList<>());

    // GET = only read. Handles: /feedback  and  /feedback?search=Rohit
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        String search = req.getParameter("search");
        if (search == null) search = "";

        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();

        out.println("<html><head><title>All Feedback</title></head><body>");
        out.println("<h2>All Feedback</h2>");
        out.println("<p>Search was sent with GET, see the address bar: ?search="
                + escape(search) + "</p>");

        int count = 0;
        synchronized (feedbackList) {
            for (String[] f : feedbackList) {
                boolean match = search.isEmpty()
                        || f[0].toLowerCase().contains(search.toLowerCase());
                if (match) {
                    out.println("<p><b>" + escape(f[0]) + "</b>: " + escape(f[1]) + "</p>");
                    count++;
                }
            }
        }
        if (count == 0) out.println("<p>No feedback found.</p>");

        out.println("<br><a href='index.html'>Back to form</a>");
        out.println("</body></html>");
    }

    // POST = send data to be saved. Data travels in the request body, not the URL.
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String message = req.getParameter("message");

        if (name != null && !name.trim().isEmpty()
                && message != null && !message.trim().isEmpty()) {
            feedbackList.add(new String[]{name.trim(), message.trim()});
        }

        // Redirect to GET so that pressing Refresh does not submit the form twice
        res.sendRedirect("feedback");
    }

    // Stops users from injecting HTML/JavaScript into the page
    private String escape(String s) {
        return s.replace("&", "&amp;").replace("<", "&lt;")
                .replace(">", "&gt;").replace("\"", "&quot;");
    }
}
