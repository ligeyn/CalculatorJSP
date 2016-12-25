import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by admin on 25.11.2016.
 */
@WebServlet("/calculator")
public class CalculatorServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        HttpSession ses = req.getSession();
        String digit = req.getParameter("digit");
        String mathaction = req.getParameter("mathaction");
        String s = (String) ses.getAttribute("sesDigit");
        String action = (String) ses.getAttribute("sesMath");
        String temp = null;
        if (s == null && action == null) {
            ses.setAttribute("sesDigit", digit);
            ses.setAttribute("sesMath", mathaction);
            s = (String) ses.getAttribute("sesDigit");
            action = (String) ses.getAttribute("sesMath");
        } else {
            if (action.equals("+")) {
                temp = String.valueOf(Double.valueOf(s) + Double.valueOf(digit));
            } else if (action.equals("-")) {
                temp = String.valueOf(Double.valueOf(s) - Double.valueOf(digit));
            } else if (action.equals("*")) {
                temp = String.valueOf(Double.valueOf(s) * Double.valueOf(digit));
            } else if (action.equals("/")) {
                try {
                    temp = String.valueOf(Double.valueOf(s) / Double.valueOf(digit));
                    if (Double.valueOf(temp) == (Double.POSITIVE_INFINITY) || Double.valueOf(temp) == (Double.NEGATIVE_INFINITY))
                        throw new ArithmeticException();
                } catch (ArithmeticException exc) {
                    temp = "Делить на ноль нельзя!";
                }
            } else if (action.equals("=")) {
                temp = s;
            }
            ses.setAttribute("sesDigit", temp);
            ses.setAttribute("sesMath", mathaction);
            s = (String) ses.getAttribute("sesDigit");
            action = (String) ses.getAttribute("sesMath");
            if (action.equals("=")) {
                ses.setAttribute("sesDigit", null);
                ses.setAttribute("sesMath", null);
            }
        }
        resp.getWriter().write("<style>input[type=text] {display: block; height: 30px; width: 11%; border-color: #7a7790; border-radius: 3px;}    " +
                ".btn {height: 40px; width: 40px; color: white; border: solid; border-color: #7a7790; margin-right: 0.2%; font-size: 20px; border-radius: 50%; " +
                "background-color: #83747c; transition: all 0.3s;}   .btn:hover{transform: scale(.9);}  .txt {font-family: Arial; color: #83747c;}</style>" +
                "<form style=\"margin-top: 15%;\" align=\"center\" action=\"calculator\" method=\"post\">\n" +
                "<input style=\"margin: 0 auto;\" type=\"text\" name=\"digit\">\n" +
                "<p class=\"txt\">" + s + "</p>" +
                "<p class=\"txt\">" + action + "</p>" +
                "<input class=\"btn\" type = \"submit\" name = \"mathaction\" value= \"+\">\n" +
                "<input class=\"btn\" type = \"submit\" name = \"mathaction\" value= \"-\">\n" +
                "<input class=\"btn\" type = \"submit\" name = \"mathaction\" value= \"*\">\n" +
                "<input class=\"btn\" type = \"submit\" name = \"mathaction\" value= \"/\">\n" +
                "<input class=\"btn\" type = \"submit\" name = \"mathaction\" value = \"=\">\n"
        );
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write("<style>input[type=text] {display: block; height: 30px; width: 11%; border-color: #7a7790; border-radius: 3px;}    " +
                ".btn {height: 40px; width: 40px; color: white; border: solid; border-color: #7a7790; margin-right: 0.2%; font-size: 20px; border-radius: 50%; " +
                "background-color: #83747c; transition: all 0.3s;}   .btn:hover{transform: scale(.9);}  .txt {font-family: Arial; color: #83747c;}</style>" +
                "<form style=\"margin-top: 15%;\" align=\"center\" action=\"calculator\" method=\"post\">\n" +
                "<input style=\"margin: 0 auto;\" type=\"text\" name=\"digit\">\n" +
                "<p class=\"txt\">" + 0 + "</p>" +
                "<p class=\"txt\">" + null + "</p>" +
                "<input class=\"btn\" type = \"submit\" name = \"mathaction\" value= \"+\">\n" +
                "<input class=\"btn\" type = \"submit\" name = \"mathaction\" value= \"-\">\n" +
                "<input class=\"btn\" type = \"submit\" name = \"mathaction\" value= \"*\">\n" +
                "<input class=\"btn\" type = \"submit\" name = \"mathaction\" value= \"/\">\n" +
                "<input class=\"btn\" type = \"submit\" name = \"mathaction\" value = \"=\">\n"
        );

        HttpSession ses = req.getSession();
        ses.setAttribute("sesDigit", null);
        ses.setAttribute("sesMath", null);

    }

}