package servlets;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


import backend.WikiFinder;


// Extend HttpServlet class to create Http Servlet
@SuppressWarnings("serial")
public class Questions extends HttpServlet {

   

   public void init() throws ServletException {
      //Nothing particular needs to happen on the init of this Servlet
   }

   public void doGet(HttpServletRequest request, 
      HttpServletResponse response)
      throws ServletException, IOException 
   {
	   /*
	    * All of the variables needed throughout this class.
	    * Question contains the current questions
	    * Answer[1-4] contains the current possible answers
	    * Directory contains the link to if the answer is right or wrong (since it 
	    * comes back as random from the backend)
	    * Correct answer is the number of the correct answer (1-4)
	    * sc is the current score, will be changed later
	    */
	   String question;
	   String answer1, answer2, answer3, answer4;
	   String directory1, directory2, directory3, directory4;
	   int correctAnswer;
	   int sc = 0;
	   
	   
	   
	   //Gets the current session
	   HttpSession session=request.getSession(); 
	   
	   /*
	    * Gets the current score of the session. If there is no current
	    * score (at the beginning of the game), it sets the session
	    * attribute to 0
	    */
	   if(null != session.getAttribute("Score")) {
		    sc = (int)session.getAttribute("Score");
	   } else {
		   session.setAttribute("Score", 0);
		    sc = (int)session.getAttribute("Score");
	   }
	   session.setAttribute("Score",sc);
	  
	   /*
	    * Generates the new question either by pulling one from the session or making a new one. 
	    * If there is no session attribute of question (wasn't loaded in time)
	    * it makes a new question.
	    */
	   WikiFinder newQuestion = (WikiFinder)session.getAttribute("Question");
	   if(null == newQuestion) {
		   newQuestion = new WikiFinder();
	   } 
	  
	   	/*
	   	 * Sets the variables equal to the WikiFinder attributes to be displayed in the html file
	   	 */
   	  question = newQuestion.getQuestion();
      answer1 = newQuestion.getAnswerA();
      answer2 = newQuestion.getAnswerB();
      answer3 = newQuestion.getAnswerC();
      answer4 = newQuestion.getAnswerD();
      correctAnswer = newQuestion.getCorrectAnswerNumber();
      session.setAttribute("AnswerURL", newQuestion.getAnswerURL()); //Sets session attribute for answerURL so the success and fail servlets can use it
      
      /**
       * Sets the correct directory.
       */
      directory1 = "badresponse";
      directory2 = "badresponse";
      directory3 = "badresponse";
      directory4 = "badresponse";
      if(correctAnswer == 1) directory1 = "response";
      if(correctAnswer == 2) directory2 = "response";
      if(correctAnswer == 3) directory3 = "response";
      if(correctAnswer == 4) directory4 = "response";
      
      //Sets form token so that success knows to increment score
	   session.setAttribute("formToken", "u6r76fnvie3");
	   
	   
	   /*
	       * Sets response type to html and writes up the response.
	       * This is a very inefficient way to do this (at least to write it)
	       * but I needed to pass the question and answer variables into the html file.
	       * As of right now, this is the only way I know to accomplish this.
	       * 
	       * As for the html, the inline css should be moved to its own css file.
	       * It currently is within this file to make sure that everything
	       * is correct for testing purposes. Also, most things aren't repeated more
	       * than 4 times, so it isn't the worst things to do, just not the best
	       * practice.
	       */   
	  response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      try {
    	  out.print("<!DOCTYPE html>\r\n" + 
    	  		"<html>\r\n" + 
    	  		"<head>\r\n" + 
    	  		"<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\r\n" + 
    	  		"	<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\r\n" + 
    	  		"	<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\r\n" + 
    	  		"    <link rel=\"stylesheet\" href=\"styles/footer.css\">\r\n" + 
    	  		"	<link rel=\"stylesheet\" href=\"styles/styles.css\">" +
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
    	  		"</head>\r\n" + 
    	  		"<body>\r\n" + 
    	  		"\r\n" +
    	  		"<div style=\"text-align:center; padding-top:10%\">" +
    	  		"<h1>" + question +"</h1>" +
    	  		"\r\n" + 
    	  		"\r\n" 
    	  		+ "<div class=\"box-left\">" +     	  		 
    	  		"<a href=\""+directory1+"\" class=\"btn-gradient cyan large\">" + answer1 +"</a>\r\n"
    	  				 + "</div>" + "<div class=\"box-right\">" +
    	  		"<a href=\""+directory2+"\" class=\"btn-gradient purple large\">" + answer2 +"</a>\r\n" +
    	  		  
  					"</div>" + "<div class=\"box-left\">" +
    	  		"<a href=\""+directory3+"\" class=\"btn-gradient green large\">"+answer3+"</a>\r\n" + 
    	  		
					"</div>" + "<div class=\"box-right\">" +
    	  		"<a href=\""+directory4+"\" class=\"btn-gradient yellow large\">"+answer4+"</a>" +
    	  		"</div>" +
    	  		"<h1>Score: "+
    	  				 
    	  				 sc+"<h1>"
    	  				 +
    	        "<div class=\"footer\">\r\n" + 
    	        "  <div class=\"row\" style=\"margin-right:3%;margin-left:3%\">\r\n" + 
    	        "    <div class=\"col-sm-3\" style=\"margin-top:1%; margin-bottom:1%; border-right: 1px solid; border-color:rgba(255, 255, 255,.1)\">\r\n" + 
    	        "      <h3>Brett Santema</h3>\r\n" + 
    	        "      <h4>bsantema@iastate.edu</h4>\r\n" + 
    	        "      <a href=\"https://www.linkedin.com/in/brett-santema-41836014a/\">\r\n" + 
    	        "      <img src=\"images/linkedin.png\" style=\"width:9%;height:9%;padding-bottom:29px \"></a>\r\n" + 
    	        "    </div>\r\n" + 
    	        "    <div class=\"col-sm-3\" style=\"margin-top:1%; margin-bottom:1%; border-right: 1px solid; border-color:rgba(255, 255, 255,.1)\">\r\n" + 
    	        "     <h3>Ethan Vander Wiel</h3>\r\n" + 
    	        "     <h4>ehv@iastate.edu</h4>\r\n" + 
    	        "     <a href=\"https://www.linkedin.com/in/ethan-vander-wiel-4355b8158/\">\r\n" + 
    	        "     <img src=\"images/linkedin.png\" style=\"width:9%;height:9%;padding-bottom:29px\"></a>\r\n" + 
    	        "    </div>\r\n" + 
    	        "    <div class=\"col-sm-3\" style=\"margin-top:1%; margin-bottom:1%; border-right: 1px solid; border-color: rgba(255, 255, 255,.1)\">\r\n" + 
    	        "      <h3>Jack Wilkinson</h3>\r\n" + 
    	        "      <h4>jtw1@iastate.edu</h4>\r\n" + 
    	        "      <a href=\"https://www.linkedin.com/in/jack-wilkinson-19491114b/\">\r\n" + 
    	        "      <img src=\"images/linkedin.png\" style=\"width:9%;height:9%;padding-bottom:29px\"></a>\r\n" + 
    	        "    </div>\r\n" + 
    	        "    <div class=\"col-sm-3\" style=\"margin-top:1%; margin-bottom:1%\">\r\n" + 
    	        "      <h3>Willis Knox</h3>\r\n" + 
    	        "      <h4>wpknox@iastate.edu</h4>\r\n" + 
    	        "      <a href=\"https://www.linkedin.com/in/willis-knox-57a39214a/\">\r\n" + 
    	        "      <img src=\"images/linkedin.png\" style=\"width:9%;height:9%;padding-bottom:29px\"></a>\r\n" + 
    	        "    </div>\r\n" + 
    	        "  </div>\r\n" + 
    	        "</div>" +
    	  		"\r\n" + 
    	  		"\r\n" + 
    	  		"\r\n" + 
    	  		"</body>\r\n" + 
    	  		"</html>");
    	  
    	  /*
    	   * Currently my way of loading a new question in the background. 
    	   * This only works if the question is fully loaded by the time the user
    	   * guesses. However, it does save about 10 seconds off load times
    	   * if the load is successful.
    	   */
    	  WikiFinder next = new WikiFinder();
    	  session.setAttribute("Question", next);
       } finally {
          out.close();  // Always close the output writer
          
       }
   }

   public void destroy() {
   }
}