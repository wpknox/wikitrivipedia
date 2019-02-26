# wikitrivipedia
Source Code for the Wikitrivipedia site.
Site: http://wikitrivipedia.us-east-2.elasticbeanstalk.com/ 
This site is not yet optimized for mobile, but does work.

Ethan Vander Wiel - ehv@iastate.edu - ethanvdub64@iastate.edu

The wikitrivipedia site was initially made for the hackathon HackISU 2018.
As a general summary, wikitrivipedia finds a random wikipedia page, creates a question out of it, and creates four answers
that are within the same category and dispalys them in an online quiz format. The game keeps track of individual player scores
and will display that at all times. Currently, the rules are that if you miss one question you lose, although that can easily
be changed in the future.

What I, Ethan Vander Wiel, created:
I did the entirety of the front end for the site, including the interaction with the html, servlets,
and the underlying html scraping java file. 
I also got the site deployed to aws and managed the ec2 instance for the site.
The files I created are:

The three main servlets:
  1. Fail.java
  2. Questions.java
  3. Success.java

The web side files:
  1. index.html
  2. web.xml
  3. footer.css
  4. styles.css

Stylistically, these pages are very much in a prototype stage.
I also imported the linkedin png, the HackISU logo, and the font

Within this project, the file I did not create was: WikiFinder.java. This was made by bsantema@iastate.edu using jsoup. 
The other two members of the project did not create any files currently within this directory, but they played an important role in
prototyping the project using a jFrame variant and helping to create the key ideas and rules of the game (jtw1@iastate.edu and wpknox@iastate.edu).



How the front end works:
The front end of this project is built on java servlets. These servlets help to maintain and redirect http requests from the users.
The map of this project is as follows: 
1. User arrives at index.html (home page)
2. After clicking start, an http request for site/question is made which directs to the "Questions.java" servlet. 
  This servlet then accesses the WikiFinder.java file to get the question and answers, and reponds with html. 
3. If the user gets the question right, they are directed to the site/response page. This shows their current score, the 
link to the right wikipedia page, and allows them to click to a new question. If they click to the new question, they are redirected
back to site/question (step 1).
4. If the user gets the question wrong, they are directed to the site/badresponse page. This shows their end score, the link to
the right wikipedia page, and allows them to click to restart the game. If they click, they are redirected to index.html (step 1).

Each inidivual servlet is thoroughly commented out. For more information on http and servlets, look:
https://www.tutorialspoint.com/servlets/servlets-client-request.htm

Current bugs/features we are working on:
1. Load times are very slow. This is mainly because the search has to find a random wiki page that fits a certain set of predefined
rules that we made. These rules help to encourage quality questions. I have tried to optimize the system by 
having the questions loaded in the background. This currently works, as long as the user does not answer the question by the time
the next one is loaded (still not ideal).
2. If a user is in the middle of a quiz and then goes back to index, their score is maintained. This should be an user fix using
a form token, but we havn't yet decided if it is a bug or a feature (keeping your same score if you haven't lost yet).
3. Graphically, there are some bugs that crop up rarely. First off, the site is not yet optimized for mobile, so some of the 
boxes and buttons are not rendered well. Secondly, there is a bug every once in a while that causes the answer boxes to appear
all over the site. This is fixed after answering the question. The graphics are still very much in a prototype phase.

If there are any other bugs that you notice, please email them to me at ethanvdub64@gmail.com or ehv@iastate.edu.
If you have any questions about the code, feel free to ask me at those same emails.

  
