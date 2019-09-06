# Team: Cavalier
Brandon Hurrington, Chris Coulon, Steve Grant & Manish KC
Project Name: PipeLine
# Overview:
An app to track the progress through the Amazon Apprenticeship program, including milestones, significant dates, documents, and benchmarks. 

Link to the project: http://team-cavalier-pipeline.us-east-2.elasticbeanstalk.com/

# Core Implementations:
- An admin could register on the app.
- The admin could create group, add tasks to the group.
- The admin could edit the existing tasks.
- The admin could generate a link for the user to register on the app.
- The link could be used to sign up. 
- After registration, the user could see the group that has been assigned and the tasks associated with it. 
- The user could verify the completion of the task. 



# Project Components
- 

1. Build a RESTful API that is a usable product.
    - Must have at least 2 models, likely related, with data stored in a database.
    - Must have views for HTML, JSON, or any combination of the two.
    - Must have at least 2 controller files, with full CRUD on at least one resource.
    - Should most likely contain Auth using Spring.
        - Exception: if you really want to build something hardware-related instead of a web app

2. Document your application.
    - Include a README.md file with:
        - An overview of the application and its functionality
        - Screenshots of the application
        - A link to the deployed application
        - A list of technologies used in the application
        - Directions to clone the repo and get the application running on your own computer
    - Include relevant comments in your code.
        - Do NOT include irrelevant comments or dead code in your application.

3. Test your application.
    - Must contain unit tests for all getters and setters, as well as any utility methods.
    - Must contain integration tests for at least all non-authenticated GET request endpoints.

4. Deploy your application to the world.
    - Your application must be available at a custom domain name, using AWS ElasticBeanstalk or similar.
