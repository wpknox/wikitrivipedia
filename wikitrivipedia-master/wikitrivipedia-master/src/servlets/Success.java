package servlets;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;



// Extend HttpServlet class to create Http Servlet
@SuppressWarnings("serial")
public class Success extends HttpServlet {


   public void init() throws ServletException {
	 //Nothing particular needs to happen on the init of this Servlet
   }

   public void doGet(HttpServletRequest request, 
      HttpServletResponse response)
      throws ServletException, IOException 
   {
	   //Gets the current user session
	  HttpSession session= request.getSession(false); 
	  
	  
	  /*
	   * Session attribute that creates a form token. This form token
	   * ensures that a user cannot simply refresh the page
	   * to gain more points. The form token is then set to a different string
	   * after adding the score
	   */
	  
	  String checkAgainst = (String)session.getAttribute("formToken");
	  String check = "u6r76fnvie3";
	  int sc=(int)session.getAttribute("Score");
	  if(check == checkAgainst) {
		  session.setAttribute("Score", sc + 1); //Adds to current session score
	  }
	  session.setAttribute("formToken", "wrong");
	  
	  /*
	   * Creates int variable to display as score in html write-up and 
	   * String variable to display the URL of the correct answer
	   */
	  sc=(int)session.getAttribute("Score"); 
	  String answerURL = (String)session.getAttribute("AnswerURL");
	  
      
      /*
       * Sets response type to html and writes up the response.
       * This is a very inefficient way to do this (at least to write it)
       * but I needed to pass the score and answerURL into the html file.
       * As of right now, this is the only way I know to accomplish this.
       * 
       * As for the html, the inline css should be moved to its own css file.
       * It currently is within this file to make sure that everything
       * is correct for testing purposes. Also, most things aren't repeated more
       * than 4 times, so it isn't the worst things to do, just not the best
       * practice.
       */
	  response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      out.println("<!DOCTYPE html>\r\n" + 
      		"<html>\r\n" + 
      		"<head>\r\n" + 
      		"<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\r\n" + 
      		"	<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\r\n" + 
      		"	<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\r\n" + 
      		"    <link rel=\"stylesheet\" href=\"styles/footer.css\">\r\n" + 
      		"	<link rel=\"stylesheet\" href=\"styles/styles.css\">\r\n" + 
      		"<style>\r\n" + 
      		".button {\r\n" + 
      		"    background-color: #4CAF50;\r\n" + 
      		"    border: none;\r\n" + 
      		"    color: white;\r\n" + 
      		"    padding: 15px 32px;\r\n" + 
      		"    text-align: center;\r\n" + 
      		"    text-decoration: none;\r\n" + 
      		"    display: inline-block;\r\n" + 
      		"    font-size: 16px;\r\n" + 
      		"    margin: 4px 2px;\r\n" + 
      		"    cursor: pointer;\r\n" + 
      		"}\r\n" + 
      		"</style>\r\n" + 
      		"<meta charset=\"UTF-8\">\r\n" + 
      		"<title>Response Page</title>\r\n" + 
      		"</head>\r\n" + 
      		"<body>\r\n" + 
      		"<div class =\"right\" style=\"text-align:center; padding-top:15%\">\r\n" + 
      		"<h1>Congrats! You got the right answer!</h1>\r\n" + 
      		"<a href=\"question\" class=\"btn-gradient cyan large\"><h1>Next Question!</h1></a>\r\n" + 
      		"</div>\r\n<br/>" + 
      		"<h1 style=\"text-align:center\">Score: "+sc +"<br/> Answer: <a href=\"" + answerURL +"\" target=\"_blank\">" + answerURL + "</a></h1>"+
      		"<div class=\"footer\">\r\n" + 
      		"  <div class=\"row\" style=\"margin-right:3%;margin-left:3%\">\r\n" + 
      		"    <div class=\"col-sm-3\" style=\"margin-top:1%; margin-bottom:1%; border-right: 1px solid; border-color:rgba(255, 255, 255,.1)\">\r\n" + 
      		"      <h3>Brett Santema</h3>\r\n" + 
      		"      <h4>bsantema@iastate.edu</h4>\r\n" + 
      		"      <a href=\"https://www.linkedin.com/in/brett-santema-41836014a/\">\r\n" + 
      		"      <img src=\"images/linkedin.png\" style=\"width:9%;height:9%;padding-bottom:3px \"></a>\r\n" + 
      		"    </div>\r\n" + 
      		"    <div class=\"col-sm-3\" style=\"margin-top:1%; margin-bottom:1%; border-right: 1px solid; border-color:rgba(255, 255, 255,.1)\">\r\n" + 
      		"     <h3>Ethan Vander Wiel</h3>\r\n" + 
      		"     <h4>ehv@iastate.edu</h4>\r\n" + 
      		"     <a href=\"https://www.linkedin.com/in/ethan-vander-wiel-4355b8158/\">\r\n" + 
      		"     <img src=\"images/linkedin.png\" style=\"width:9%;height:9%;padding-bottom:3px\"></a>\r\n" + 
      		"    </div>\r\n" + 
      		"    <div class=\"col-sm-3\" style=\"margin-top:1%; margin-bottom:1%; border-right: 1px solid; border-color: rgba(255, 255, 255,.1)\">\r\n" + 
      		"      <h3>Jack Wilkinson</h3>\r\n" + 
      		"      <h4>jtw1@iastate.edu</h4>\r\n" + 
      		"      <a href=\"https://www.linkedin.com/in/jack-wilkinson-19491114b/\">\r\n" + 
      		"      <img src=\"images/linkedin.png\" style=\"width:9%;height:9%;padding-bottom:3px\"></a>\r\n" + 
      		"    </div>\r\n" + 
      		"    <div class=\"col-sm-3\" style=\"margin-top:1%; margin-bottom:1%\">\r\n" + 
      		"      <h3>Willis Knox</h3>\r\n" + 
      		"      <h4>wpknox@iastate.edu</h4>\r\n" + 
      		"      <a href=\"https://www.linkedin.com/in/willis-knox-57a39214a/\">\r\n" + 
      		"      <img src=\"images/linkedin.png\" style=\"width:9%;height:9%;padding-bottom:3px\"></a>\r\n" + 
      		"    </div>\r\n" + 
      		"  </div>\r\n" + 
      		"</div>\r\n" + 
      		"</body>\r\n" + 
      		"</html>");
   }

   public void destroy() {
   }
}