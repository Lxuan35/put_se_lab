Louan HAMON -
University ID : ER-2198

# Answers to the questions :

### 2.1. How to change the operation of the mock object to verify that the interaction of the loadExpenses method with the database mock object was correct, i.e. first a connection to the database was opened (connect), then data was downloaded (queryAll), and finally the connection was terminated (close)?

To verify the interaction of the `loadExpenses` method with the database mock object, we can use a mocking framework like Mockito to create a mock of the database and then set up expectations for the method calls. With this, we can verify the order of interactions with the database mock object. 

### 5.1. Does the order of expectations for the same method matched by different arguments matter?

Yes, the order matter. When we define multiple expectations for the same method, Mockito evaluates them based on the order we wrote them, and the most recently defined rule that matches the arguments will be the one it uses.

