Source Code for the Wikitrivipedia site. Site: http://wikitrivipedia.us-east-2.elasticbeanstalk.com/ This site is not yet optimized for mobile, but does work.

Contributors: Ethan Vander Wiel, Brett Santema, Jack Wilkinson, Willis Knox

The wikitrivipedia game was made for the ISU Fall 2018 Hackathon. The game finds a random Wikipedia page, creates a question based off the
page, takes the title of the page as one of the answers, then searches three more times for other Wikipedia pages within the same category
for incorrect answers. The user plays until they get a question wrong, then the game is over.

There are three parts to the project in the files attached. The part I worked mostly, along with Jack Wilkinson was the testing background in Java. We worked alongside Brett Santema in the early stages of the project to get a test environment set up so it the game could be playable in an IDE environment. All of our work is in the wikipediaDay folder.

The second part of the project is in both the wikipediaDay folder, and the wikitrivipedia-master. In the former, it is called Question.java and in the later, it is called WikiFinder.java. This is the file that Brett Santema made that scrapped Wikipedia for the question itself.

The third part was the front end and it was written by Ethan Vander Wiel I have copied his description of what it contains below:
How the front end works: The front end of this project is built on java servlets. These servlets help to maintain and redirect http requests from the users. The map of this project is as follows:

1. User arrives at index.html (home page)
2. After clicking start, an http request for site/question is made which directs to the "Questions.java" servlet. This servlet then accesses the WikiFinder.java file to get the question and answers, and reponds with html.
3. If the user gets the question right, they are directed to the site/response page. This shows their current score, the link to the right wikipedia page, and allows them to click to a new question. If they click to the new question, they are redirected back to site/question (step 1).
4. If the user gets the question wrong, they are directed to the site/badresponse page. This shows their end score, the link to the right wikipedia page, and allows them to click to restart the game. If they click, they are redirected to index.html (step 1).

-Ethan Vander Wiel

The major issue of this project is load times. Since we did not use any APIs the program has to load and new Wikipedia page for every question, and if it lands on a page that we dictated would not be good for a question, it has to load a new page. It may go through this process several times, causing the time between questions to vary greatly.

Overall, this project was a fun introduction to working in a group environment in a short amount of time for my group members and I.
