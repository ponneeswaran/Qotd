# Qotd
AW Final Project
Client ID:321152491452-m5uv0imoma9m07i4pub2la7ufg1bvd1d.apps.googleusercontent.com
Client Secret:axnWdPtF0Q6xQSEg_RMIDq--

Problem Overview:

We require an adaptive system that primarily determines a set of quiz questions to be given to the user based on his/her profile. The goal of a perfect system is to achieve two things: Prevent student's motivation loss and distraction by not giving them questions below their competency level and prevent lowering student's self confidence not providing questions way above their competency level. The system can have three types of users: Administrator, Faculty and Student.
The administrator will have access to the entire system.
Faculty can create/edit/evaluate and delete quizzes and can see the performance of students who has taken his/her quiz.
Student can go to their profile and take up quizzes assigned to them. They can also view their progress and results.
The system should also be able to create reports and notifications that can aide the users with the system activities.

References:
http://www.sciencedirect.com/science/article/pii/S1877042814007289
https://www.researchgate.net/publication/228569733

Adaptive Functionalities
With our regular short brainstorming sessions we came up with the following modules that will be adaptive -  
User Modelling: Using both implicit and explicit profiling techniques to form an User Model that lets us have a detailed understanding of the user’s expertise in the subject.  
Adaptive Quiz: Adaptively select quiz from the knowledge base based on the user’s answering patterns and knowledge in the topic.
Adaptive Feedback: Personalize the type and content of the feedback based on identified areas of strength and weakness of the student.
Adaptive Support: Based on the student’s answers, identify subject areas that has scope for improvement and suggest learning materials and methods.

Other Modules:
We have identified a few more functionalities that are required for the working of the project and  to support the adaptive modules. They are -
Knowledge Base: Creating a database with subjects, related questions and its difficulty levels, comprehensive list of subject areas and categorizing questions and user details with and without user participation.
Notification engine: lets user and teacher receive relevant notifications and updates through mail.
Self Assessment: lets the user define goals for themselves and shows progress and warnings.
Quiz Interface: User friendly interface for the Quiz of the day.
User Management:Categorize users into various roles like faculty, students, etc., letting them register to our application and manage their profile information.
Reporting Services: Generating and sending reports to students and faculty indicating individual performance and overall statistics with required visualization.

Based on the knowledge of our team members and the requirements of the project we have decided, after carefully analyzing and discussing the feasibility, to use the following technologies,
MongoDB / PostgreSQL
Spring MVC
Bootstrap
AngularJS, jQuery
HTML5, CSS3