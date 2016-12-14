# CS188Project3
An Android app that provides a list of things to do for patients that need therapy. Software Engineering - Project 3 - Fall 2016

## Disclaimer
The icons were retrieved from [Flat Icons](http://www.flaticon.com/)

## Using the Source Code
1. Clone the project 

  ```
    $ git clone https://github.com/maheshgaya/CS188Project3.git
    $ cd CS188Project3/
  ```
  
2. This project uses Firebase Authentication For Google and Email Sign-in. You need to get the configuration file from
the [Google Firebase Console](https://console.firebase.google.com).

  **Steps:**
  
  1. Click on "Create a new project" in the Firebase Console to create a new project
  2. Give it a useful name, choose your region, and click on "Create Project"
  3. Click on "Add Firebase to your Android App"
  4. The package name should be "com.example.kelly.mmelk" (without quotes). You can give it a nickname.
  5. Now on your computer terminal generate a SHA1 key with the following statement :
  
      ```
      $ keytool -exportcert -alias androiddebugkey -keystore ~/.android/debug.keystore -list -v
      ```
      
      **(Note: you can leave the password blank)**
      
     Copy the SHA1 and put that into the "Debug signing certificate SHA-1" field.
  6. Then click on "Add App". Make sure you download the "google-services.json"
  7. Add the "google-services.json" to the "app" directory
  8. Now, go back to the Firebase console and select Develop->Authentication on the left navigation menu
  9. Go to "Sign-in method" and enable "Google" and "Email/Password"
  
3. Import the project in Android Studio

  ```
    File -> New -> Import Project -> (Choose CS188Project3/MMELK/)
  ```
4. Run the app on an Android phone. Emulator will not work as they currently don't support the latest version of Firebase API

## Contributing to the Source Code
1. Fork this repository by clicking on the Fork button on the top right corner
2. [Clone](https://help.github.com/articles/cloning-a-repository/) your own repository on your desktop
3. Commit your changes to your own repository
4. Once you are ready for your patch, create a [pull request](https://yangsu.github.io/pull-request-tutorial/)

## License

  Copyright 2016 CS188 Project 3 Group 1

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

