@postdatas
Feature: Add user 

Scenario: Add user with valid key
Given Adding new user
When  we give request  with invalid detials
And   we give request  with detials
Then the user shoukd be created

