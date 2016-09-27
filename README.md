###FLICK-HUNTER PROJECT  

###Demo URL
http://flickhunter.herokuapp.com

###Introduction
This search program allows user to perform various queries on the available **movie data collection**.   

#### Features/Functionalities
1. **Vector Space Model Constructon**
    a. The data file is read and tokenized.
    b. Punctuations, delimiters and stopwords have been removed.
    c. The generated dictionary of words contains stemmed words.
    d. Each term has a posting list associated with it containing all the documents that contain the term and their tfidf values.

2. **Search Functionality:** 
	It has search option that asks user for query string to perform search on fields like title, plot and cast.
		
3. **Generated Output:** 
    a. The search result is obtained by using vector space model.
    b. All the potential outcomes are ranked and displayed accordingly using tf-idf calculations.
	
### Technologies

    Java 1.7
    Glassfish 4
    Maven
    Eclipse
 		
### Required Modules
	Requires JRE to be installed previously on the machine. Should have JRE 1.7 or greater to get best results.
	Requires Maven to generate a WAR file for Deployment.
	Requires a Java application server such as Glassfish or Apache Tomcat. Glassfish 4 is recommended.
	
### Installation	
    1. Install Maven on machine.(https://maven.apache.org/install.html)
    2. Open terminal or command prompt and go to the project source directory.
    3. Run the command : "mvn clean install", this will generate a WAR file of the project under "target" folder and also download the dependencies of the project.
    4. Install an application server(Glassfish is used for testing this project)(https://glassfish.java.net/docs/4.0/installation-guide.pdf)
    5. On Glassfish server, create a domain if not already created (if installed with default configurations, skip this step as a default domain "domain1" is created while installing).
    6. Deploy the application WAR on server under Applications sub menu. (Glassfish Admin Console can be found at "http://localhost:4848" to do complete this step)
    7. Change the context Path of the application to "/FlickHunter" while deploying the application. The Deployment process will take a few minutes, please be patient.
    8. Once deployed correctly, the application can be accessed at :"http://localhost:8080/FlickHunter/index.html" (Default port for Glassfish deployments is 8080)
    9. Use the text box to search for the movies and look at the ranked results.

#### Developed by
* Aditya Padhye
* Chevelyne Melvin De'Mello
* Pooja Sharma
