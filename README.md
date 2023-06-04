# TaskManager
TaskManager RESTfulAPI project for CIP.

The Task Manager API is a RESTful web service that allows users to manage tasks. 
It provides several endpoints for creating, retrieving, updating, and deleting tasks. 
This README file provides an overview of the available APIs and their functionality.


The base URL for accessing the Task Manager API is: localhost:8080/myAPI


------------------------------------------------------------------------------------------------------------------------------------------

1. Retrieve all tasks
URL: /myAPI
Method: GET
Description: Retrieves a list of all tasks.
Request Parameters: None
Response: A JSON array containing the details of all tasks. Each task object includes the following fields:
id (Integer): The unique identifier of the task.
name (String): The name of the task.
task (String): The task details.
description (String): A shortened description of the task. If the original description is longer than 20 characters, it will be truncated and appended with a message providing a URL to retrieve the full description.
dueDate (String): The due date of the task.
timeCreated (String): The timestamp when the task was created.

------------------------------------------------------------------------------------------------------------------------------------------

2. Add a new task
URL: /myAPI
Method: POST
Description: Creates a new task.
Request Body: A JSON object representing the new task with the following fields:
name (String): The name of the task.
task (String): The task details.
description (String): The full description of the task.
dueDate (String): The due date of the task.
Response: None

------------------------------------------------------------------------------------------------------------------------------------------

3. Delete a task
URL: /myAPI/{taskId}
Method: DELETE
Description: Deletes a task with the specified ID.
Path Parameter:
taskId (Integer): The unique identifier of the task to be deleted.
Response: None

------------------------------------------------------------------------------------------------------------------------------------------

4. Edit a task
URL: /myAPI/{taskId}
Method: PUT
Description: Updates the details of a task with the specified ID.
Path Parameter:
taskId (Integer): The unique identifier of the task to be updated.
Request Body: A JSON object representing the updated task with the following fields:
name (String): The new name of the task.
task (String): The new task details.
description (String): The new full description of the task.
dueDate (String): The new due date of the task.
Response: None

------------------------------------------------------------------------------------------------------------------------------------------

5. Retrieve a specific task
URL: /myAPI/{taskId}
Method: GET
Description: Retrieves the details of a task with the specified ID.
Path Parameter:
taskId (Integer): The unique identifier of the task to be retrieved.
Response: A JSON object containing the details of the task with the specified ID. If no task is found, an empty response will be returned.

------------------------------------------------------------------------------------------------------------------------------------------


That concludes the documentation for the Task Manager API. You can use these APIs to perform various operations on tasks in the task management system.
