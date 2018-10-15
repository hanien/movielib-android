# Android Template

This is the template for the third assignment, covering Android. It contains a basic Android app with two activites, and a HTTP request to the Camel backend.

## Installation
In this assignment, we'll use Android Studio. Install it using the following instructions.

### Win/Mac:
1. Install AndroidStudio following the instructions at [https://developer.android.com/studio](https://developer.android.com/studio) (use all default/standard options, unless you know what you're doing)
2. Create your own fork of the template at [https://gitlab.com/dit341/android-template.git](https://gitlab.com/dit341/android-template.git)
3. Check out the fork (create a folder and do a git clone to it with the URL of your fork)
4. Select *Open an existing Android Studio project* -> Select the folder with the pulled template
5. Wait for the first build to finish - this takes a while (Gradle downloads lots of dependencies)
6. Run the project by selecting *Run* -> *Run* -> *0. Edit Configurations* -> *Run* (in the new Window)
7. In the new window, create a new virtual device (emulator). You will have to install a new system image first.

### Linux:
1. Make sure you have Java 8 installed
2. Download AndroidStudio from [https://developer.android.com/studio](https://developer.android.com/studio)
3. Unzip the downloaded file. Open a Terminal and cd into the bin folder within the extracted folder (android-studio/bin). Run ./studio.sh - follow the installation dialog, using all default/standard options, unless you know what you're doing.
4. Create your own fork of the template at [https://gitlab.com/dit341/android-template.git](https://gitlab.com/dit341/android-template.git)
5. Check out the fork (create a folder and do a git clone to it with the URL of your fork)
6. Select *Open an existing Android Studio project* -> Select the folder with the pulled template
7. Wait for the first build to finish - this takes a while (Gradle downloads lots of dependencies)
8. Run the project by selecting *Run* -> *Run* -> *0. Edit Configurations* -> *Run* (in the new Window)
9. In the new window, create a new virtual device (emulator). You will have to install a new system image first.

## Usage
The structure of the template is as follows (as displayed in the Android view of Android Studio; the folder structure is a slightly different).

* *manifests* - Contains the AndroidManifest.xml. This file has all the definitions (of Activities etc.)
* *java* - Contains all the Java files. There is a folder for the actual App, and two test folders.
* *res* - Contains the resources. The *layout* sub-folders contains the xml UI definitions. *values* contains value definitions (e.g., strings).

Creating an Activity, Service, etc. in Android Studio (right-click on the root or java folder -> *New* -> *Activity* -> *Empty Acitivity*) automatically creates the UI file, the Java file, and the corresponding definition in the manifest.

## HTTP Request
Clicking the Get Camels button in the main activity performs an HTTP GET request to the camel backend. It uses the URL defined in *res/values/urls.xml*. By default, it uses the IP address *10.0.2.2*. This is the IP address pointing from the Android Emulator to the machine running it (basically, the localhost on your computer).

If you have your app running on an actual device, you will have to have both the device and your computer running in the same network and point the URL to the computer IP. Additionally, you will have to disable any firewall rules that block external HTTP request to your HTTP port (e.g., 3000). This does *not* work within eduroam/NOMAD.

## onClick Listeners
In Android, buttons are connected to methods using so-called *Listeners*. If you open any of the UI XML definitions (e.g., *res/layour/acitivity_main.xml*) and click on a button, you can assign a method name to the *onClick* property in the *Attributes* tab. This will then execute the method with the same name in the Activity's Java file whenever the button is clicked. The method needs to have return parameter *void* and an input parameter *View view*.
